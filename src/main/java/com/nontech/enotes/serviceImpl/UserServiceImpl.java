/**
 * Created By Arun Singh
 * Date:03-02-2025
 * Time:10:07
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.serviceImpl;

import com.nontech.enotes.config.security.CustomUserDetails;
import com.nontech.enotes.config.security.UserDetailsServiceImpl;
import com.nontech.enotes.dto.EmailRequest;
import com.nontech.enotes.dto.UserDto;
import com.nontech.enotes.dto.request.LoginRequest;
import com.nontech.enotes.dto.response.LoginResponse;
import com.nontech.enotes.entity.AccountStatus;
import com.nontech.enotes.entity.Role;
import com.nontech.enotes.entity.User;
import com.nontech.enotes.jwt.JwtService;
import com.nontech.enotes.repository.RoleRepo;
import com.nontech.enotes.repository.UserRepo;
import com.nontech.enotes.service.UserService;
import com.nontech.enotes.util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private Validation validation;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public Boolean register(UserDto userDto, String url) throws Exception {
        validation.userValidation(userDto);
        User user = mapper.map(userDto, User.class);
        setRole(userDto, user);

        AccountStatus status = AccountStatus.builder()
                .isActive(false)
                .verificationCode(UUID.randomUUID().toString())
                .build();
        user.setStatus(status);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        if (!ObjectUtils.isEmpty(savedUser)) {
            // send email
            sendEmail(savedUser, url);
            return true;
        }
        return false;
    }

    private void sendEmail(User savedUser, String url) throws Exception {
        String message = "Hi,<b>[[username]]</b>"
                + "<br><br>Your account registered successfully<br>"
                + "<br>Click the bellow link to verify and active your account<br>"
                + "<a href='[[url]]'>Click Here</a><br><br>"
                + "Thanks, <br>"
                + "Enotes.com";
        message = message.replace("[[username]]", savedUser.getFirstName());
        message = message.replace("[[url]]", url + "/api/v1/home/verify?uid=" + savedUser.getId() + "&&code=" + savedUser.getStatus().getVerificationCode());
        EmailRequest emailRequest = EmailRequest.builder()
                .to(savedUser.getEmail())
                .title("Account creating conformation")
                .subject("Account created successfully")
                .message(message)
                .build();
        emailService.sendMail(emailRequest);
    }

    private void setRole(UserDto userDto, User user) {
        List<Integer> reqRoleId = userDto.getRoles().stream().map(roleDto -> roleDto.getId()).toList();
        List<Role> roles = roleRepo.findAllById(reqRoleId);
        user.setRoles(roles);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authenticate.getPrincipal();

            String token = jwtService.generateToken(customUserDetails.getUser());

            LoginResponse loginResponse = LoginResponse.builder()
                    .userDto(mapper.map(customUserDetails.getUser(), UserDto.class))
                    .token(token)
                    .build();
            return loginResponse;
        }
        return null;
    }
}

/**
 * Created By Arun Singh
 * Date:03-02-2025
 * Time:10:07
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.serviceImpl;

import com.nontech.enotes.dto.UserDto;
import com.nontech.enotes.entity.Role;
import com.nontech.enotes.entity.User;
import com.nontech.enotes.repository.RoleRepo;
import com.nontech.enotes.repository.UserRepo;
import com.nontech.enotes.service.UserService;
import com.nontech.enotes.util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

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

    @Override
    public Boolean register(UserDto userDto) {
        validation.userValidation(userDto);
        User user = mapper.map(userDto, User.class);
        setRole(userDto, user);
        User savedUser = userRepo.save(user);
        if (!ObjectUtils.isEmpty(savedUser)) {
            return true;
        }
        return false;
    }

    private void setRole(UserDto userDto, User user) {
        List<Integer> reqRoleId = userDto.getRoles().stream().map(roleDto -> roleDto.getId()).toList();
        List<Role> roles = roleRepo.findAllById(reqRoleId);
        user.setRoles(roles);
    }
}

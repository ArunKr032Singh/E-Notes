/**
 * Created By Arun Singh
 * Date:03-02-2025
 * Time:15:53
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.serviceImpl;

import com.nontech.enotes.dto.EmailRequest;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailFrom;

    public void sendMail(EmailRequest emailRequest) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(mailFrom,emailRequest.getTitle());
        helper.setTo(emailRequest.getTo());
        helper.setSubject(emailRequest.getSubject());

        helper.setText(emailRequest.getMessage(),true);

        mailSender.send(message);

    }
}

package lk.ijse.gdse.wanderlust.controller;


import lk.ijse.gdse.wanderlust.dto.ResponsDto;
import lk.ijse.gdse.wanderlust.dto.UserDTO;
import lk.ijse.gdse.wanderlust.servies.impl.UserServiesimpl;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;


@RestController
@RequestMapping("/api/v1/password")
@CrossOrigin
public class PasswordController {

    @Autowired
    private UserServiesimpl userService;

    @PostMapping("/sentOTP")
    public String sentOTP(@RequestBody Map<String, String> requestBody) {
        System.out.println("sentOTP");
        try {
            String email = requestBody.get("email");
            System.out.println(email + "  email");
            boolean exists = userService.ifEmailExists(email);
            if (!exists) {
                return "Email does not exist";
            }
            System.out.println("sentOTP");
            int code = (1000 + (int) (Math.random() * 9000));
            sendEmail(email, code);
            return String.valueOf(code);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

        public void sendEmail(String email, int code) {
            System.out.println("nipun");
        new Thread(() -> {
            try {
                String senderEmail = "imeshnipun@gmail.com";
                String senderPassword = "upgb vmib oacs gmka"; // Replace with the app-specific password from Gmail

                String subject = "OTP Code from Wonderlust";

                String body = "Dear User,\n\n" +
                        "Your OTP code for accessing Wanderlust services is: " + code + "\n\n" +
                        "Please use this code to verify your identity. The OTP is valid for 10 minutes only.\n" +
                        "If you did not request this OTP, please ignore this email or contact support.\n\n" +
                        "Best regards,\n" +
                        "The Wanderlust Team";

                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                // Create a mail session with authentication
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

                try {
                    // Create a MimeMessage object for the email
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(senderEmail));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message.setSubject(subject);
                    message.setText(body);

                    // Send the email
                    Transport.send(message);

                    System.out.println("OTP sent successfully to " + email);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        }

    @PutMapping("/updatePassword")
    public ResponseEntity<ResponsDto> updatePassword(@RequestBody UserDTO userDTO) {
        try {
            UserDTO exuser = userService.searchUser(userDTO.getEmail());
            if (exuser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponsDto(StatusList.Not_Found, "User not found", null));
            }

            // set new password and update using resetPass()
            exuser.setPassword(userDTO.getPassword());
            int res = userService.resetPass(exuser);

            if (res == StatusList.Created) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponsDto(StatusList.OK, "✅ Password updated successfully!", null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                        .body(new ResponsDto(StatusList.Bad_Gateway, "❌ Error updating password", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<ResponsDto> resetPassword(@RequestBody UserDTO userDTO) {
        try {
            UserDTO exuser = userService.searchUser(userDTO.getEmail());
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponsDto(StatusList.OK, "Password updated successfully", exuser));
            if (exuser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponsDto(StatusList.Not_Found, "User not found", null));
            }

            exuser.setPassword(userDTO.getPassword());
            int res = userService.resetPass(exuser);

            switch (res) {
                case StatusList.Created -> {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponsDto(StatusList.Created, "Password updated successfully", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponsDto(StatusList.Bad_Gateway, "Error updating password", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.Utils.Utility;
import swp391.quizpracticing.service.IVerificationService;

/**
 *
 * @author Mosena
 */
@Service
public class VerificationService implements IVerificationService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public void sendVerification(String name, String email, String token
            ,String password){
        String siteURL=Utility.getSiteURL();
        String toAddress = email;
        String fromAddress = "gmail@sss";
        String senderName = "IQuizz";
        String subject = "IQuiz account created";
        String content = "Dear [[name]],<br>"
                +"Your email has been used to register IQuizz<br>"
                +"Password is [[password]]<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3><br>"
                + "If you did not do this, please click here:<br>"
                + "<h3><a href=\"[[URL_discard]]\" target=\"_self\">DISCARD</a></h3><br>"
                + "Thank you,<br>"
                + "IQuizz.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            content = content.replace("[[name]]", name);
            content = content.replace("[[password]]", password);
            String verifyURL = siteURL + "/user-created/verify?code=" + token;
            String discardURL = siteURL + "/user-created/discard?code=" + token;
            content = content.replace("[[URL]]", verifyURL);
            content=content.replace("[[URL_discard]]", discardURL);
            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        mailSender.send(message);
    }

    @Override
    public void sendVerificationRegistration(Integer registrationId,String name, String email, String token, String password) {
        String siteURL=Utility.getSiteURL();
        String toAddress = email;
        String fromAddress = "gmail@sss";
        String senderName = "IQuizz";
        String subject = "IQuiz account created";
        String content = "Dear [[name]],<br>"
                +"Your email has been used to register IQuizz<br>"
                +"Password is [[password]]<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3><br>"
                + "If you did not do this, please click here:<br>"
                + "<h3><a href=\"[[URL_discard]]\" target=\"_self\">DISCARD</a></h3><br>"
                + "Thank you,<br>"
                + "IQuizz.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            content = content.replace("[[name]]", name);
            content = content.replace("[[password]]", password);
            String verifyURL = siteURL + "/user-created/verify?code=" + token;
            String discardURL = siteURL + "/registration-created/discard?code=" + token+"&registrationId="+registrationId;
            content = content.replace("[[URL]]", verifyURL);
            content=content.replace("[[URL_discard]]", discardURL);
            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        mailSender.send(message);
    }
}
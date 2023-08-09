/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.repository.IAccountRepository;
import swp391.quizpracticing.service.IAccountService;

/**
 *
 * @author Admin
 */
@Component
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    IAccountRepository accountRepository;

    @Value("${timeout}")
    private int timeout;

    @Override
    public void updateResetPasswordToken(String token, String email) {
        User acc = accountRepository.findByEmail(email);
        if (acc != null) {
            acc.token(token);
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime updatedDateTime = currentDateTime.plusMinutes(timeout);
            Timestamp updatedDate = Timestamp.valueOf(updatedDateTime);
            acc.setLastUpdateDate(updatedDate);
            accountRepository.save(acc);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return accountRepository.findAccountByToken(token);
    }

    @Override
    public void updatePassword(User acc, String newPassword) {
        acc.setPassword(newPassword);

        acc.token(null);
        accountRepository.save(acc);
    }

    public String checkEmailExist(String email) {
        return accountRepository.checkEmailExist(email);
    }

    @Override
    public void updateUserProfile(UserDTO userSession) {
        accountRepository.save(modelMapper.map(userSession, User.class));
    }

}

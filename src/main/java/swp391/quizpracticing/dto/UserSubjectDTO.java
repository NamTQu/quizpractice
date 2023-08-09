package swp391.quizpracticing.dto;


import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSubjectDTO {
    private Integer id;
    private UserDTO user;
    private SubjectDTO subject;
    private PricepackageDTO pricePackage;
    private Timestamp registrationTime;
    private RegistrationstatusDTO registrationStatus;
    private Timestamp validFrom;
    private Timestamp validTo;
    private UserDTO userUpdate;
    private String notes;
    private UserDTO userCreated;
}


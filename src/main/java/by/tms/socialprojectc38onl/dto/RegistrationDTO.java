package by.tms.socialprojectc38onl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String nickname;
    private String email;
    private String password;
    private String repeatPassword;
}

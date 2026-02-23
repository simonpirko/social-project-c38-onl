package by.tms.socialprojectc38onl.dto;

import by.tms.socialprojectc38onl.models.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResultDTO {
    private boolean isSuccess;
    private String message;
}

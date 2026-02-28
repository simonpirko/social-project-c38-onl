package by.tms.socialprojectc38onl.models;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String nickname;
    private String email;
    private String password;
    private Date createAt;
}

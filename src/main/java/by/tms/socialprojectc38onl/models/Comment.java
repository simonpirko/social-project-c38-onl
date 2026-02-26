package by.tms.socialprojectc38onl.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private Long accountId;
    private Long postId;
    private String text;
    private Timestamp createdAt;
}

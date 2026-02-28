package by.tms.socialprojectc38onl.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int id;
    private String title;
    private String description;
    private Date createAt;
    private int accountID;
    private String images;
    private List<Comment> comments;
}

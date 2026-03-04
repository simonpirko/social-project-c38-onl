package by.tms.socialprojectc38onl.dto;

import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.models.Post;

import java.util.List;

public record AccountDataDTO(Account account, List<Post> userPosts, int postsCount, int followersCount, int followingCount) {}

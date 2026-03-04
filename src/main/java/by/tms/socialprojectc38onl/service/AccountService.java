package by.tms.socialprojectc38onl.service;

import by.tms.socialprojectc38onl.dao.DAOAccounts;
import by.tms.socialprojectc38onl.dao.DAOPosts;
import by.tms.socialprojectc38onl.dao.SubscribeDAO;
import by.tms.socialprojectc38onl.dto.AccountDataDTO;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.models.Post;

import java.util.List;
import java.util.Objects;

public class AccountService {
    private final DAOAccounts accountDAO;
    private final DAOPosts postsDAO;
    private final SubscribeDAO subscribeDAO;

    private static AccountService INSTANCE;

    private AccountService() {
        this.accountDAO = DAOAccounts.getInstance();
        this.postsDAO = DAOPosts.getInstance();
        this.subscribeDAO = SubscribeDAO.getInstance();
    }

    public static synchronized AccountService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new AccountService();
        }
        return INSTANCE;
    }

    public AccountDataDTO getAccountData(Integer id) {
        Account account = accountDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Post> posts = postsDAO.findByAccountId(id);
        int postsCount = postsDAO.countByAccountId(id);
        int followersCount = subscribeDAO.countFollowers(id);
        int followingCount = subscribeDAO.countFollowing(id);

        return new AccountDataDTO(account, posts, postsCount, followersCount, followingCount);
    }
}

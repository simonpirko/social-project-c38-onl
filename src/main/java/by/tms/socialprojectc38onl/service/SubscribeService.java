package by.tms.socialprojectc38onl.service;

import by.tms.socialprojectc38onl.dao.SubscribeDAO;
import by.tms.socialprojectc38onl.models.Account;

import java.util.List;
import java.util.Objects;

public class SubscribeService {
    private final SubscribeDAO subscribeDAO;
    private static SubscribeService INSTANCE;

    private SubscribeService () {
        this.subscribeDAO = SubscribeDAO.getInstance();
    }

    public static synchronized SubscribeService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new SubscribeService();
        }
        return INSTANCE;
    }

    public boolean isFollowing(Integer followerId, Integer followingId) {
        return subscribeDAO.isFollowing(followerId, followingId);
    }

    public void save(Integer followerId, Integer followingId) {
        subscribeDAO.save(followerId, followingId);
    }

    public void delete(Integer followerId, Integer followingId) {
        subscribeDAO.delete(followerId, followingId);
    }

    public int countFollowers(Integer userId) {
        return subscribeDAO.countFollowers(userId);
    }

    public int countFollowing(Integer userId) {
        return subscribeDAO.countFollowers(userId);
    }

    public List<Account> findAllFollowers(Integer userId) {
        return subscribeDAO.getFollowers(userId);
    }

    public List<Account> findAllFollowings(Integer userId) {
        return subscribeDAO.getFollowings(userId);
    }
}

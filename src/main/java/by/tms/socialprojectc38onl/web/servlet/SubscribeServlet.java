package by.tms.socialprojectc38onl.web.servlet;

import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.service.SubscribeService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/account/subscribe")
public class SubscribeServlet extends HttpServlet {
    private final SubscribeService subscribeService = SubscribeService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account currentAccount = (Account) req.getSession().getAttribute("account");

        if (Objects.isNull(currentAccount)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Integer followerId = currentAccount.getId();
        Integer followingId = Integer.parseInt(req.getParameter("id"));

        if(Objects.isNull(followingId)) {
            resp.sendRedirect(req.getContextPath() + "/account");
        }

        if(subscribeService.isFollowing(followerId, followingId)) {
            subscribeService.delete(followerId, followingId);
        } else {
            subscribeService.save(followerId, followingId);
        }

        resp.sendRedirect(req.getContextPath() + "/account?id=" + followingId);
    }
}

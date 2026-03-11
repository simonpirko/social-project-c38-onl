package by.tms.socialprojectc38onl.web.servlet;

import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.service.SubscribeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/account/following")
public class FollowingsServlet extends HttpServlet {
    private final SubscribeService subscribeService = SubscribeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account currentAccount = (Account) req.getSession().getAttribute("account");
        if (Objects.isNull(currentAccount)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Integer showingAccountId;
        Integer currentAccountId = currentAccount.getId();
        String accountId = req.getParameter("id");

        if (Objects.isNull(accountId) || accountId.isEmpty()) {
            showingAccountId = currentAccountId;
        } else
        {
            showingAccountId = Integer.parseInt(accountId);
        }

        List<Account> followings = subscribeService.findAllFollowings(showingAccountId);
        req.setAttribute("followings", followings);
        req.getRequestDispatcher("/pages/followings.jsp").forward(req, resp);
    }
}
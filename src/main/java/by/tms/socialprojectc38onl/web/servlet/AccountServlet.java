package by.tms.socialprojectc38onl.web.servlet;

import by.tms.socialprojectc38onl.dto.AccountDataDTO;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.service.AccountService;
import by.tms.socialprojectc38onl.service.SubscribeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/account/*")
public class AccountServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();
    private final SubscribeService subscribeService = SubscribeService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account currentAccount = (Account) req.getSession().getAttribute("account");
        String pathInfo = req.getPathInfo();
//        int accountId = 0;
//
//        if (Objects.nonNull(pathInfo) && pathInfo.matches("/\\d+")) {
//            accountId = Integer.parseInt(pathInfo.substring(1));
//        }
        Integer showingAccountId;
        Integer currentAccountId = currentAccount.getId();
        String accountId = req.getParameter("id");

        if (Objects.isNull(accountId) || accountId.isEmpty()) {
            showingAccountId = currentAccountId;
            req.setAttribute("isPersonal", true);
        } else
        {
            showingAccountId = Integer.parseInt(accountId);

            if(Objects.equals(showingAccountId, currentAccountId)) {
                req.setAttribute("isPersonal", true);
            }
        }
        boolean followingStatus = subscribeService.isFollowing(currentAccountId, showingAccountId);
        req.setAttribute("isFollowing", followingStatus);

        AccountDataDTO accountData = accountService.getAccountData(showingAccountId);
        req.setAttribute("accountData", accountData);
        req.getRequestDispatcher("/pages/account.jsp").forward(req, resp);
    }
}

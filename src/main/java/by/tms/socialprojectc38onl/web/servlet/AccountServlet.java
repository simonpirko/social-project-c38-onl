package by.tms.socialprojectc38onl.web.servlet;

import by.tms.socialprojectc38onl.dto.AccountDataDTO;
import by.tms.socialprojectc38onl.service.AccountService;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int accountId = 0;

        if (Objects.nonNull(pathInfo) && pathInfo.matches("/\\d+")) {
            accountId = Integer.parseInt(pathInfo.substring(1));
        }

        AccountDataDTO accountData = accountService.getAccountData(accountId);

        req.setAttribute("accountData",accountData);
        req.getRequestDispatcher("/pages/account.jsp").forward(req, resp);
    }
}

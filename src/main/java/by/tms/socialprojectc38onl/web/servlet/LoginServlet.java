package by.tms.socialprojectc38onl.web.servlet;

import by.tms.socialprojectc38onl.exception.AuthException;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    final LoginService auth = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            Account account = auth.authenticate(req.getParameter("email"), req.getParameter("password"));
            req.getSession().setAttribute("account", account);
            resp.sendRedirect(req.getContextPath() + "/");
        }
        catch (AuthException e) {
            req.setAttribute("message", e.getMessage());
            req.setAttribute("isCreated", false);
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }

    }
}

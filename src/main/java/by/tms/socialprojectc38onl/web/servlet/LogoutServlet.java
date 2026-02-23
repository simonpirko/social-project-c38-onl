package by.tms.socialprojectc38onl.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/logout")
public class LogoutServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if(!Objects.isNull(session)) {
            session.invalidate();
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }
}

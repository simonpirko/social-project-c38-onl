package by.tms.socialprojectc38onl.web.servlet;

import by.tms.socialprojectc38onl.dto.RegistrationDTO;
import by.tms.socialprojectc38onl.dto.RegistrationResultDTO;
import by.tms.socialprojectc38onl.service.RegistrationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String name = req.getParameter("nickname");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        RegistrationService registrationService = new RegistrationService();
        RegistrationResultDTO registrationResultDTO = registrationService.register(
                new RegistrationDTO(email, name, password, repeatPassword)
        );

        if (registrationResultDTO.isSuccess()) {
            req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
            return;
        }

        req.setAttribute("message", registrationResultDTO.getMessage());
        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        req.getRequestDispatcher("/pages/registration.jsp").forward(req, res);
    }
}

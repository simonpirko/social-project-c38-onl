package by.tms.socialprojectc38onl.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/",
            "/login",
            "/registration",
            "/users",
            "/posts",
            "/account"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getServletPath();
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = Objects.nonNull(session) && session.getAttribute("account") != null;
        boolean isAuthPath = "/login".equals(path) || "/register".equals(path);

        if (isLoggedIn && isAuthPath) {
            res.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        boolean isPublic = PUBLIC_PATHS.contains(path);

        if (!isPublic && !isLoggedIn) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        chain.doFilter(request, response);
    }
}


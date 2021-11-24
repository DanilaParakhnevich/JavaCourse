package by.parakhnevich.keddit.controller.filter;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.CommandName;
import by.parakhnevich.keddit.controller.command.CommandPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class BlockFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.isBanned()) {
            if (request.getParameter("command").equals(CommandName.LOGOUT.toString().toLowerCase(Locale.ROOT)) ||
            request.getParameter("command").equals(CommandName.LOGIN.toString().toLowerCase(Locale.ROOT))) {
                request.getSession().invalidate();
                response.sendRedirect(CommandPage.LOGIN_PAGE);
            }
            else {
                request.getRequestDispatcher(CommandPage.BLOCKED_USER).forward(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }
}

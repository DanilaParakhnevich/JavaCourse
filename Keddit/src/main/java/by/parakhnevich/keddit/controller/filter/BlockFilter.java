package by.parakhnevich.keddit.controller.filter;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.CommandName;
import by.parakhnevich.keddit.controller.command.CommandPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BlockFilter implements Filter {
    List<CommandName> commandNames = Arrays.asList(CommandName.LOGIN, CommandName.LOGIN_PAGE,
            CommandName.REGISTRATION_PAGE, CommandName.SIGN_UP);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain next) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        User user = (User) request.getSession().getAttribute("user");
        if (user == null && !commandNames.contains(CommandName.valueOf(request.getParameter("command").toUpperCase()))) {
            request.getRequestDispatcher(CommandPage.LOGIN_PAGE).forward(request, response);
        }
        else if (user != null && user.isBanned() && !commandNames.contains(CommandName.valueOf(request.getParameter("command").toUpperCase()))) {
            request.getRequestDispatcher(CommandPage.BLOCKED_USER).forward(request, response);
        }
        else {
            next.doFilter(request, response);
        }
    }
}

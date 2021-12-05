package by.parakhnevich.keddit.controller.filter;

import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.CommandName;
import by.parakhnevich.keddit.controller.command.CommandPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagesFilter implements Filter {
    private final Map<Role, List<CommandName>> pagesFilter = new HashMap<>();

    public PagesFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
        List<CommandName> adminPages = Arrays.asList(
                CommandName.EDIT_ROLE_PAGE,
                CommandName.EDIT_ROLE,
                CommandName.ON_MODERATION_PAGE,
                CommandName.PUBLICATIONS_ON_MODERATION,
                CommandName.ACCEPT_PUBLICATION,
                CommandName.DENY_PUBLICATION,
                CommandName.BLOCK_USER
        );

        pagesFilter.put(Role.ADMIN, adminPages);

        List<CommandName> moderatorPages = Arrays.asList(
                CommandName.PUBLICATIONS_ON_MODERATION,
                CommandName.ON_MODERATION_PAGE
        );

        pagesFilter.put(Role.MODERATOR, moderatorPages);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        Role role = user.getRole();
        String command = request.getParameter("command");
        if (command == null) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
        } else {
            if (!pagesFilter.get(role).contains(CommandName.valueOf(command.toUpperCase()))) {
                request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            } else {
                chain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    private boolean isCommandEnable(CommandName command) {
        for (Map.Entry<Role, List<CommandName>> element : pagesFilter.entrySet()) {
            if (element.getValue().contains(command)) {
                return true;
            }
        }
        return false;
    }
}

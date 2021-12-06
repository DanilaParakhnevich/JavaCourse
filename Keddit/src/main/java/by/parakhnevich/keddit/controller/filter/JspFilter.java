package by.parakhnevich.keddit.controller.filter;

import by.parakhnevich.keddit.controller.command.CommandPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * This filter denies access for users to .jsp files directly
 * @author Danila Parakhnevich
 */
public class JspFilter implements Filter {
    public void  doFilter(ServletRequest request, ServletResponse response,
                          FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req= (HttpServletRequest) request;
        req.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request,response);
    }
}

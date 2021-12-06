package by.parakhnevich.keddit.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter translates all the information into UTF-8 standard.
 * @author Danila Parakhnevich
 */
public class CharsetFilter implements Filter {
    Logger logger = LogManager.getLogger(CharsetFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain       next)
            throws IOException, ServletException {
        if(null == request.getCharacterEncoding())
            request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        logger.debug("CharsetFilet on command : " + request.getParameter("command"));
        next.doFilter(request, response);
    }

}

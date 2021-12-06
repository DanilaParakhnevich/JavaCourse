package by.parakhnevich.keddit.controller.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The tag for copyrighting in jsp pages.
 * @author Danila Parakhnevich
 */
public class CopyrightTag extends TagSupport {

    private final Logger logger = LogManager.getLogger(CopyrightTag.class);
    private final String COPYRIGHT = "©";
    private final String STICK = "-";

    private int year;
    private String developer;


    public void setDeveloper(String developer) {
        this.developer = developer;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int doStartTag() throws JspException {
        String copyright = getCopyright();
        try {
            JspWriter out = pageContext.getOut();
            out.write(copyright);
        } catch (IOException e) {
            logger.error(e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    private String getCopyright() {
        StringBuilder value;
        value = new StringBuilder(COPYRIGHT);
        value.append(developer).append(" ").append(STICK).append(" ").append(year);
        return value.toString();
    }
}

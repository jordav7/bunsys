package ec.com.dlc.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class VerifySessionFilter
 */
@WebFilter(value="/VerifySessionFilter", urlPatterns={"/*"})
public class VerifySessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public VerifySessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession(Boolean.FALSE);
		 String reqURI = httpServletRequest.getRequestURI();
         if ( reqURI.indexOf("/login.xhtml") >= 0 || (session != null && session.getAttribute("usuarioLogueado") != null)
                                     || reqURI.contains("javax.faces.resource") || reqURI.contains("resources")) {
             chain.doFilter(request, response);
         } else {  // user didn't log in but asking for a page that is not allowed so take user to login page
        	 httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.xhtml");
         }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

package org.cloudcoder.app.server.rpc;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.cloudcoder.app.shared.model.CloudCoderAuthenticationException;
import org.cloudcoder.app.shared.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletUtil {
	private static Logger logger = LoggerFactory.getLogger(ServletUtil.class); 
	
	/**
	 * Check whether or not the client is authenticated.
	 * 
	 * @param request    the {@link HttpServletRequest}
	 * @param servletCls the Class object of the servlet (this information is useful for logging authentication failures)
	 * @return the authenticated User object
	 * @throws CloudCoderAuthenticationException if the client is not authenticated
	 */
	public static User checkClientIsAuthenticated(HttpServletRequest request, Class<? extends Servlet> servletCls) throws CloudCoderAuthenticationException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionAttributeKeys.USER_KEY);
		if (user == null) {
			logger.warn("{}: Authentication failure - no user in session", servletCls.getSimpleName());
			throw new CloudCoderAuthenticationException();
		}
		return user;
	}
	

}

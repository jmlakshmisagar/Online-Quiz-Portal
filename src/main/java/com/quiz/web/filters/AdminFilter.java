
package com.quiz.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebFilter(urlPatterns = { "/admin/*" })
public class AdminFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession s = req.getSession(false);
		if (s == null || s.getAttribute("admin") == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/login");
			return;
		}
		chain.doFilter(request, response);
	}
}

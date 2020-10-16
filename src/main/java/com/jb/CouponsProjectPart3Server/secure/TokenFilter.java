package com.jb.CouponsProjectPart3Server.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jb.CouponsProjectPart3Server.utils.Env;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@WebFilter("/*")
public class TokenFilter implements Filter {

	@Autowired
	TokenManager tokenManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = req.getRequestURL().toString();
		String token = "No Token";

		if (!(req.getMethod().equalsIgnoreCase("OPTIONS")))
			if (!path.contains("login"))
				if (!path.contains("register"))
					if (!path.contains("general")) {
						// Check if token exist and save it
						Enumeration<String> headerNames = req.getHeaderNames();
						if (headerNames != null) {
							while (headerNames.hasMoreElements()) {
								String name = headerNames.nextElement();
								if (name.equalsIgnoreCase("Token")) {
									token = req.getHeader(name);
									break;
								}
							}
						}

//						System.out.println();
//					System.out.println("Method: "+req.getMethod());
//					System.out.println("checking the request " + path);
//					System.out.println("Token: " + token);
						if (tokenManager.getTokens().get(token) == null) {
							res.sendRedirect(Env.URL + "/404");
							return;
						}
					}
		chain.doFilter(request, response);

	}

}
package com.bonedone.filter;

import com.bonedone.model.User;
import com.bonedone.util.RestUtil;
import com.bonedone.util.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/auth")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        if (Objects.isNull(user)) response.setStatus(401);
        else response.getWriter().write(RestUtil.getJsonFromObject(user));


    }

    @Override
    public void destroy() {

    }
}

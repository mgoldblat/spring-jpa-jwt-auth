package com.mgoldblat.auth.filter;

import com.mgoldblat.auth.constant.SecurityConstants;
import com.mgoldblat.auth.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(SecurityConstants.HEADER_AUTHORIZATION_KEY);

        if (StringUtils.isEmpty(authorizationHeader)
                || !authorizationHeader.startsWith(SecurityConstants.TOKEN_BEARER_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.replace(SecurityConstants.TOKEN_BEARER_PREFIX + " ", "");

        String userName = TokenUtil.INSTANCE.getUserName(token);
        UserDetails user = this.userDetailsService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken authenticationToken = TokenUtil.INSTANCE.getAuthentication(token, user);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
}

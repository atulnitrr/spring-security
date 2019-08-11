package com.atul.security.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class LogginFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
            final FilterChain filterChain) throws IOException, ServletException {
        Assert.isTrue(servletRequest instanceof HttpServletRequest, "this assumes you have HTTP request");
        HttpServletRequest request = HttpServletRequest.class.cast(servletRequest);
        final String uri  = request.getRequestURI();
        logger.info("URI --> " + uri + " . ");
        long time = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long delta = System.currentTimeMillis() - time;
        logger.info("request for " + uri + " tool " + delta + " ms");

    }

    @Override public void destroy() {

    }
}

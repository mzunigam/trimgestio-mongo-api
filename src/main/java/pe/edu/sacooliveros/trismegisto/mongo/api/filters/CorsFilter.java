package pe.edu.sacooliveros.trismegisto.mongo.api.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{

        HttpServletResponse response = (HttpServletResponse) servletResponse;
         HttpServletRequest request = (HttpServletRequest) servletRequest;

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, Authorization");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            if(response.getHeader("Access-Control-Allow-Origin").equals("*")){
                filterChain.doFilter(servletRequest, response);
             }
            else if(response.getHeader("Access-Control-Allow-Origin").contains(request.getRemoteHost())) {
                filterChain.doFilter(servletRequest, response);
            }else{
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

    }

    @Override
    public void destroy(){
        Filter.super.destroy();
    }

}

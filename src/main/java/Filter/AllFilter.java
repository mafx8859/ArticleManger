package Filter;

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
@WebFilter({"/lookArticle.jsp","/mangerArticle.jsp","/writeArticle.jsp"})
public class AllFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response , FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
       HttpServletRequest req=(HttpServletRequest)request ;
       HttpServletResponse resp=(HttpServletResponse )response ;
       req.setCharacterEncoding("utf-8");
       resp.setContentType("text/html;charset=UTF-8");
       if(req.getSession().getAttribute("iflogin")==null){
    	   response.getWriter().print("<script language='javascript'>alert('您还未登录！！！请登录');</script>");
    	   resp.setHeader("refresh", "1,URL=index.jsp");
       }else{
    	   chain.doFilter(request,response);
       }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

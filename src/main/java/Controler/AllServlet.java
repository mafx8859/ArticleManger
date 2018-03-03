package Controler;

import java.io.IOException;
import java.util.Timer;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.ldap.SortControl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.descriptor.web.LoginConfig;

import Entity.ArticleClassBean;
import Entity.Articles;
import Entity.RegexString;
import Entity.Users;
import Service.*; 
/**
 * Servlet implementation class AllServlet
 */
public class AllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String flag=request.getParameter("flag");
		switch(flag){
		     case "login":login(request, response);break;
		     case "reginster":register(request, response);break;
		     case "addArticles":addArticles(request, response);break;
		     case "lookArticle":lookArticle(request,response);break;
		     case "delArticle":delArticle(request,response);break;
		     case "editorArticle":editorArticle(request,response);break;
		     case "subEditorArticles":subEditorArticles(request,response);break;
		     case "selectMessage":mangerArticles(request, response);break;
		     case "safeout":safeOut(request, response);break;
		     case "selectByClass":selectByClass(request, response);break;
		     case "sort":sortControl(request, response);
		     //case "download":download(request ,response);
		     
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void sortControl(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String what=request .getParameter("what");
		int id=Integer.parseInt(request.getParameter("id"));
		ArtcileService service=new ArtcileService();
		boolean ifSucceed;
		if(what.equals("up")){
			ifSucceed=service .SortArticle(id,-1);
		}else{
			ifSucceed=service .SortArticle(id,1);
		}
		if(ifSucceed ==true){                      
			mangerArticles(request, response);
		}
	}
	private void selectByClass(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int classId=Integer.parseInt(request .getParameter("id"));
		if(classId==1){
			mangerArticles(request, response);
			return;
		}
		ArrayList<Articles> list=new ArrayList<Articles>();
		ArtcileService service=new ArtcileService();
		list=service.selectByClassService(classId);
		int count=list.size();
		request.setAttribute("messageCount", count);
		request.setAttribute("art", list);
		try {
			request .getRequestDispatcher("mangerArticle.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void safeOut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void subEditorArticles(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String title=request.getParameter("title");
		String issure=request.getParameter("issure");
		String article=request.getParameter("container");
		String date=request.getParameter("date");
		int classId=Integer.parseInt(request.getParameter("articleClass"));
		Articles art=new Articles();
		art.setArticle(article);
		art.setDate(date);
		art.setIssure(issure);
		art.setTitle(title);
		art.setArticleClass(classId);
		int id=Integer.parseInt(request.getParameter("id"));
		ArtcileService service=new ArtcileService();
		try {
			boolean ifSucceed=service.subEditorArticlesService(id,art);
			if(ifSucceed==true){
			   	response.getWriter().print("<script language='javascript'>alert('修改成功');</script>");
			    response.setHeader("refresh", "1,URL=writeArticle.jsp");
			}else{
				response.getWriter().print("<script language='javascript'>alert('修改失败');</script>");
				response.setHeader("refresh", "1,URL=writeArticle.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void editorArticle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int articleId=Integer .parseInt(request.getParameter("id"));
		ArtcileService service=new ArtcileService();
		try {
			Articles articles=service .selectArticles_1(articleId);
			request .setAttribute("art", articles);
			request .getRequestDispatcher("writeArticle.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void delArticle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int articleId=Integer .parseInt(request.getParameter("id"));
		ArtcileService service=new ArtcileService();
		try {
			boolean ifSucceed=service.delArticleService(articleId);
			if(ifSucceed==true){
			    response.getWriter().print("<script language='javascript'>alert('删除成功');</script>");
				mangerArticles(request ,response);
			}else{
				response.getWriter().print("<script language='javascript'>alert('删除失败');</script>");
				mangerArticles(request ,response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void lookArticle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int articleId=Integer .parseInt(request.getParameter("id"));
		ArtcileService service=new ArtcileService();
		try {
			Articles articles=service .selectArticles_1(articleId);
			System.out.println("fdgdf==="+articles);
			request .setAttribute("art", articles);
			request .getRequestDispatcher("lookArticle.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		ArtcileService service=new ArtcileService();
		boolean ifExist=service .login(username,pwd);
		if(ifExist ==true){
			try {
				//查询一次信息
				request.getSession().setAttribute("iflogin", "login");
				request.getSession().setAttribute("user", username);
				mangerArticles(request ,response);
				//request.getRequestDispatcher("mangerArticle.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				/*response.getWriter().print("<script language='javascript'>alert('用户名或密码错误，请重新登录');</script>");
				response.setHeader("refresh","1,URL=index.jsp");*/
				String message="用户名或密码错误请重新登录......";
				request.setAttribute("message",message );
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
		 		e.printStackTrace();
			}
		}
		
	}
	private void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
		Users users=new Users();
		users.setEmail(email);
		users.setPassWord(pwd);
		users.setUserName(username);
		boolean ifSucceed=false;
		ArtcileService service=new ArtcileService();
		try {
			ifSucceed=service.reginsterService(users);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ifSucceed==true){
			try {
				response.getWriter().print("<script language='javascript'>alert('注册成功');</script>");
				response.setHeader("refresh", "1,URL=index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().print("<script language='javascript'>alert('注册失败');</script>");
				response.setHeader("refresh", "1,URL=index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void addArticles(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		RegexString reg=new RegexString();
		String title=request.getParameter("title");
		String issure=request.getParameter("issure");
		String article=request.getParameter("container");
		String article_new=reg.getURL(article);
		if(article_new!=null){
			article=article_new;
		}
		String date=request.getParameter("date");
		int articleClassId=Integer.parseInt(request.getParameter("articleClass"));
		Articles art=new Articles();
		art.setArticle(article);
		art.setDate(date);
		art.setIssure(issure);
		art.setTitle(title);
		art.setArticleClass(articleClassId);
		ArtcileService service=new ArtcileService();
		boolean ifSucceed=service.addArticlesService(art);
		if(ifSucceed==true){
			try {
				response.getWriter().print("<script language='javascript'>alert('发布成功');</script>");
				response.setHeader("refresh", "1,URL=writeArticle.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().print("<script language='javascript'>alert('发布失败！！！');</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setHeader("refresh", "1,URL=writeArticle.jsp");
		}
	}
	private void mangerArticles(HttpServletRequest request, HttpServletResponse response){
		String pageNowString=request.getParameter("pageNow");
		int pageSize=18;
		int pageNow=1;
		if(pageNowString!=null){
			pageNow=Integer .parseInt(pageNowString)+1;
		}
		request .setAttribute("pageNow", pageNow);
		ArrayList<Articles> list=new ArrayList<Articles>();
		ArrayList<ArticleClassBean > list1=new ArrayList<ArticleClassBean>();
		ArtcileService service=new ArtcileService();
		int count=service.getMessageCount();
		request.setAttribute("messageCount", count);
		list=service.selectArticles(pageNow,pageSize);
		list1=service .selectArticleClass();
		request.getSession().setAttribute("articleClass", list1);
		request.setAttribute("art", list);
		try {
			request.getRequestDispatcher("mangerArticle.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

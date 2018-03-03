package Service;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.*;
import Entity.*;
public class ArtcileService {

	public boolean login(String username, String pwd) {
		// TODO Auto-generated method stub
		UserDao ud=new UserDao();
		Users user=ud.loginDao(username,pwd);
		if(user==null){
			return false;
		}else{
			return true;
		}
	}

	public boolean reginsterService(Users users) throws SQLException {
		// TODO Auto-generated method stub
		UserDao ud=new UserDao();
		int ifSucceed=ud.reginsterDao(users);
		if(ifSucceed==1){
			return true;
		}else{
	     	return false;
		}
	}

	public boolean addArticlesService(Articles art) {
		// TODO Auto-generated method stub
		ArticleDao ud=new ArticleDao();
		int ifSucceed=0;
		try {
			ifSucceed=ud.addArticleDao(art);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ifSucceed ==1){
			return true;
		}else{
		   return false;
		}
	}

	public ArrayList<Articles> selectArticles(int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		ArticleDao ud=new ArticleDao();
		ArrayList<Articles> list=new ArrayList<Articles>();
		try {
			list=(ArrayList<Articles>) ud.selectArticles(pageNow,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Articles selectArticles_1(int articleId) throws SQLException {
		// TODO Auto-generated method stub
		ArticleDao ud=new ArticleDao();
		Articles articles =ud.selectArticlesDao(articleId );
		return articles;
	}

	public boolean delArticleService(int articleId) throws SQLException {
		// TODO Auto-generated method stub
		ArticleDao ud=new ArticleDao();
		int ifSucceed=ud.delArticleDao(articleId);
		if(ifSucceed ==1){
			return true;
		}else{
		   return false;
		}
	}
	public boolean subEditorArticlesService(int id, Articles art) throws SQLException {
		// TODO Auto-generated method stub
		ArticleDao ud=new ArticleDao();
		int ifSucceed=ud.subEditorArticlesDao(id,art);
		if(ifSucceed ==1){
			return true;
		}else{
		   return false;
		}

	}

	public int getMessageCount() {
		// TODO Auto-generated method stub
		ArticleDao ud=new ArticleDao();
		int count=0;
		try {
			count=ud.getMessageCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<ArticleClassBean> selectArticleClass() {
		// TODO Auto-generated method stub
		ArticleDao ud=new ArticleDao();
		ArrayList<ArticleClassBean> list=new ArrayList<ArticleClassBean>();
		try {
			list=ud.selectArticleClassDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Articles> selectByClassService(int classId) {
		// TODO Auto-generated method stub
		ArrayList<Articles> list=new ArrayList<Articles>();
		ArticleDao ud=new ArticleDao();
		try {
			list=ud.selectByClassDao(classId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean SortArticle(int id, int i) {
		// TODO Auto-generated method stub
		ArticleDao ud=new ArticleDao();
		int ifSucceed=0;
		try {
			ifSucceed=ud.sortArticleDao(id,i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ifSucceed==2){
			return true;
		}
		return false;
	}

}

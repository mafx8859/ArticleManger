package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.tomcat.util.buf.UDecoder;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.impl.NewPooledConnection;

import Entity.ArticleClassBean;
import Entity.Articles;

public class ArticleDao {
	/***jdbc链接数据库**/
	/*private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/articlemanger";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	public  Connection getConnection () throws SQLException{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn=DriverManager .getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}*/
	/**使用c3p0数据库连接池*/
	private ComboPooledDataSource dataSource1=new ComboPooledDataSource("mysql1");//new出一个存储数据源接口对象
	private Connection conn=null;
	public synchronized final Connection getConnection () throws SQLException{
	   	conn=dataSource1.getConnection();
		return conn;
	}
	public void close(Connection conn){
		try {
			conn.close();
			dataSource1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int addArticleDao(Articles art) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		QueryRunner qRunner =new QueryRunner();
		Object[] param={art.getTitle(),art.getIssure(),art.getArticle(),art.getDate(),art.getArticleClass()};
		String sql="insert into articles (title,issure,article,date,articleClass) values(?,?,?,?,?)";
		int ifSucceed=qRunner .update(conn, sql,param);
		close(conn);
		return ifSucceed;
	}
	public List<Articles> selectArticles(int pageNow, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		Object[] param={pageNow*pageSize-pageSize,pageNow*pageSize};
		String sql="select * from articles limit ?,?";
		List<Articles> list=new ArrayList <Articles>();
		QueryRunner qRunner =new QueryRunner();
		list=qRunner .query(conn,sql, new BeanListHandler<Articles>(Articles.class),param);
		close(conn);
		return list;
	}
	public Articles selectArticlesDao(int articleId) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from articles where id="+articleId+"";
		Connection conn=getConnection();
		QueryRunner qRunner =new QueryRunner();
		Articles articles=qRunner.query(conn, sql,new BeanHandler<Articles >(Articles .class ));
		close(conn);
		return articles;
	}
	public int delArticleDao(int articleId) throws SQLException {
		// TODO Auto-generated method stub
		String sql="delete from articles where id="+articleId+"";
		Connection conn=getConnection();
		QueryRunner qRunner =new QueryRunner();
		int ifSucceed=qRunner .update(conn, sql);
		close(conn);
		return ifSucceed;
	}
	public int subEditorArticlesDao(int id, Articles art) throws SQLException {
		// TODO Auto-generated method stub
		Object[] param={art.getTitle(),art.getIssure(),art.getArticle(),art.getDate(),art.getArticleClass()};
		Connection conn=getConnection();
		QueryRunner qRunner =new QueryRunner();
		String sql="update articles set title=?,issure=?,article=?,date=?,articleClass=? where id="+id+"";
		int ifSucceed=qRunner.update(conn,sql,param);
		close(conn);
		return ifSucceed;
		
	}
	public int getMessageCount() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=getConnection();
		String sql="select * from articles";
		QueryRunner qRunner=new QueryRunner();
		List<Articles> countList=qRunner.query(connection ,sql,new BeanListHandler<Articles>(Articles .class));
		int count=countList .size();
		close(connection);
		return count;
	}
	public ArrayList<ArticleClassBean> selectArticleClassDao() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from ArticleClass where 1=1";
		Connection connection=getConnection();
		QueryRunner qRunner=new QueryRunner();
		ArrayList<ArticleClassBean> list=new ArrayList<ArticleClassBean>();
		list=(ArrayList<ArticleClassBean>) qRunner .query(connection,sql,new BeanListHandler<ArticleClassBean>(ArticleClassBean .class));
		close(connection);
		return list;
	}
	public ArrayList<Articles> selectByClassDao(int classId) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from articles where articleClass="+classId +"";
		Connection connection=getConnection();
		QueryRunner qRunner=new QueryRunner();
		ArrayList<Articles> list=new ArrayList<Articles>();
		list=(ArrayList<Articles>) qRunner .query(connection ,sql,new BeanListHandler<Articles>(Articles .class));
		close(connection);
		return list;
	}
	public int sortArticleDao(int id, int i) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=getConnection();
		QueryRunner qRunner=new QueryRunner();
		String sql1="select * from articles where id="+id+"";
		String sql2="select * from articles where id="+(id+i)+"";
		Articles articles1=new Articles();
		Articles articles2=new Articles();
		articles1=qRunner .query(connection ,sql1,new BeanHandler<Articles>(Articles.class));
		Object[] param1={articles1.getTitle(),articles1.getDate(),articles1.getArticle(),articles1.getIssure(),articles1.getArticleClass()};
		articles2=qRunner .query(connection ,sql2,new BeanHandler<Articles>(Articles.class));
		Object[] param2={articles2.getTitle(),articles2.getDate(),articles2.getArticle(),articles2.getIssure(),articles2.getArticleClass()};
		String sql3="update articles set title=?,date=?,article=?,issure=?,articleClass=? where id="+id+"";
		String sql4="update articles set title=?,date=?,article=?,issure=?,articleClass=? where id="+(id+i)+"";
		int ifSucceed1=qRunner .update(connection, sql3, param2);                                    
		int ifSucceed2=qRunner .update(connection, sql4, param1);
		close(connection);
		return ifSucceed1+ifSucceed2;
	}

}

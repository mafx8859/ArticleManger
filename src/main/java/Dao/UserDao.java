package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.impl.NewPooledConnection;

import Entity.Articles;
import Entity.Users;

public class UserDao {
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
	public Users loginDao(String username, String pwd) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn = getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QueryRunner qRunner =new QueryRunner();
		String sql="SELECT * FROM users WHERE username='"+username+"' AND pwd='"+pwd+"'";
		Users user=null;
		try {
			user = qRunner .query(conn,sql,new BeanHandler<Users>(Users.class));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn);
		}
		return user;
	}
	public int reginsterDao(Users users) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		Object[] param={users.getUserName(),users .getPassWord(),users.getEmail()};
		QueryRunner queryRunner =new QueryRunner();
		String sql="insert into users (username,pwd,email) values (?,?,?)";
		int ifSucceed=queryRunner.update(conn,sql,param);
		close(conn);
		return ifSucceed;
	}
}

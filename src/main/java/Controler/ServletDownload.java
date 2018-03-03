package Controler;

import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.io.InputStream; 
import java.io.OutputStream; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
@WebServlet("/ServletDownload")
public class ServletDownload extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	/** * @see HttpServlet#HttpServlet() */ 
	public ServletDownload() { 
	 super(); 
	// TODO Auto-generated constructor stub
    } 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 	
		String filename = request.getParameter("fileName"); //获得请求文件名 
		String road=request.getParameter("road");//得到存储路径
		System.out.println(filename); 		 			
		response.setContentType(getServletContext().getMimeType(filename));//设置文件MIME类型 	
		response.setHeader("Content-Disposition", "attachment;filename="+filename);//设置Content-Disposition 	 	
		String fullFileName = getServletContext().getRealPath(road + filename); //获取目标文件的绝对路径 		
		//读取文件 		
		InputStream in = new FileInputStream(fullFileName);//读取目标文件，通过response将目标文件写到客户端 	 		
		OutputStream out = response.getOutputStream();//写文件 		
		int b; 		
		while((b=in.read())!= -1){//read方法是读取一定字节的数据流，放回值是int类型，当到达文件末尾是返回-1 
			out.write(b); 		
		} 		 		
		in.close(); 		
		out.close(); 
		}
	/* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 	
		// TODO Auto-generated method stub 	} } 
	}
}



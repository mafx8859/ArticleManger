package Entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexString {
   private String ptn=".*href=\"(.*)\"";
   public String getURL(String s){
	   Pattern p = Pattern.compile(ptn, Pattern.DOTALL);
	   Matcher m = p.matcher(s);//获取href中“”里的内容
       if (m.find()) {
	       	String[] a=m.group(1).split("\"");//截掉title后的字符串
	       	String[] b=a[0].split("/");//截取附件的名字
	       	a[0]="ServletDownload?fileName="
	       	+b[6]+"&road="
	       	+"/ueditor/jsp/upload/file/"+b[5]+"/";//后去包含文件名和路径的HTML文本
	       	s=s.replaceAll(m.group(1), a[0]);
	        //System.out.println(a[0]);
	       	return s;
       } else {
            System.out.println("not match");
            return null;
       }
   }
}

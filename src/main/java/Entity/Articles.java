package Entity;

public class Articles {
   private int id;
   private String title;
   private String date;
   private String issure;
   private String article;
   private int articleClass;
   public int getArticleClass() {
	return articleClass;
}
public void setArticleClass(int articleClass) {
	this.articleClass = articleClass;
}
public String getTitle() {
	return title;
   }
   public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
public void setTitle(String title) {
	this.title = title;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getIssure() {
	return issure;
}
public void setIssure(String issure) {
	this.issure = issure;
}
public String getArticle() {
	return article;
}
public void setArticle(String article) {
	this.article = article;
}
}

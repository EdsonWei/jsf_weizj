package onlinebook;

import javaee.jsf.StuEntities.StudentEO;
import javaee.jsf.ejb.DBop;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

//定义托管BeanStudent，与项目的JSF页面相关联，且作用域为SessionScoped
@ManagedBean
@SessionScoped
public class BookBean {
	//定义一些属性用来存储JSF页面中的数据
	String isbn;
	String author;
	String pub;
	float price;
	String type;
    BookEO aBook;
    List<BookEO> allBook = null;
    
    //注入用于数据库操作的无状态会话Bean
    @EJB
	DBopBook op;
   
   
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BookEO getaBook() {
		return aBook;
	}
	public void setaBook(BookEO aBook) {
		this.aBook = aBook;
	}
	public List<BookEO> getAllBook() {
		return allBook;
	}
	public void setAllBook(List<BookEO> allBook) {
		this.allBook = allBook;
	}
	public DBopBook getOp() {
		return op;
	}
	public void setOp(DBopBook op) {
		this.op = op;
	}
	//用于添加一个新学生的方法
    public String AddBook() throws Exception  {
    	BookEO b= new BookEO();
    	b.setIsbn(isbn);
    	b.setAuthor(author);
    	b.setPub(pub);
    	b.setPrice(price);
    	b.setType(type);
    	//调用EJB中的 addNewStudent来实现添加操作
    	op.addNewBook(b);
    	
    	List<MyLog> logs=opuser.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("增");
        logtemp.setType("图书管理员");
        logtemp.setUser(quanju.name);
        opuser.addLog(logtemp);
    	QueryBooks();
    	//返回值为student，使页面导航到student.xhtml
    	return "/book/student";
    }
	//用于更新一个学生信息的方法
    public String UpdateBook(){
    	//调用EJB中的 updateStudent来实现添加操作
    	op.updateBook(this.aBook);
    	List<MyLog> logs=opuser.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("改");
        logtemp.setType("图书管理员");
        logtemp.setUser(quanju.name);
        opuser.addLog(logtemp);
    	//返回值为student，使页面导航到student.xhtml
    	QueryBooks();
    	return "/book/student";
    }

	//用于更新一个学生信息的方法    
    public String EditBook(){
    	//下面3行用来读取页面中通过“f:param”传递的参数，其中的“params.get”后的名字与传递的参数的名字相匹配    	
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("isbn");
		//调用EJB中的 findStudent来查找对应的学生对象
    	this.aBook=op.findBook( studentNum );
    	//返回值为editStudent，使页面导航到editStudent.xhtml
    	QueryBooks();
    	return "/book/editStudent";
    }
    //用于删除一个学生信息的方法    
    public String DeleteBook(){
    	//下面3行用来读取页面中通过“f:param”传递的参数，其中的“params.get”后的名字与传递的参数的名字相匹配    
    	FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("isbn");		
		//调用EJB中的 deleteStudent来删除对应的学生信息
		op.deleteBook(studentNum);		
		//返回值为student，使页面导航到student.xhtml
		QueryBooks();
		
		List<MyLog> logs=opuser.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("删");
        logtemp.setType("图书管理员");
        logtemp.setUser(quanju.name);
        opuser.addLog(logtemp);
    	return "/book/student";
    }    
    
  //构造JPQL实现查询的示例
    public String QueryBooks(){
    	String sql=" select c from  BookEO c  "  ;
		//调用EJB中的 executeQuery来执行构造的JPQL查询语句    	
    	allBook=op.executeQuery(sql);
		//返回值为student，使页面导航到student.xhtml
    	return "/book/student";
    }
    @EJB
	DBopUser opuser;
    public String date(){   
        String temp_str="";   
        Date dt = new Date();   
        //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");   
        temp_str=sdf.format(dt);   
        return temp_str;   
    } 
    public String QueryBooks2(){
    	String sql=" select c from  BookEO c  "  ;
		//调用EJB中的 executeQuery来执行构造的JPQL查询语句    	
    	allBook=op.executeQuery(sql);
    	
    	List<MyLog> logs=opuser.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("查");
        logtemp.setType("图书管理员");
        logtemp.setUser(quanju.name);
        opuser.addLog(logtemp);
		//返回值为student，使页面导航到student.xhtml
    	return "/book/student";
    }
}

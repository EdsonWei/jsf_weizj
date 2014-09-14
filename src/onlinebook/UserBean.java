package onlinebook;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.swing.JOptionPane;

import java.util.*;
//定义托管BeanStudent，与项目的JSF页面相关联，且作用域为SessionScoped
@ManagedBean
@SessionScoped
public class UserBean {
	//定义一些属性用来存储JSF页面中的数据
	String name;
	String pwd;
    String home;
    String school;
    String id;
    String rank;
    UserEO aUser;
    String pwda;
    List<UserEO> allUser = null;
    
    //注入用于数据库操作的无状态会话Bean
    @EJB
	DBopUser op;
   
    public void validate(FacesContext context,
 			UIComponent component,
			Object obj)throws ValidatorException {
		String password = (String) obj;
		if(password.length() < 8) {
			FacesMessage message = new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			"字符长度小于8",
			"字符长度不得小于8");
			FacesContext.getCurrentInstance().addMessage(null, message);
			throw new ValidatorException(message);
		}
		if(!password.matches(".+[0-9]+")) {
			FacesMessage message = new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			"密码必须包括字符与数字",
			"密码必须是字符加数字所组成");
			FacesContext.getCurrentInstance().addMessage(null, message);
			throw new ValidatorException(message);
		}

    }

	//用于添加一个新学生的方法
    public String AddUser() throws Exception  {
    	UserEO newUser= new UserEO();
    	newUser.setName(this.name);
    	newUser.setPwd(this.pwd);
    	newUser.setHome(this.home);
    	newUser.setSchool(this.school);
    	newUser.setId(this.id);
    	newUser.setRank("0");
    	List<UserEO> temp=op.getAllUser();
    	Iterator<UserEO> it=temp.iterator();
    	if(!pwd.equals(pwda))
    		return "register_buyizhi";
    	int ff=1;
    	while(it.hasNext()){
    		UserEO tmp=it.next();
    		if(tmp.getName().equals(this.name)) {
    			ff=-1;
    			break;
    		}
    	}
    	//调用EJB中的 addNewStudent来实现添加操作
    	if(ff==1)
    	{
    		
    		op.addNewUser(newUser);return "login";
    	}
    	else{
    	//返回值为student，使页面导航到student.xhtml
    		return "register_again";
    	}
    }
    public String date(){   
        String temp_str="";   
        Date dt = new Date();   
        //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");   
        temp_str=sdf.format(dt);   
        return temp_str;   
    } 


    public String gaiUser() throws Exception  {
    	List<UserEO> temp=op.getUserById(this.name);
		Iterator<UserEO> it=temp.iterator();
		if(!it.hasNext()) return "wangji_bucunzai";
		UserEO next=it.next();
    	UserEO newUser= new UserEO();
    	if(!next.getHome().equals(home)||!next.getSchool().equals(school)||!next.getId().endsWith(id)) return "wangji_sb";
    	if(!pwd.equals(pwda))
    		return "wangji_buyizhi";
    	newUser.setName(this.name);
    	newUser.setPwd(this.pwd);
    	newUser.setHome(this.home);
    	newUser.setSchool(this.school);
    	newUser.setId(this.id);
    	newUser.setRank("0");
    	op.gaiNewUser(newUser);return "login";
    	
    }
    public String getPwda() {
		return pwda;
	}
	public void setPwda(String pwda) {
		this.pwda = pwda;
	}
	

	public String getName() {
		return name;
	}
	 @EJB
		DanLi opdan;
	 int zhong;
	 int cu;
	 public
	 UserBean(){
		 cu=0;
		 zhong=0;
	 }
	
	public String VerifyPassword(){
		List<UserEO> temp=op.getUserById(this.name);
		Iterator<UserEO> it=temp.iterator();
				
		if(!it.hasNext()) {return "login_sb";}		
		if(this.pwd.equals(temp.get(0).getPwd())) {CarBean.biao=name;
		opdan.setCu(opdan.getCu()+1);
		opdan.setZhong(opdan.getZhong()+1);
		cu=opdan.getCu();
		zhong=opdan.getZhong();
		return "success";}
		else {return "login_sb";}			
	}
	public DanLi getOpdan() {
		return opdan;
	}

	public void setOpdan(DanLi opdan) {
		this.opdan = opdan;
	}

	public int getZhong() {
		return zhong;
	}

	public void setZhong(int zhong) {
		this.zhong = zhong;
	}

	public int getCu() {
		return cu;
	}

	public void setCu(int cu) {
		this.cu = cu;
	}

	public String tui(){
		opdan.setCu(opdan.getCu()-1);
		cu=opdan.getCu();
		return "login";	
	}
	//用于更新一个学生信息的方法    
    public String EditUser(){
    	//下面3行用来读取页面中通过“f:param”传递的参数，其中的“params.get”后的名字与传递的参数的名字相匹配    
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("name");
		//调用EJB中的 findStudent来查找对应的学生对象
		List<UserEO> temp=op.getUserById(studentNum);
		Iterator<UserEO> it=temp.iterator();
    	this.aUser=it.next();
    	//返回值为editStudent，使页面导航到editStudent.xhtml
    	QueryUsers();
    	return "/admin/editStudent";
    }
  //用于更新一个学生信息的方法    
    public String EditUserss(){
    	//下面3行用来读取页面中通过“f:param”传递的参数，其中的“params.get”后的名字与传递的参数的名字相匹配    
		
    	return "/admin/success";
    }
    //用于删除一个学生信息的方法    
    public String DeleteUser(){
    	//下面3行用来读取页面中通过“f:param”传递的参数，其中的“params.get”后的名字与传递的参数的名字相匹配    
    	FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("name");		
		//调用EJB中的 deleteStudent来删除对应的学生信息
		op.deleteUser(studentNum);	
		
		List<MyLog> logs=op.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("删");
        logtemp.setType("用户管理员");
        logtemp.setUser(quanju.name);
        op.addLog(logtemp);
		//返回值为student，使页面导航到student.xhtml
		QueryUsers();
    	return "/admin/student";
    }    
    
  //构造JPQL实现查询的示例
    public String QueryUsers(){
    	String sql=" select c from  UserEO c   " ;
		//调用EJB中的 executeQuery来执行构造的JPQL查询语句    	
    	allUser=op.executeQuery(sql);
		//返回值为student，使页面导航到student.xhtml
    	
    	return "/admin/student";
    }
    public String QueryUsers2(){
    	String sql=" select c from  UserEO c   " ;
		//调用EJB中的 executeQuery来执行构造的JPQL查询语句    	
    	allUser=op.executeQuery(sql);
		//返回值为student，使页面导航到student.xhtml
    	List<MyLog> logs=op.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("查");
        logtemp.setType("用户管理员");
        logtemp.setUser(quanju.name);
        op.addLog(logtemp);
    	return "/admin/student";
    }
	public void setName(String name) {
		this.name = name;
	}
	//用于更新一个学生信息的方法
    public String UpdateUser(){
    	//调用EJB中的 updateStudent来实现添加操作
    	op.updateUser(this.aUser);
    	
    	List<MyLog> logs=op.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
        logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("改");
        logtemp.setType("用户管理员");
        logtemp.setUser(quanju.name);
        op.addLog(logtemp);
    	//返回值为student，使页面导航到student.xhtml
    	QueryUsers();
    	return "/admin/student";
    }

	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getHome() {
		return home;
	}


	public void setHome(String home) {
		this.home = home;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	public UserEO getaUser() {
		return aUser;
	}


	public void setaUser(UserEO aUser) {
		this.aUser = aUser;
	}


	public List<UserEO> getAllUser() {
		return allUser;
	}


	public void setAllUser(List<UserEO> allUser) {
		this.allUser = allUser;
	}


	public DBopUser getOp() {
		return op;
	}


	public void setOp(DBopUser op) {
		this.op = op;
	}
	
}

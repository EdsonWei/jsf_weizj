package onlinebook;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.swing.JOptionPane;


//定义托管BeanStudent，与项目的JSF页面相关联，且作用域为SessionScoped
@ManagedBean
@RequestScoped
public class AdminBean {
	//定义一些属性用来存储JSF页面中的数据
	String name;
	String pwd;
    String pwda;
    
    //注入用于数据库操作的无状态会话Bean
    @EJB
	DBopAdmin op;
   
    public String getPwda() {
		return pwda;
	}
	public void setPwda(String pwda) {
		this.pwda = pwda;
	}
	public String getName() {
		return name;
	}
	public String VerifyPassword(){
		List<AdminEO> temp=op.getAdminById(this.name);
		Iterator<AdminEO> it=temp.iterator();
		if(!it.hasNext()) {return "login_admin_sb";}		
		if(this.pwd.equals(temp.get(0).getPwd())) return "/admin/zhong";
		else {return "login_admin_sb";}			
	}
	
	public String VerifyPasswordbook(){
		List<BookAdminEO> temp=op.getAdminByIdbook(this.name);
		quanju.name=name;
		Iterator<BookAdminEO> it=temp.iterator();
		if(!it.hasNext()) {return "bookadminsb";}		
		if(this.pwd.equals(temp.get(0).getPwd())) return "/admin/student";
		else {return "bookadminsb";}			
	}
	public String VerifyPassworduser(){
		List<UserAdminEO> temp=op.getAdminByIduser(this.name);
		quanju.name=name;
		Iterator<UserAdminEO> it=temp.iterator();
		if(!it.hasNext()) {return "useradminsb";}		
		if(this.pwd.equals(temp.get(0).getPwd())) return "/book/student";
		else {return "useradminsb";}			
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public DBopAdmin getOp() {
		return op;
	}


	public void setOp(DBopAdmin op) {
		this.op = op;
	}
	
}

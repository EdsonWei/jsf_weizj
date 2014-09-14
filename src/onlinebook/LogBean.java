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
public class LogBean {
	//定义一些属性用来存储JSF页面中的数据
	String date;
	String type;
	 @EJB
		LogOp op;
	 @EJB
		DBopUser opp;
	 @EJB
		DBop oop;
	 List<MyLog> allLog = null;
	 public String QueryLogs(){
	    	allLog=opp.getAllLog();
			//返回值为student，使页面导航到student.xhtml
	    	return "/log/student";
	    }
	 public String QueryLog(){
	    	String sql=" select c from  MyLog c  where c.type like '%" + this.type + "%' and date like '%" + this.date +"%' " ;
			//调用EJB中的 executeQuery来执行构造的JPQL查询语句    	
	    	allLog=oop.executeQuerylog(sql);
			//返回值为student，使页面导航到student.xhtml
	    	return "/log/student";
	    }
	public LogOp getOp() {
		return op;
	}
	public void setOp(LogOp op) {
		this.op = op;
	}
	public DBopUser getOpp() {
		return opp;
	}
	public void setOpp(DBopUser opp) {
		this.opp = opp;
	}
	public List<MyLog> getAllLog() {
		return allLog;
	}
	public void setAllLog(List<MyLog> allLog) {
		this.allLog = allLog;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}

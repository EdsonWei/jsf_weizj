package javaee.jsf;

import javaee.jsf.StuEntities.StudentEO;
import javaee.jsf.ejb.DBop;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

//定义托管BeanStudent，与项目的JSF页面相关联，且作用域为SessionScoped
@ManagedBean
@SessionScoped
public class Student {
	//定义一些属性用来存储JSF页面中的数据
	int stu_Num;
	String stu_Name;
    int stu_TeamNum;
    int stu_Age;
    String stu_Major;
    String stu_Gender;
    StudentEO aStudent;
    List<StudentEO> allStudent = null;
    
    //注入用于数据库操作的无状态会话Bean
    @EJB
	DBop op;
   
    public int getStu_Num() {
		return stu_Num;
	}

	public void setStu_Num(int stu_Num) {
		this.stu_Num = stu_Num;
	}

	public String getStu_Name() {
		return stu_Name;
	}

	public void setStu_Name(String stu_Name) {
		this.stu_Name = stu_Name;
	}

	public int getStu_TeamNum() {
		return stu_TeamNum;
	}

	public void setStu_TeamNum(int stu_TeamNum) {
		this.stu_TeamNum = stu_TeamNum;
	}

	public int getStu_Age() {
		return stu_Age;
	}

	public void setStu_Age(int stu_Age) {
		this.stu_Age = stu_Age;
	}

	public String getStu_Major() {
		return stu_Major;
	}

	public void setStu_Major(String stu_Major) {
		this.stu_Major = stu_Major;
	}

	public String getStu_Gender() {
		return stu_Gender;
	}

	public void setStu_Gender(String stu_Gender) {
		this.stu_Gender = stu_Gender;
	}

	public StudentEO getaStudent() {
		return aStudent;
	}
	public void setaStudent(StudentEO aStudent) {
		this.aStudent = aStudent;
	}
	public List<StudentEO> getAllStudent() {
		return this.allStudent;
	}
	
	public void setAllStudent(List<StudentEO> allStudent) {
		this.allStudent = allStudent;
	}  
	
	//用于添加一个新学生的方法
    public String AddStudent() throws Exception  {
    	StudentEO newStu= new StudentEO();
    	newStu.setStudentName(this.stu_Name);
    	newStu.setStudentNum(this.stu_Num);
    	newStu.setTeamNum(this.stu_TeamNum);
    	newStu.setAge(this.stu_Age);
    	newStu.setGender(this.stu_Gender);
    	newStu.setMajor(this.stu_Major);
    	//调用EJB中的 addNewStudent来实现添加操作
    	op.addNewStudent(newStu);
    	//返回值为student，使页面导航到student.xhtml
    	return "student";
    }
	//用于更新一个学生信息的方法
    public String UpdateStudent(){
    	//调用EJB中的 updateStudent来实现添加操作
    	op.updateStudent(this.aStudent);
    	//返回值为student，使页面导航到student.xhtml
    	return "student";
    }

	//用于更新一个学生信息的方法    
    public String EditStudent(){
    	//下面3行用来读取页面中通过“f:param”传递的参数，其中的“params.get”后的名字与传递的参数的名字相匹配    	
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("studentNum");
		//调用EJB中的 findStudent来查找对应的学生对象
    	this.aStudent=op.findStudent( Integer.valueOf(studentNum) );
    	//返回值为editStudent，使页面导航到editStudent.xhtml
    	return "editStudent";
    }
    //用于删除一个学生信息的方法    
    public String DeleteStudent(){
    	//下面3行用来读取页面中通过“f:param”传递的参数，其中的“params.get”后的名字与传递的参数的名字相匹配    
    	FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("studentNum");		
		//调用EJB中的 deleteStudent来删除对应的学生信息
		op.deleteStudent(studentNum);		
		//返回值为student，使页面导航到student.xhtml
    	return "student";
    }    
    
  //构造JPQL实现查询的示例
    public String QueryStudents(){
    	String sql=" select c from  StudentEO c  where c.studentName like '%" + this.stu_Name + "%' and studentNum like '%" + this.stu_Num +"%' " ;
		//调用EJB中的 executeQuery来执行构造的JPQL查询语句    	
    	allStudent=op.executeQuery(sql);
		//返回值为student，使页面导航到student.xhtml
    	return "student";
    }
}

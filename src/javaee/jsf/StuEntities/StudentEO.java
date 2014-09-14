package javaee.jsf.StuEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
//实体Bean StudentEO用来构造与数据库中的表students的映射
@Table(name="students")
@NamedQueries({
	//在此定义用于此实体类的常用的查询
    @NamedQuery(name = "findAllStudent",query = "SELECT l "
    + "FROM StudentEO l")
    , @NamedQuery(name = "findStudentOrdeById", query = "SELECT l FROM StudentEO l "
    + " ORDER BY l.studentNum")
    , @NamedQuery(name = "findStudentByName", query = "SELECT DISTINCT l FROM StudentEO l "
    + " WHERE l.studentName = :studentName")
    , @NamedQuery(name = "findStudentByID", query = "SELECT DISTINCT l FROM StudentEO l "
    + " WHERE l.studentNum = :studentNum")
})
public class StudentEO implements Serializable {
	private static final long serialVersionUID = 1L;
	//@Id指明关键字，每个实体类都必须要有
	@Id
	@Column(name="studentNum")
	private int studentNum;

	@Column(name="studentName")
	private String studentName;

	@Column(name="teamNum")
	private int teamNum;

	@Column(name="age")
	private int age;

	@Column(name="gender")
	private String gender;

	@Column(name="major")
	private String major;

	public StudentEO() {
	}
	public int getStudentNum() {
		return this.studentNum;
	}
	public void setStudentNum(int StudentNum) {
		this.studentNum = StudentNum;
	}
	public String getStudentName() {
		return this.studentName;
	}
	public void setStudentName(String StuName) {
		this.studentName = StuName;
	}
	public int getTeamNum() {
		return this.teamNum;
	}
	public void setTeamNum(int TeamNum) {
		this.teamNum = TeamNum;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int Age) {
		this.age = Age;
	}
	public String getGender() {
		return this.gender;
	}
	public void setGender(String Gender) {
		this.gender = Gender;
	}
	public String getMajor() {
		return this.major;
	}
	public void setMajor(String Major) {
		this.major = Major;
	}
}
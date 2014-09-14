package onlinebook;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
//实体Bean StudentEO用来构造与数据库中的表students的映射
@Table(name="admin")
@NamedQueries({
	//在此定义用于此实体类的常用的查询
    @NamedQuery(name = "findAdminById", query = "SELECT l FROM AdminEO l "
    + " where l.user=:name")
    //, @NamedQuery(name = "findStudentByName", query = "SELECT DISTINCT l FROM StudentEO l "
    //+ " WHERE l.studentName = :studentName")
    //, @NamedQuery(name = "findStudentByID", query = "SELECT DISTINCT l FROM StudentEO l "
    //+ " WHERE l.studentNum = :studentNum")
})
public class AdminEO implements Serializable {
	private static final long serialVersionUID = 1L;
	//@Id指明关键字，每个实体类都必须要有
	@Id
	@Column(name="user")
	private String user;

	@Column(name="pwd")
	private String pwd;

	
	public String getUser() {
		return user;
	}

	public void setUser(String name) {
		this.user = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
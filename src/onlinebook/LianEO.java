package onlinebook;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
//实体Bean StudentEO用来构造与数据库中的表students的映射
@Table(name="lian")
@IdClass(pk.class)
/*
@NamedQueries({
	//在此定义用于此实体类的常用的查询
    @NamedQuery(name = "findAllBook",query = "SELECT l "
    + "FROM BookEO l")
    , @NamedQuery(name = "findBookByIsbn", query = "SELECT l FROM BookEO l "
    + " WHERE l.isbn = :isbn")
    , @NamedQuery(name = "findBookByType", query = "SELECT DISTINCT l FROM BookEO l "
    + " WHERE l.type = :type")
})
*/
public class LianEO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//@Id指明关键字，每个实体类都必须要有
	@Id
	@Column(name="id")
	private String id;
	@Id
	@Column(name="isbn")
	private String isbn;

	
	@Column(name="count")
	private int count;

	
	public LianEO() {
	}




	

	
	public String getId() {
		return id;
	}







	public void setId(String id) {
		this.id = id;
	}







	public String getIsbn() {
		return isbn;
	}







	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}







	public int getCount() {
		return count;
	}







	public void setCount(int count) {
		this.count = count;
	}







	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
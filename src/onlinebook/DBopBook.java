package onlinebook;

import java.util.List;
import javaee.jsf.StuEntities.StudentEO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//构造了一个 无状态会话Bean，实现实体的各种操作
@Stateless
public class DBopBook {
	//@PersistenceContext用来以标注的方式注入一个实体管理器，其中的“jsf_example”是在persistence.xml中定义的持久化单元的名字
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public DBopBook() {
        
    }
    //运行在实体BeanStudentEO中定义的查询"findAllStudent"来取出所有的学生数据           	
	public List <BookEO> getAllBook(){
		@SuppressWarnings("unchecked")
        List <BookEO> Students= em.createNamedQuery("findAllBook")
        							.getResultList();
        return Students;
	}	
	//运行在实体BeanStudentEO中定义的查询"findStudentByID"来按学号查找一个学生	
	public BookEO findBook(String isbn){
		@SuppressWarnings("unchecked")
		List <BookEO> tStuList= em.createNamedQuery("findBookByIsbn")
    	.setParameter("isbn", isbn).getResultList();
		return tStuList.get(0);    	
	}	
	//添加一个新的学生信息
	public void addNewBook(BookEO newStu) {
		em.persist(newStu);
	}
	//删除一个学生信息	
	public void deleteBook(String isbn){
		BookEO aStu= findBook( isbn );
		em.remove(aStu);
	}
	//更新一个学生信息	
	public void updateBook(BookEO Stu){
		em.merge(Stu);
	}
	//执行JPQL的查询语句来查找学生
	public List <BookEO> executeQuery(String sql){
		Query query = em.createQuery(sql);
		@SuppressWarnings("unchecked")
    	List <BookEO> Students= query.getResultList();
    	return Students;
	}	
}

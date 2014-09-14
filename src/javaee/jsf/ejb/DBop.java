package javaee.jsf.ejb;

import java.util.List;

import javaee.jsf.StuEntities.StudentEO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import onlinebook.MyLog;

//构造了一个 无状态会话Bean，实现实体的各种操作
@Stateless
public class DBop {
	//@PersistenceContext用来以标注的方式注入一个实体管理器，其中的“jsf_example”是在persistence.xml中定义的持久化单元的名字
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public DBop() {
        
    }
    //运行在实体BeanStudentEO中定义的查询"findAllStudent"来取出所有的学生数据           	
	public List <StudentEO> getAllStudent(){
		@SuppressWarnings("unchecked")
        List <StudentEO> Students= em.createNamedQuery("findAllStudent")
        							.getResultList();
        return Students;
	}	
	//运行在实体BeanStudentEO中定义的查询"findStudentByID"来按学号查找一个学生	
	public StudentEO findStudent(Integer pStuID){
		@SuppressWarnings("unchecked")
		List <StudentEO> tStuList= em.createNamedQuery("findStudentByID")
    	.setParameter("studentNum", pStuID).getResultList();
		return tStuList.get(0);    	
	}	
	//添加一个新的学生信息
	public void addNewStudent(StudentEO newStu) {
		em.persist(newStu);
	}
	//删除一个学生信息	
	public void deleteStudent(String studentNum){
		StudentEO aStu= findStudent( Integer.valueOf(studentNum) );
		em.remove(aStu);
	}
	//更新一个学生信息	
	public void updateStudent(StudentEO Stu){
		em.merge(Stu);
	}
	//执行JPQL的查询语句来查找学生
	public List <StudentEO> executeQuery(String sql){
		Query query = em.createQuery(sql);
		@SuppressWarnings("unchecked")
    	List <StudentEO> Students= query.getResultList();
    	return Students;
	}	
	public List <MyLog> executeQuerylog(String sql){
		Query query = em.createQuery(sql);
		@SuppressWarnings("unchecked")
    	List <MyLog> Students= query.getResultList();
    	return Students;
	}	
}

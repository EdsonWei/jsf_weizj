package onlinebook;

import java.util.List;

import javaee.jsf.StuEntities.StudentEO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//构造了一个 无状态会话Bean，实现实体的各种操作
@Stateless
public class DBopUser {
	//@PersistenceContext用来以标注的方式注入一个实体管理器，其中的“jsf_example”是在persistence.xml中定义的持久化单元的名字
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public DBopUser() {
        
    }
    //运行在实体BeanStudentEO中定义的查询"findAllStudent"来取出所有的学生数据           	
	public List <UserEO> getAllUser(){
		@SuppressWarnings("unchecked")
        List <UserEO> Users= em.createNamedQuery("findAllUser")
        							.getResultList();
        return Users;
	}	
	public List <MyLog> getAllLog(){
		@SuppressWarnings("unchecked")
        List <MyLog> Users= em.createNamedQuery("findAllLog")
        							.getResultList();
        return Users;
	}	
	public List <UserEO> getUserById(String name){
		@SuppressWarnings("unchecked")
        List <UserEO> Users= em.createNamedQuery("findUserById").setParameter("name", name)
        							.getResultList();
        return Users;
	}	
	//添加一个新的学生信息
	public void addNewUser(UserEO newUser) {
		em.persist(newUser);
	}
	public void gaiNewUser(UserEO newUser) {
		em.merge(newUser);
	}
	public void updateUser(UserEO Stu){
		em.merge(Stu);
	}
	public List <UserEO> executeQuery(String sql){
		Query query = em.createQuery(sql);
    	List <UserEO> Users= query.getResultList();
    	return Users;
	}	
	//运行在实体BeanStudentEO中定义的查询"findStudentByID"来按学号查找一个学生	
		public UserEO findUser(String name){
			@SuppressWarnings("unchecked")
			List <UserEO> tStuList= em.createNamedQuery("findUserById")
	    	.setParameter("name", name).getResultList();
			return tStuList.get(0);    	
		}	
		//删除一个学生信息	
		public void deleteUser(String name){
			UserEO aStu= findUser( name );
			em.remove(aStu);
		}
		public void addLog(MyLog x)
		{
			em.persist(x);
		}
		//更新一个学生信息	
		//public void updateStudent(StudentEO Stu){
		//	em.merge(Stu);
		//}
}

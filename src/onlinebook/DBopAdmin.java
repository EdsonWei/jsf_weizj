package onlinebook;

import java.util.List;

import javaee.jsf.StuEntities.StudentEO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//构造了一个 无状态会话Bean，实现实体的各种操作
@Stateless
public class DBopAdmin {
	//@PersistenceContext用来以标注的方式注入一个实体管理器，其中的“jsf_example”是在persistence.xml中定义的持久化单元的名字
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public DBopAdmin() {
        
    }
    //运行在实体BeanStudentEO中定义的查询"findAllStudent"来取出所有的学生数据           	
	public List <AdminEO> getAdminById(String name){
		@SuppressWarnings("unchecked")
        List <AdminEO> Admins= em.createNamedQuery("findAdminById").setParameter("name", name)
        							.getResultList();
        return Admins;
	}	
	public List <BookAdminEO> getAdminByIdbook(String name){
		@SuppressWarnings("unchecked")
        List <BookAdminEO> Admins= em.createNamedQuery("findAdminByIdbook").setParameter("name", name)
        							.getResultList();
        return Admins;
	}	
	public List <UserAdminEO> getAdminByIduser(String name){
		@SuppressWarnings("unchecked")
        List <UserAdminEO> Admins= em.createNamedQuery("findAdminByIduser").setParameter("name", name)
        							.getResultList();
        return Admins;
	}	
	
	
}

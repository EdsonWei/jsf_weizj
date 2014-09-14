package onlinebook;

import java.util.List;
import javaee.jsf.StuEntities.StudentEO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//构造了一个 无状态会话Bean，实现实体的各种操作
@Stateless
public class LogOp {
	//@PersistenceContext用来以标注的方式注入一个实体管理器，其中的“jsf_example”是在persistence.xml中定义的持久化单元的名字
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public LogOp() {
        
    }
    
   
}

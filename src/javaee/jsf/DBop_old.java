package javaee.jsf;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javaee.jsf.StuEntities.StudentEO;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class DBop_old {
	
	private EntityManagerFactory emf= Persistence.createEntityManagerFactory("jsf_example") ;
//	@PersistenceUnit(unitName="SchoolManage")
//	EntityManagerFactory emf;
	private EntityManager em=emf.createEntityManager();
	
//	@Resource
//	private UserTransaction utx;	
	           	
	public List <StudentEO> getAllStudent(){
			@SuppressWarnings("unchecked")
        	List <StudentEO> Students= em.createNamedQuery("findAllStudent")
                     .getResultList();
        	return Students;
	}	
	
	public StudentEO findStudent(Integer pStuID){
		@SuppressWarnings("unchecked")
		List <StudentEO> tStuList= em.createNamedQuery("findStudentByID")
    	.setParameter("studentNum", pStuID).getResultList();
		return tStuList.get(0);    	
}	
	

	public void addNewStudent(StudentEO newStu)  throws Exception  {
		EntityTransaction etx;
		etx=em.getTransaction();
		etx.begin();
//		em.joinTransaction();
		boolean Committed=false;
		try{
		em.persist(newStu);
		etx.commit();
		Committed=true;
		}
		finally{
			if(!Committed){
				etx.rollback();
			}
		}
	}
	
	public void deleteStudent(StudentEO Stu){
		EntityTransaction etx;
		etx=em.getTransaction();
		etx.begin();
//		em.joinTransaction();
		boolean Committed=false;
		try{
		em.remove(Stu);
		etx.commit();
		Committed=true;
		}
		finally{
			if(!Committed){
				etx.rollback();
			}
		}
	}
	
	public void updateStudent(StudentEO Stu){
		EntityTransaction etx;
		etx=em.getTransaction();
		etx.begin();
//		em.joinTransaction();
		boolean Committed=false;
		try{
		em.merge(Stu);
		etx.commit();
		Committed=true;
		}
		finally{
			if(!Committed){
				etx.rollback();
			}
		}	}
}
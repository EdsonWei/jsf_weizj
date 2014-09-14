package onlinebook;

import java.util.List;

import javaee.jsf.StuEntities.StudentEO;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
@Singleton
public class DanLi {
	
	
    int zhong;
    int cu;
    public void jian()
    {
    	this.setCu(cu--);
    }
	public int getZhong() {
		return zhong;
	}
	public void setZhong(int zhong) {
		this.zhong = zhong;
	}
	public int getCu() {
		return cu;
	}
	public void setCu(int cu) {
		this.cu = cu;
	}
}

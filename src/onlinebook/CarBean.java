package onlinebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javaee.jsf.StuEntities.StudentEO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

//构造了一个 无状态会话Bean，实现实体的各种操作
@Stateful
@ManagedBean
@SessionScoped
public class CarBean {
	public static String biao;
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<BookEO> getAllBooks() {
		return allBooks;
	}

	public void setAllBooks(List<BookEO> allBooks) {
		this.allBooks = allBooks;
	}

	public List<CarItem> getItems() {
		return items;
	}

	public void setItems(List<CarItem> items) {
		this.items = items;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public DBopBook getOp() {
		return op;
	}

	public void setOp(DBopBook op) {
		this.op = op;
	}
	//@PersistenceContext用来以标注的方式注入一个实体管理器，其中的“jsf_example”是在persistence.xml中定义的持久化单元的名字
	@PersistenceUnit(unitName = "jsf_example")
	private EntityManagerFactory emf;
	private EntityManager em;
	List<BookEO> allBooks;
    List<CarItem> items;
    String isbn;
    public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	int count;
    
    @EJB
   	DBopBook op;
    @EJB
   	OpZhong opp;
    public CarBean() {
        items=new ArrayList<CarItem>();
        allOrder=new ArrayList<OrderEO>();
        allLian=new ArrayList<LianEO>();
        allXiang=new ArrayList<Xiang>();
    }
    @PostConstruct
    public void init()
    {
    	em=emf.createEntityManager();
    	
    }
    
	//运行在实体BeanStudentEO中定义的查询"findStudentByID"来按学号查找一个学生	
	public BookEO findBook(String isbn){
		@SuppressWarnings("unchecked")
		List <BookEO> tStuList= em.createNamedQuery("findBookByIsbn")
    	.setParameter("isbn", isbn).getResultList();
		return tStuList.get(0);    	
	}	
	//添加一个新的学生信息
	public String addNewBooka() {
		
		BookEO temp=findBook(isbn);
		CarItem tmp=new CarItem();
		tmp.setAuthor(temp.getAuthor());
		tmp.setCount(count);
		tmp.setIsbn(temp.getIsbn());
		tmp.setPrice(temp.getPrice());
		tmp.setPub(temp.getPub());
		tmp.setType(temp.getPub());
		items.add(tmp);
		
		return "/mai/student";
		
	}
	//添加一个新的学生信息
		public String addNewBookaa() {
			
			BookEO temp=findBook(isbn);
			CarItem tmp=new CarItem();
			tmp.setAuthor(temp.getAuthor());
			tmp.setCount(count);
			tmp.setIsbn(temp.getIsbn());
			tmp.setPrice(temp.getPrice());
			tmp.setPub(temp.getPub());
			tmp.setType(temp.getPub());
			items.add(tmp);
			
			return "/car/content";
			
		}
	public String addNewBookav() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		isbn= params.get("isbn");		
		return "/mai/editStudent";
		
	}
	public String addNewBookavv() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		isbn= params.get("isbn");		
		return "/mai/editStudent2";
		
	}
	//删除一个学生信息	
	public String deleteBooka(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String isbn= params.get("isbn");
		int i;
		for(i=0;i<items.size();i++)
			if(isbn.equals(items.get(i).getIsbn())) break;	
		items.remove(i);
		return "/car/content";
	}
	//更新一个学生信息	
	public String updateBooka(){
		
		int i;
		for(i=0;i<items.size();i++)
			if(isbn.equals(items.get(i).getIsbn())) items.get(i).setCount(count);	
		return "/car/content";
	}
	//构造JPQL实现查询的示例
    public String QueryBooks(){
    	String sql=" select c from  BookEO c  "  ;
		//调用EJB中的 executeQuery来执行构造的JPQL查询语句    	
    	allBooks=op.executeQuery(sql);
		//返回值为student，使页面导航到student.xhtml
    	return "/mai/student";
    }
    String hao;
    String add;
    String pho;
    float zhe=1;
    float total=0;
    String rank;
    @EJB
	DBopUser oop;
    List<OrderEO> allOrder; 
    List<LianEO> allLian;
    List<Xiang> allXiang;
    @EJB
   	DBopBook top;
    public String getAllItem()
    {
    	FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String numm= params.get("num");
    	allLian=em.createNamedQuery("findAllItem").setParameter("id", numm)
    	    	.getResultList();
    	allXiang=new ArrayList<Xiang>();
    	for(int i=0;i<allLian.size();i++)
    	{
    		LianEO liantemp=allLian.get(i);
    		String iisbn=liantemp.getIsbn();
    		BookEO booktemp=top.findBook(iisbn);
    		Xiang itemtemp=new Xiang();
    		itemtemp.setIsbn(iisbn);
    		itemtemp.setAuthor(booktemp.getAuthor());
    		itemtemp.setPub(booktemp.getPub());
    		itemtemp.setCount(liantemp.getCount());
    		itemtemp.setPrice(booktemp.getPrice());
    		itemtemp.setType(booktemp.getType());
    		allXiang.add(itemtemp);
    	}
    	return "/order/editStudent";
    }
    public static String getBiao() {
		return biao;
	}

	public static void setBiao(String biao) {
		CarBean.biao = biao;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public OpZhong getOpp() {
		return opp;
	}

	public void setOpp(OpZhong opp) {
		this.opp = opp;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public DBopUser getOop() {
		return oop;
	}

	public void setOop(DBopUser oop) {
		this.oop = oop;
	}

	public List<OrderEO> getAllOrder() {
		return allOrder;
	}

	public void setAllOrder(List<OrderEO> allOrder) {
		this.allOrder = allOrder;
	}

	public List<LianEO> getAllLian() {
		return allLian;
	}

	public void setAllLian(List<LianEO> allLian) {
		this.allLian = allLian;
	}

	public DBopBook getTop() {
		return top;
	}

	public void setTop(DBopBook top) {
		this.top = top;
	}

	public void setAllXiang(List<Xiang> allItem) {
		this.allXiang = allItem;
	}

	public List<Xiang> getAllXiang() {
		return allXiang;
	}

	public String jie()
    {
    	
    	
		List <OrderEO> t= em.createNamedQuery("findAllOrder")
    	.getResultList();
    	Integer ii=t.size()+1;
    	hao=ii.toString();
    	float sum=0;
    	for(int j=0;j<t.size();j++) sum+=Float.valueOf(t.get(j).getTotal());
    	total=0;
    	for(int i=0;i<items.size();i++)
    	{
    		CarItem iii=items.get(i);
    		total+=iii.getCount()*iii.getPrice();
    		LianEO liantemp=new LianEO();
    		liantemp.setIsbn(items.get(i).getIsbn());
    		liantemp.setId(hao);
    		liantemp.setCount(iii.getCount());
    		opp.jian1(liantemp);
    		
    	}
    	items.clear();
    	sum+=total;
    	if(t.size()==0) {total=total;rank="3";zhe=1;}
    	if(sum<5000&&sum>0&&t.size()>=1) {total*=0.98;rank="3";zhe=(float)0.98;}
    	if(sum>=5000&&sum<10000&&t.size()>=1) {total*=0.9;zhe=(float)0.9;rank="2";}
    	if(sum>=10000&&t.size()>=1) {total*=0.8; zhe=(float)0.8;rank="1";}
  
    	UserEO utemp=oop.findUser(biao);
    	utemp.setRank(rank);
    	oop.gaiNewUser(utemp);
    	
    	OrderEO otemp=new OrderEO();
    	otemp.setNum(hao);
    	otemp.setAd(add);
    	otemp.setPh(pho);
    	otemp.setZhe(zhe);
    	otemp.setTotal(total);
    	opp.jian2(otemp);
    	allOrder.add(otemp);
		return "/success";    	
    	
    }

	public String getHao() {
		return hao;
	}

	public void setHao(String hao) {
		this.hao = hao;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getPho() {
		return pho;
	}

	public void setPho(String pho) {
		this.pho = pho;
	}

	public float getZhe() {
		return zhe;
	}

	public void setZhe(float zhe) {
		this.zhe = zhe;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
}

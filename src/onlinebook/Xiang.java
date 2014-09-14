package onlinebook;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


//实体Bean StudentEO用来构造与数据库中的表students的映射



public class Xiang implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	public Xiang() {
	}

	String isbn;
	String author;
	String pub;
	float price;
	String type;
    int count;

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
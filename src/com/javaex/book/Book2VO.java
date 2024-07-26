package com.javaex.book;

public class Book2VO {

	//필드
	private int id;
	private String title;
	private String pubs;
	private String pub_date;
	private int author_id;
	private String name;
	private String desc;
	
	
	//생성자
	public Book2VO() {
		
	}
	public Book2VO(int id, String title, String pubs, String pub_date, int author_id, String name, String desc) {
		super();
		this.id = id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
		this.name = name;
		this.desc = desc;
	}
	
	
	//메서드 -gs
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	//메서드 일반
	
	@Override
	public String toString() {
		return "Book2VO [id=" + id + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date + ", author_id="
				+ author_id + ", name=" + name + ", desc=" + desc + "]";
	}
	
	
	
	
	
	
	
	
	
}

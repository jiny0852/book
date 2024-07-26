package com.javaex.author;

public class AuthorVO {

	//필드
	private int id;
	private String name;
	private String desc;
	
	
	
	//생성자
	public AuthorVO() {
	}
	public AuthorVO(int id, String name, String desc) {
		super();
		this.id = id;
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
		return "AuthorVO [id=" + id + ", name=" + name + ", desc=" + desc + "]";
	}
	
	
	
	
	
	
}

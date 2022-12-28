package com.example.demo.config;

public class Customer {
	private int id;
	private String cname;
	private String birthday;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", cname=" + cname + ", birthday=" + birthday + "]";
	}
	
	
}

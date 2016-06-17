package com.magnus.edutech.model;


import java.io.Serializable;

public class Chapters extends CommonDomain implements Serializable{
	private String subject_id;
	private String category_id;     //device_id + tailor_id + incremental_id
    private String name;
    private int count;
   
  

    public Chapters(String category_id,String subject_id, String name) {
        
    	this.category_id = category_id;
        this.subject_id =subject_id;
    	this.name = name;
        
    }

    public Chapters() {
    }

	public String getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

  

   /* @Override
    public String toString() {

        StringBuilder ret = new StringBuilder();
        ret.append("customer_id:").append(this.customer_id).append("\n");
        ret.append("name:").append(this.name).append("\n");
        ret.append("phoneNumber:").append(this.phoneNumber).append("\n");
        ret.append("emailAddress:").append(this.emailAddress).append("\n");
        ret.append("address:").append(this.address).append("\n");
        ret.append("gender:").append(this.gender).append("\n");

        return ret.toString();
    }*/

}

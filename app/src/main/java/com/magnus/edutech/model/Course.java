package com.magnus.edutech.model;


import java.io.Serializable;

public class Course extends CommonDomain implements Serializable{
    private String subject_id;     //device_id + tailor_id + incremental_id
    private String name;
   
  

    public Course(String subject_id, String name) {
        this.subject_id = subject_id;
        this.name = name;
        
    }

    public Course() {
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

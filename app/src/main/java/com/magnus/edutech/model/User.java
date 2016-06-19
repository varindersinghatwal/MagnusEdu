package com.magnus.edutech.model;


public class User extends CommonDomain {
	 private String fullName;
    private String email;
    private String mobile;
    private String password;
    private String rePassword;
    private String clintId;



    private String amountPaid;

    public User(String fullName, String email, String mobile, String password,String rePassword) {

    	this.fullName = fullName;
        this.email =email;
    	this.mobile = mobile;
		this.password = password;
		this.rePassword = rePassword;

    }
    public User(String email, String password) {
        this.email =email;
        this.password = password;
        }
    public User() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getClintId() {
        return clintId;
    }

    public void setClintId(String clintId) {
        this.clintId = clintId;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
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

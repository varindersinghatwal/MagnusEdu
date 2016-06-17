package com.magnus.edutech.model;


import java.io.Serializable;

public class Videos extends CommonDomain implements Serializable{
	private String video_id;
	private String subject_id;
	private String category_id;     //device_id + tailor_id + incremental_id
    private String name;
    private String url;
    private boolean downloaded;
   
  

    public Videos(String video_id,String category_id,String subject_id, String name,String url) {
        this.video_id =video_id;
    	this.category_id = category_id;
        this.subject_id =subject_id;
    	this.name = name;
    	this.url =url;        
    }

    public Videos() {
    }

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isDownloaded() {
		return downloaded;
	}

	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
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

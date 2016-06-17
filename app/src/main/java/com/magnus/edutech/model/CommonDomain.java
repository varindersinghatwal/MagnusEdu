package com.magnus.edutech.model;

public class CommonDomain {
    
    private boolean is_query_execute;
    private String from;
    private boolean sync;
    private boolean purchased;
    private String product_id;
    private String message;

    

	public boolean isIs_query_execute() {
        return is_query_execute;
    }

    public void setIs_query_execute(boolean is_query_execute) {
        this.is_query_execute = is_query_execute;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
    
}

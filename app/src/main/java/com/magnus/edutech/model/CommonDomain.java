package com.magnus.edutech.model;

public class CommonDomain {

    private boolean is_query_execute;
    private int from;
    private boolean purchased;
    private String message;


    public boolean isIs_query_execute() {
        return is_query_execute;
    }

    public void setIs_query_execute(boolean is_query_execute) {
        this.is_query_execute = is_query_execute;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }


    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

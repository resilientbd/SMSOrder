package com.project.messageorder.smsorder;

/**
 * Created by faisal-shakiba on 11/13/2016.
 */
public class item {
    private  String messagebody;

    public item(String messagebody) {
        this.messagebody = messagebody;
    }

    public String getMessagebody() {
        return messagebody;
    }

    public void setMessagebody(String messagebody) {
        this.messagebody = messagebody;
    }
}

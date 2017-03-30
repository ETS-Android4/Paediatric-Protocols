package com.mwongera.paediatric_protocols;

/**
 * Created by mwongera on 2/10/17.
 */

public class item {

    private String title;
    private int imageId;
    public item (Integer imageId,String title) {
        this.title=title;
        this.imageId=imageId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public int getImageId(){
        return imageId;
    }
    public void setImageId(int imageId){
        this.imageId=imageId;
    }

}

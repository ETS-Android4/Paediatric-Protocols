package com.mwongera.paediatric_protocols;

/**
 * Created by mwongera on 2/10/17.
 */

public class item {

    private String title, titlebtn ;
    public item (String title, String titlebtn) {
        this.title=title;
        this.titlebtn = titlebtn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitlebtn(){
        return titlebtn;
    }
    public void setTitlebtn(String titlebtn) {
        this.titlebtn = titlebtn;
    }

}

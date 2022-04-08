package com.example.sidebarsettings;

import java.io.Serializable;
//This is object that is used to transfer data between two activities
public class SettingsObject implements Serializable {
    //All the values that can be given
    private String gravity = "";
    private float font_size = 0;
    private String style = "";
    private boolean caps = false;
    private String text_content = "";
    private boolean editable = true;

    //Methods for all of them to either set or get values
    public void setEditable(Boolean b){
        editable = b;
    }
    public void setGravity(String str){
        gravity = str;
    }
    public void setFont_size(float i){
        font_size = i;
    }
    public void setStyle(String str){
        style = str;
    }
    public void setCaps(boolean str){
        caps = str;
    }
    public void setText_content(String str){
        text_content = str;
    }
    public String getGravity(){
        return gravity;
    }
    public float getFont_size(){
        return font_size;
    }
    public String getStyle(){
        return style;
    }
    public boolean getCaps(){
        return caps;
    }
    public String getText_content(){
        return text_content;
    }
    public boolean getEditable(){
        return editable;
    }
}

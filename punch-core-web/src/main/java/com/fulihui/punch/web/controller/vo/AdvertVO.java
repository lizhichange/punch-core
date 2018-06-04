package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

/**
 * @author lizhi
 */
public class AdvertVO extends ToString{

    private static final long serialVersionUID = 5751853147447185490L;
    private String id;
    
    private String advertType;
    
    private String advertName;
    
    private String advertImg;
    
    private String advertUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdvertType() {
        return advertType;
    }

    public void setAdvertType(String advertType) {
        this.advertType = advertType;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getAdvertImg() {
        return advertImg;
    }

    public void setAdvertImg(String advertImg) {
        this.advertImg = advertImg;
    }

    public String getAdvertUrl() {
        return advertUrl;
    }

    public void setAdvertUrl(String advertUrl) {
        this.advertUrl = advertUrl;
    }
    
    

}

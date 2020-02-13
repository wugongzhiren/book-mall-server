package com.mall.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//自增ID
    private String tips1;
    private String tips2;
    private String tips3;
    private String img1;
    private String img2;
    private String img3;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTips1() {
        return tips1;
    }

    public void setTips1(String tips1) {
        this.tips1 = tips1;
    }

    public String getTips2() {
        return tips2;
    }

    public void setTips2(String tips2) {
        this.tips2 = tips2;
    }

    public String getTips3() {
        return tips3;
    }

    public void setTips3(String tips3) {
        this.tips3 = tips3;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }
}

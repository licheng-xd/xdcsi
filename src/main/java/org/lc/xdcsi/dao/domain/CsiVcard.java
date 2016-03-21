package org.lc.xdcsi.dao.domain;

import com.alibaba.fastjson.JSON;

/**
 * Created by lc on 16/3/20.
 */
public class CsiVcard {

    private String name;

    private String mobile;

    private String city;

    private String company;

    private String pos;

    private String graduation;

    private long createtime;

    public CsiVcard() {
        this.createtime = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    @Override public String toString() {
        return JSON.toJSONString(this);
    }
}

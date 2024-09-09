package com.example.mvpburgerweahter.databean;
public class LocationInfo {
    private String name; // 地区/城市名称
    private String id; // 地区/城市ID
    private String lat; // 地区/城市纬度
    private String lon; // 地区/城市经度
    private String adm2; // 地区/城市的上级行政区划名称
    private String adm1; // 地区/城市所属一级行政区域
    private String country; // 地区/城市所属国家名称

    // 构造函数
    public LocationInfo() {
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAdm2() {
        return adm2;
    }

    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }

    public String getAdm1() {
        return adm1;
    }

    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // toString 方法，用于打印对象信息
    @Override
    public String toString() {
        return "LocationInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", adm2='" + adm2 + '\'' +
                ", adm1='" + adm1 + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
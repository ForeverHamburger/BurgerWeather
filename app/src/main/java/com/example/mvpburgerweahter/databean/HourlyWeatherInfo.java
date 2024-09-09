package com.example.mvpburgerweahter.databean;

public class HourlyWeatherInfo {
    private String fxTime; // 预报时间
    private String temp; // 温度
    private String icon; // 天气状况的图标代码
    private String text; // 天气状况的文字描述
    private String wind360; // 风向360角度
    private String windDir; // 风向
    private String windScale; // 风力等级
    private String windSpeed; // 风速
    private String humidity; // 相对湿度
    private String precip; // 当前小时累计降水量
    private String pop; // 逐小时预报降水概率
    private String pressure; // 大气压强
    private String cloud; // 云量
    private String dew; // 露点温度

    // 构造函数
    public HourlyWeatherInfo() {
    }

    // Getter 和 Setter 方法
    public String getFxTime() {
        return fxTime;
    }

    public void setFxTime(String fxTime) {
        this.fxTime = fxTime;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWind360() {
        return wind360;
    }

    public void setWind360(String wind360) {
        this.wind360 = wind360;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindScale() {
        return windScale;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getDew() {
        return dew;
    }

    public void setDew(String dew) {
        this.dew = dew;
    }

    @Override
    public String toString() {
        return "HourlyWeatherInfo{" +
                "fxTime='" + fxTime + '\'' +
                ", temp='" + temp + '\'' +
                ", icon='" + icon + '\'' +
                ", text='" + text + '\'' +
                ", wind360='" + wind360 + '\'' +
                ", windDir='" + windDir + '\'' +
                ", windScale='" + windScale + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", humidity='" + humidity + '\'' +
                ", precip='" + precip + '\'' +
                ", pop='" + pop + '\'' +
                ", pressure='" + pressure + '\'' +
                ", cloud='" + cloud + '\'' +
                ", dew='" + dew + '\'' +
                '}';
    }
}

package com.ycy.entity;

import com.ycy.utils.StringUtils;

public class UserInfo {
    private long id;
    private String password;
    private String phone;
    private String name;
    private String headImage;
    private float money;
    private float goldCoin;
    private float totalMoney;
    private String weixin;
    private String douyin;
    private int sign;//签到第几天

    public boolean isLogin(){
        return !StringUtils.isEmpty(phone);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public float getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(float goldCoin) {
        this.goldCoin = goldCoin;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getDouyin() {
        return douyin;
    }

    public void setDouyin(String douyin) {
        this.douyin = douyin;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }
}

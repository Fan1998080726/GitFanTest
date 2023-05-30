package com.sdkj.util.AccessTokenInfo;

import com.sdkj.util.DBBean;

public class WeixinUserInfo {

	public String  openId;
	public int  subscribe;
	public String  SubscribeTime;
	public String  nickname;
	public int  sex;
	public String  country;
	public String  province;
	public String  city;
	public String  language;
	public String  groupid;
	public String  HeadImgUrl;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getSubscribeTime() {
		return SubscribeTime;
	}
	public void setSubscribeTime(String subscribeTime) {
		SubscribeTime = subscribeTime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getHeadImgUrl() {
		return HeadImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		HeadImgUrl = headImgUrl;
	}
	@Override
	public String toString() {
		return "WeixinUserInfo [openId=" + openId + ", subscribe=" + subscribe + ", SubscribeTime=" + SubscribeTime
				+ ", nickname=" + nickname + ", sex=" + sex + ", country=" + country + ", province=" + province
				+ ", city=" + city + ", language=" + language + ", groupid=" + groupid + ", HeadImgUrl=" + HeadImgUrl
				+ ", getOpenId()=" + getOpenId() + ", getSubscribe()=" + getSubscribe() + ", getSubscribeTime()="
				+ getSubscribeTime() + ", getNickname()=" + getNickname() + ", getSex()=" + getSex() + ", getCountry()="
				+ getCountry() + ", getProvince()=" + getProvince() + ", getCity()=" + getCity() + ", getLanguage()="
				+ getLanguage() + ", getGroupid()=" + getGroupid() + ", getHeadImgUrl()=" + getHeadImgUrl()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
}

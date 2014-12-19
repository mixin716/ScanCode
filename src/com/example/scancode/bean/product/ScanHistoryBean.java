package com.example.scancode.bean.product;

/** 积分兑换历史bean */
public class ScanHistoryBean {

	String name;// 姓名
	String value;// 码乐盾
	String time;// 时间
	String type;// 是否评价

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

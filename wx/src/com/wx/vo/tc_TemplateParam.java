package com.wx.vo;




public class tc_TemplateParam {
	private String Name;
	private String Value;
	private String Color;
	public tc_TemplateParam(String Name, String Value, String Color) {
		// TODO Auto-generated constructor stub
		this.Name = Name;
		this.Value = Value;
		this.Color = Color;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
    
}


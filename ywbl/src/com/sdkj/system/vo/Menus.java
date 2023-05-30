package com.sdkj.system.vo;

import java.util.List;

public class Menus {

	private String menu_id;		// 菜单ID
	private String menu_pid;	// 菜单父ID
	private String menu_name;	// 菜单名称
	private String menu_url;	// 菜单指向路径
	private String menu_image;	// 菜单图片
	private int is_child;		// 备注
	
	private List<Menus> child_menus;//子菜单列表 txb add
	
	private String role_id; // 权限ID
	
	private String rm_add;//是否可以添加 0：是 1：否
	private String rm_update;//是否可以修改 0：是 1：否
	private String rm_del;//是否可以删除 0：是 1：否

	
	public String getRm_add() {
		return rm_add;
	}

	public void setRm_add(String rm_add) {
		this.rm_add = rm_add;
	}

	public String getRm_update() {
		return rm_update;
	}

	public void setRm_update(String rm_update) {
		this.rm_update = rm_update;
	}

	public String getRm_del() {
		return rm_del;
	}

	public void setRm_del(String rm_del) {
		this.rm_del = rm_del;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_pid() {
		return menu_pid;
	}

	public void setMenu_pid(String menu_pid) {
		this.menu_pid = menu_pid;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getMenu_image() {
		return menu_image;
	}

	public void setMenu_image(String menu_image) {
		this.menu_image = menu_image;
	}

	public int getIs_child() {
		return is_child;
	}

	public void setIs_child(int is_child) {
		this.is_child = is_child;
	}

	public List<Menus> getChild_menus() {
		return child_menus;
	}

	public void setChild_menus(List<Menus> child_menus) {
		this.child_menus = child_menus;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "Menus [menu_id=" + menu_id + ", menu_pid=" + menu_pid + ", menu_name=" + menu_name + ", menu_url="
				+ menu_url + ", menu_image=" + menu_image + ", is_child=" + is_child + ", child_menus=" + child_menus
				+ ", role_id=" + role_id + ", rm_add=" + rm_add + ", rm_update=" + rm_update + ", rm_del=" + rm_del
				+ "]";
	}




	
}

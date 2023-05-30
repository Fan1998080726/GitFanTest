/**
 * EXT树形结构，用于保存一级菜单及其子菜单
 * 
 */
package com.sdkj.system.vo;

import java.io.Serializable;
import java.util.List;

public class ExtTreeVo  implements Serializable  {
	private String menuId;
	private String title; // 一级菜单名称
	private String iconCls; // 一级菜单引用图片
	private List<ExtTreeChildrenVo> children; // 子菜单

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<ExtTreeChildrenVo> getChildren() {
		return children;
	}

	public void setChildren(List<ExtTreeChildrenVo> children) {
		this.children = children;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}

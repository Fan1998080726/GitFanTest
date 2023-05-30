/**
 * EXT的树形结构，用于展示除一级菜单外的子菜单
 * 
 */
package com.sdkj.system.vo;

import java.io.Serializable;
import java.util.List;

public class ExtTreeChildrenVo implements Serializable {
	private String text; // 菜单名称
	private String href; // 菜单连接
	private boolean leaf; // 是否为子结点
	private String hrefTarget;   //连接的target
	private String iconCls;   //图片
	private List<ExtTreeChildrenVo> children; // 子菜单
	private String menuId;
	
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public List<ExtTreeChildrenVo> getChildren() {
		return children;
	}

	public void setChildren(List<ExtTreeChildrenVo> children) {
		this.children = children;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	
	

}

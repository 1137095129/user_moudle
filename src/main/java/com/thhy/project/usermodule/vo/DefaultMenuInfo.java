package com.thhy.project.usermodule.vo;

import java.io.Serializable;
import java.util.List;

public class DefaultMenuInfo implements Serializable {
	private static final long serialVersionUID = 5307901113322603573L;
	private Integer menuId;
	private String component;
	private Boolean hidden;
	private Integer level;
	private Integer parentId;
	private String name;
	private String path;
	private String redirect;
	private Meta meta;

	private List<DefaultMenuInfo> children;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<DefaultMenuInfo> getChildren() {
		return children;
	}

	public void setChildren(List<DefaultMenuInfo> children) {
		this.children = children;
	}
}

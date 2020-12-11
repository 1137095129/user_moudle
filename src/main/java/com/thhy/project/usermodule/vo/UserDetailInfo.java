package com.thhy.project.usermodule.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDetailInfo implements Serializable {
	private static final long serialVersionUID = 3638807339391358782L;
	private Integer userId;
	private String username;
	private String realName;
	private Integer sex;
	private String photoUrl;
	private List<Menu> menuVos;
	private List<Roles> roleVos;


}

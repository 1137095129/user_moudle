package com.thhy.project.usermodule.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DefaultUserListInfo implements Serializable {
	private static final long serialVersionUID = 7286029437332301333L;

	private Integer userId;
	private String username;
	private String telphone;
	private String email;
	private String realName;
	private Integer sex;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String photoUrl;
}

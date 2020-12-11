package com.thhy.project.usermodule.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RolesBasicInfo implements Serializable {
	private static final long serialVersionUID = -6530391668358573141L;
	private Integer roleId;
	private String roleName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
}

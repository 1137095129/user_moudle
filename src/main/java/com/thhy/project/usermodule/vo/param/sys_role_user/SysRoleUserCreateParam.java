package com.thhy.project.usermodule.vo.param.sys_role_user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SysRoleUserCreateParam implements Serializable {
	private static final long serialVersionUID = -8013007041563528534L;
	@NotNull
	private Integer userId;
	private String role;
}

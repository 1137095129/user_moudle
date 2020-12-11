package com.thhy.project.usermodule.vo.param.sysrole;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateRoleParam implements Serializable {
	private static final long serialVersionUID = 5025655257501507391L;
	@NotNull
	private Integer roleId;
	private String roleName;
	private Integer updateBy;
}

package com.thhy.project.usermodule.vo.param.sysrole;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeleteRoleParam implements Serializable {
	private static final long serialVersionUID = -8489322802571641614L;
	@NotNull
	private Integer roleId;
}

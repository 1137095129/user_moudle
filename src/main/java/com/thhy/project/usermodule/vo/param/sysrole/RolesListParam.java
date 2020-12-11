package com.thhy.project.usermodule.vo.param.sysrole;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class RolesListParam implements Serializable {
	private static final long serialVersionUID = -2271994258850614706L;
	@NotNull
	private Integer pageNum;
	@NotNull
	private Integer pageSize;
	private Date startTime;
	private Date endTime;
	private String roleName;
}

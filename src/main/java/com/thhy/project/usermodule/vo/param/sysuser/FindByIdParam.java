package com.thhy.project.usermodule.vo.param.sysuser;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class FindByIdParam implements Serializable {
	private static final long serialVersionUID = -8336725420276238137L;
	@NotNull
	private Integer userId;
}

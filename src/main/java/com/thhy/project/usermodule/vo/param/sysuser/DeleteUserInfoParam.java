package com.thhy.project.usermodule.vo.param.sysuser;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeleteUserInfoParam implements Serializable {
	private static final long serialVersionUID = -9118846066489911699L;
	@NotNull
	private Integer userId;
}

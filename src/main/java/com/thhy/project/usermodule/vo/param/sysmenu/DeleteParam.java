package com.thhy.project.usermodule.vo.param.sysmenu;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeleteParam implements Serializable {
	private static final long serialVersionUID = -2446584508216585562L;
	@NotNull
	private Integer menuId;
}

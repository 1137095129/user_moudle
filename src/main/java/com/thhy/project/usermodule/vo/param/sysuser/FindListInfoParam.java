package com.thhy.project.usermodule.vo.param.sysuser;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class FindListInfoParam implements Serializable {
	private static final long serialVersionUID = 4407631829105023860L;
	@NotNull
	private Integer pageNum;
	@NotNull
	private Integer pageSize;
	private String realName;
	private String telphone;
	private Date startTime;
	private Date endTime;
}

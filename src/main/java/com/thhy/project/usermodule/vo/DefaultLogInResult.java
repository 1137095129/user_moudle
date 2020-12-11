package com.thhy.project.usermodule.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultLogInResult implements Serializable {
	private static final long serialVersionUID = -4493550722420271129L;
	private String userInfo;
	private String token;
	private List<DefaultButtonInfo> buttonInfos;
	private List<DefaultMenuInfo> menuInfos;
	private String publicKey;
}

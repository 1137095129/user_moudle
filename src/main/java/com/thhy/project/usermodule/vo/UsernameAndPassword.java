package com.thhy.project.usermodule.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsernameAndPassword implements Serializable {
	private static final long serialVersionUID = 5700247092770743173L;
	private String username;
	private String password;
}

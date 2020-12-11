package com.thhy.project.usermodule.util;

import java.util.HashMap;

/**
 * 返回数据
 */
public class ResponseMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public ResponseMap() {
		put("code", 0);
		put("sym", "ok");
		put("msg", "");
	}

	public static ResponseMap error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static ResponseMap error(String msg) {
		return error(500, msg);
	}

	public static ResponseMap error(int code, String msg) {
		ResponseMap responseMap = new ResponseMap();
		responseMap.put("code", code);
		responseMap.put("sym", "fail");
		responseMap.put("msg", msg);
		return responseMap;
	}

	public static ResponseMap parameterError(int code, String msg) {
		ResponseMap responseMap = new ResponseMap();
		responseMap.put("code", code);
		responseMap.put("sym", "fail");
		responseMap.put("msg", msg);
		return responseMap;
	}

	public static ResponseMap toLogError() {
		ResponseMap responseMap = new ResponseMap();
		responseMap.put("code", 0);
		responseMap.put("msg", "请先登录！");
		responseMap.put("sym", "toLogIN");
		return responseMap;
	}

	public static ResponseMap jurisdictionError() {
		ResponseMap responseMap = new ResponseMap();
		responseMap.put("code", 0);
		responseMap.put("msg", "权限不足！");
		responseMap.put("sym", "fail");
		return responseMap;
	}

	public static ResponseMap ok(String msg) {
		ResponseMap responseMap = new ResponseMap();
		responseMap.put("msg", msg);
		return responseMap;
	}

	public static ResponseMap fail(String msg) {
		ResponseMap responseMap = new ResponseMap();
		responseMap.put("sym", "fail");
		responseMap.put("msg", msg);
		return responseMap;
	}

	public static ResponseMap ok(Object data) {
		ResponseMap responseMap = new ResponseMap();
		responseMap.put("sym", "ok");
		responseMap.put("msg", "请求成功！");
		responseMap.put("data", data);
		return responseMap;
	}

	public static ResponseMap ok() {
		return new ResponseMap();
	}

	public ResponseMap put(String key, Object value) {
		super.put(key, value);
		return this;
	}


	public static ResponseMap parameterError() {
		ResponseMap responseMap = new ResponseMap();
		responseMap.put("sym", "fail");
		responseMap.put("msg", "参数错误");
		responseMap.put("code", 400);
		return responseMap;
	}
}

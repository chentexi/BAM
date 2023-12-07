package com.trent.common.utils.result;

import lombok.Data;

/**
 * @Author: Trent
 * @Date: 2021/11/12 14:04
 * @program: BAM
 * @Description:
 */
@Data
public class ResultVo {
	private static final long serialVersionUID = -2436709332711007968L;

	/**
	 * 响应消息
	 */
	private String msg;
	/**
	 * 响应业务状态
	 */
	private Integer code;
	/**
	 * 响应中的数据
	 */
	private Object data;

	/**
	 * 默认返回成功状态码，数据对象
	 *
	 * @param data
	 */
	public ResultVo(Object data) {
		this.code = ResultCode.SUCCESS.getCode();
		this.msg = ResultCode.SUCCESS.getMsg();
		this.data = data;
	}

	public ResultVo(ResultCode resultCode, Object data) {
		this.code = resultCode.getCode();
		this.msg = resultCode.getMsg();
		this.data = data;
	}
	// 手动设置返回vo
	public ResultVo(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	// 返回指定状态码，数据对象
	public ResultVo(StatusCode statusCode, Object data) {
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
		this.data = data;
	}
//
//	// 只返回状态码
//	public ResultVo(StatusCode statusCode) {
//		this.code = statusCode.getCode();
//		this.msg = statusCode.getMsg();
//		this.data = null;
//	}


}

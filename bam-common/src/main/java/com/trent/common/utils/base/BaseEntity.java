package com.trent.common.utils.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author: Trent
 * @Date: 2021/11/12 13:41
 * @program: BAM
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Admin对象", description = "")
public class BaseEntity implements Serializable{
	@ApiModelProperty(value = "创建人")
	private String createBy;
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	@ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime  updateTime;
	@ApiModelProperty(value = "备注")
	private String remark;
	
	@TableField(exist = false)
	private Integer pageNum;
	@TableField(exist = false)
	private Integer pageSize;
	
	
	public void dd(){}
}

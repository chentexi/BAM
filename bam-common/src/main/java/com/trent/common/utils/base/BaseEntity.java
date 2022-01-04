package com.trent.common.utils.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable{
	private static final Integer DEFAULT_PAGE_SIZE = 10;
	private static final Integer DEFAULT_PAGE_NUM = 1;
	
	@ApiModelProperty(value = "创建人")
	private String createBy;
	
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	@ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	@ApiModelProperty(value = "备注")
	private String remark;
	
	@TableField(exist = false)
	@ApiModelProperty("每页数据数")
	private Integer currentPage = DEFAULT_PAGE_NUM;
	
	@TableField(exist = false)
	@ApiModelProperty("页数")
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	
	@TableField(exist = false)
	@ApiModelProperty("开始时间")
	private Date startDateTime;
	
	@TableField(exist = false)
	@ApiModelProperty("结束时间")
	private Date endDateTime;
	
}

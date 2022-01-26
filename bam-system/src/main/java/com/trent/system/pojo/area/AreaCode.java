package com.trent.system.pojo.area;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trent.common.utils.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author Trent
 * @since 2022-01-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_area_code")
@ApiModel(value="AreaCode对象", description="")
public class AreaCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区划代码")
    private Long code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "级别1-5,省市县镇村")
    private Boolean level;

    @ApiModelProperty(value = "父级区划代码")
    private Long pcode;

    @ApiModelProperty(value = "子区域集合")
    @TableField(exist = false)
    public List<AreaCode> children;
    

}

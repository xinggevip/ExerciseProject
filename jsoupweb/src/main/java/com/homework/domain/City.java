package com.homework.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_city")
@ApiModel(value="City对象", description="")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "城市id")
    private Integer locationId;

    @ApiModelProperty(value = "直属的省id")
    private Integer provinceLocationId;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "现存确诊")
    private Integer currentConfirmedCount;

    @ApiModelProperty(value = "累计确诊")
    private Integer confirmedCount;

    @ApiModelProperty(value = "境外输入")
    private Integer suspectedCount;

    @ApiModelProperty(value = "累计治愈")
    private Integer curedCount;

    @ApiModelProperty(value = "累计死亡")
    private Integer deadCount;


}

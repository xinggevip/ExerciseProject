package com.homework.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_province")
@ApiModel(value="Province对象", description="")
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "省id")
    @TableId(value = "location_id", type = IdType.ID_WORKER)
    private Integer locationId;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "省简称")
    private String provinceShortName;

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

    @ApiModelProperty(value = "特别说明")
    private String comment;

    @ApiModelProperty(value = "统计数据")
    private String statisticsData;

    @ApiModelProperty(value = "城市列表")
    @TableField(exist = false) // 排除字段
    private List<City> cities;
}

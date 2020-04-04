package com.homework.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("t_statistics")
@ApiModel(value="Statistics对象", description="")
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "传染源")
    private String infectSource;

    @ApiModelProperty(value = "通过")
    private String passWay;

    @ApiModelProperty(value = "图片路径")
    private String imgUrl;

    @ApiModelProperty(value = "每日图片")
    private String dailyPic;

    @ApiModelProperty(value = "总结")
    private String summary;

    @ApiModelProperty(value = "逻辑删除1为删除0为未删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "计数备注")
    private String countRemark;

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

    @ApiModelProperty(value = "现存无症状")
    private Integer seriousCount;

    @ApiModelProperty(value = "该字段已替换为说明1")
    private String virus;

    @ApiModelProperty(value = "备注1")
    private String remark1;

    @ApiModelProperty(value = "备注2")
    private String remark2;

    @ApiModelProperty(value = "备注3")
    private String remark3;

    @ApiModelProperty(value = "备注4")
    private String remark4;

    @ApiModelProperty(value = "备注5")
    private String remark5;

    @ApiModelProperty(value = "笔记1")
    private String note1;

    @ApiModelProperty(value = "笔记2")
    private String note2;

    @ApiModelProperty(value = "笔记3")
    private String note3;


}

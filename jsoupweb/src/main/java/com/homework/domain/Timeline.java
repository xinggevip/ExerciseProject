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
 * @since 2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_timeline")
@ApiModel(value="Timeline对象", description="")
public class Timeline implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "出版日期时间戳")
    private Long pubDate;

    @ApiModelProperty(value = "发布日期字符串")
    private String pubDateStr;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "总结")
    private String summary;

    @ApiModelProperty(value = "信息源")
    private String infoSource;

    @ApiModelProperty(value = "资源链接")
    private String sourceUrl;


}

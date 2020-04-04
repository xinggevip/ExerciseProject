package com.homework.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.homework.domain.Timeline;
import com.homework.service.TimelineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
@Api(tags = {""})
@RestController
@RequestMapping("/timeline")
public class TimelineController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TimelineService imelineService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Timeline timeline){
        return imelineService.add(timeline);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return imelineService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Timeline timeline){
        return imelineService.updateData(timeline);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Timeline> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount){
        return imelineService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Timeline findById(@PathVariable Long id){
        return imelineService.findById(id);
    }

}

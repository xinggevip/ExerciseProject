package com.homework.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.homework.domain.City;
import com.homework.service.CityService;
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
 * @since 2020-04-04
 */
@Api(tags = {""})
@RestController
@RequestMapping("/city")
public class CityController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CityService ityService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody City city){
        return ityService.add(city);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return ityService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody City city){
        return ityService.updateData(city);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<City> findListByPage(@RequestParam Integer page,
                                      @RequestParam Integer pageCount){
        return ityService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public City findById(@PathVariable Long id){
        return ityService.findById(id);
    }

}

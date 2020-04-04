package com.homework.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.homework.domain.Province;
import com.homework.service.ProvinceService;
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
@RequestMapping("/province")
public class ProvinceController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ProvinceService rovinceService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Province province){
        return rovinceService.add(province);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return rovinceService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Province province){
        return rovinceService.updateData(province);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Province> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return rovinceService.findListByPage(page, pageCount);
    }



    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Province findById(@PathVariable Long id){
        return rovinceService.findById(id);
    }

}

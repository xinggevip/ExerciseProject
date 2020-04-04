package com.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.homework.domain.Province;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
public interface ProvinceService extends IService<Province> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Province>
     */
    IPage<Province> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param province 
     * @return int
     */
    int add(Province province);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param province 
     * @return int
     */
    int updateData(Province province);

    /**
     * id查询数据
     *
     * @param id id
     * @return Province
     */
    Province findById(Long id);

}

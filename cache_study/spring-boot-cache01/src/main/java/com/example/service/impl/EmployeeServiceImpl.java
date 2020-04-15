package com.example.service.impl;

import com.example.dao.EmployeeDao;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Employee)表服务实现类
 *
 * @author 油条
 * @since 2020-04-15 12:49:49
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    /**
     * 将方法的运行结果进行缓存，以后再有相同的请求，直接从缓存中获取，不再调用方法
     *
     * CacheManager管理多个Cache组件，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一的名字
     * 属性：
     *   CacheName/value:指定存储缓存组件的名字
     *   key:缓存数据使用的key,可以使用它来指定。默认是使用方法参数的值，1-方法的返回值
     *   编写Spel表达式：#id 参数id的值， #a0/#p0 #root.args[0]
     *   keyGenerator:key的生成器，自己可以指定key的生成器的组件id
     *   key/keyGendertor二选一使用
     *   cacheManager指定Cache管理器，或者cacheReslover指定获取解析器
     *   condition:指定符合条件的情况下，才缓存；
     *   unless：否定缓存，unless指定的条件为true，方法的返回值就不会被缓存，可以获取到结果进行判断
     *   sync:是否使用异步模式，unless不支持
     *
     *   @Cacheable(cacheNames = {"emp"})  // 默认key为参数值
     *   @Cacheable(cacheNames = {"emp"},key = "#root.methodName+'['+#id+']'")  // 自定义key为queryById[id] 例queryById[1]
     *   @Cacheable(cacheNames = {"emp"},keyGenerator = "myKeyGenerator")  // 使用自定义的keyGenerator
     *   @Cacheable(cacheNames = {"emp"},keyGenerator = "myKeyGenerator",condition = "#a0>1")  // 第一个参数大于1才进行缓存
     *   @Cacheable(cacheNames = {"emp"},keyGenerator = "myKeyGenerator",unless = "#a0==2")  // 第一个参数的值的等于二就不缓存
     */
    @Cacheable(cacheNames = "emp")
    public Employee queryById(Integer id) {
        System.out.println("查询" + id + "号员工");
        return this.employeeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Employee> queryAllByLimit(int offset, int limit) {
        return this.employeeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee insert(Employee employee) {
        this.employeeDao.insert(employee);
        return employee;
    }

    /**
     * 修改数据
     *
     * 同步更新数据库和缓存
     * 注意：put的key和query的key要一致
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    @CachePut(cacheNames = {"emp"}, key = "#result.id")
    public Employee update(Employee employee) {
        System.out.println("update:  " + employee);
        this.employeeDao.update(employee);
        return this.queryById(employee.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @CacheEvict 缓存清除
     * key： 指定根据key删除，默认为参数属性名
     * @CacheEvict(value = "emp",allEntries = true)  // 清空缓存
     * 注意：清除缓存行为 默认是在方法执行之后，即方法体出现异常不会清除缓存
     * @CacheEvict(value = "emp",key = "#id",beforeInvocation = true)  // 清除缓存行为在方法执行之前，无论是否出现异常都会清除缓存
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @CacheEvict(value = "emp",key = "#id")
    public boolean deleteById(Integer id) {
        System.out.println("---->删除的employee的id是: "+id);
        return this.employeeDao.deleteById(id) > 0;
    }
}
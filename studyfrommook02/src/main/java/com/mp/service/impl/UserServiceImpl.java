package com.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import com.mp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

package com.company.project.service.impl;

import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/06/14.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("email", name);
        List<User> users =  userMapper.selectByCondition(condition);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}

package com.company.project.service;
import com.company.project.model.User;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/06/14.
 */
public interface UserService extends Service<User> {

    User findByName(String name);
}

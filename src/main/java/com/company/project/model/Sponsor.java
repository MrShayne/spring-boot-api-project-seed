package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String password;

    /**
     * 创建用户的时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取创建用户的时间
     *
     * @return create_time - 创建用户的时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建用户的时间
     *
     * @param createTime 创建用户的时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
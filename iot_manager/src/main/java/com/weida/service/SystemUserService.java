package com.weida.service;


import com.github.pagehelper.PageInfo;
import com.weida.pojo.SystemUser;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/18
 * Time:17:40
 */

public interface SystemUserService {

    void addSystemUser(SystemUser systemUser);

    void updateSystemUser(SystemUser systemUser);

    void deleteSystemUser(String account);

    SystemUser findByAccount(String account);

    PageInfo<SystemUser> findAll(int page, int pageSize);
}

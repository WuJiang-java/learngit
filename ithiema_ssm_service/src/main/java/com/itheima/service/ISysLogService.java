package com.itheima.service;

import com.itheima.pojo.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll();

}

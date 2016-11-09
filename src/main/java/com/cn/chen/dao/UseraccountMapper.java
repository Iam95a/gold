package com.cn.chen.dao;

import com.cn.chen.domain.Useraccount;

public interface UseraccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Useraccount record);

    int insertSelective(Useraccount record);

    Useraccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Useraccount record);

    int updateByPrimaryKey(Useraccount record);
}
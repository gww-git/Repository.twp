package com.nanchang.mapper;

import com.nanchang.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: 阿伟
 * @Date: 2019/9/20 17:35
 * @@Description:
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,bio) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio})")
    void InsertUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToKen(@Param("token") String token);   //当传入的是非对象时，要用param注解
}

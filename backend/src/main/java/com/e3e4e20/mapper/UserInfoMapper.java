package com.e3e4e20.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.e3e4e20.entity.mapper.UserInfoEntity;

import java.util.List;

/*
Filename: Userinfo
Created: 2023年04月08日 15时56分45秒 星期六
Author: 天龙梦雪
*/
@Repository
@Mapper
public interface UserInfoMapper {
    /**
     * 添加用户信息
     *
     * @param userInfo 用户信息
     * @return Integer 添加的用户实体类个数
     */
//    @Insert("insert into t_userinfo (userid,name,department,political_status,position) values(#{userid},#{name},#{department},#{politicalStatus},#{position})")
    int insertUserInfo(UserInfoEntity userInfo);

    /**
     * 查询数据库用户信息表中全部的用户信息
     *
     * @return 用户信息
     */
//    @Select("select * from t_userinfo")
    List<UserInfoEntity> selectAllUserInfo();

    /**
     * 查询数据库用户信息表中指定用户唯一标识对应的用户信息
     *
     * @param userid 用户唯一标识
     * @return 用户信息
     */
//    @Select("select * from t_userinfo where userid=#{userid}")
    UserInfoEntity selectUserInfoByUserid(@Param("id") String userid);

    /**
     * 根据用户唯一标识修改用户信息
     *
     * @param userInfo 用户信息
     * @return 修改的用户信息的个数
     */
//    @Update("update t_userinfo set name=#{name},department=#{department},political_status=#{politicalStatus},position=#{position} where userid=#{userid}")
    int updateUserInfoByUserid(UserInfoEntity userInfo);

    /**
     * 删除指定用户表示的用户信息
     *
     * @param userid 用户唯一标识
     * @return 删除的用户信息的个数
     */
//    @Delete("delete from t_userinfo where userid=#{userid}")
    int deleteUserInfoByUserid(@Param("id") String userid);
}

package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.LoginEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: LoginMapper.xml
Created: 2023年04月10日 21时27分45秒 星期一
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface LoginMapper {
    /**
     * 根据用户名称和用户密码查询用户唯一标识
     *
     * @param username 用户名称
     * @param password 用户密码
     * @return 用户唯一标识
     */
//    @Select("select userid from t_login where username=#{username} and password=#{password}")
    String selectUserid(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户唯一标识查询用户登录信息
     *
     * @param userid 用户唯一标识
     * @return 用户登录信息
     */
//    @Select("select * from t_login where userid=#{userid}")
    LoginEntity selectUserByUserid(@Param("id") String userid);

    /**
     * 根据人员的唯一标识、用户名称、登录密码检索数据库中是否存在该人员的登录信息
     * @param userid 人员的唯一标识
     * @param username 用户名称
     * @param password 登录密码
     * @return 人员登录信息
     */
    LoginEntity selectUserByIdNameWord (@Param("id") String userid, @Param("username") String username,
                                        @Param("password") String password);

    /**
     * 查询全部能够登录的用户信息
     *
     * @return 可登录的用户信息
     */
//    @Select("select * from t_login")
    List<LoginEntity> selectAllLoginUser();

    /**
     * 添加用户登录的用户
     *
     * @param loginEntity 用户登录信息
     * @return 添加的用户登录信息的个数
     */
//    @Insert("insert into t_login (userid,username,password) values(#{userid},#{username},#{password})")
    Integer addLoginUser(LoginEntity loginEntity);

    /**
     * 根据用户唯一标识修改用户登录密码
     *
     * @param userid   用户唯一标识
     * @param password 用户密码
     * @return 修改的用户登录信息的个数
     */
//    @Update("update t_login set password=#{password} where userid=#{userid}")
    Integer updatePasswordByUserid(@Param("id") String userid, @Param("password") String password);

    /**
     * 根据用户唯一标识删除登录用户
     *
     * @param userid 用户唯一标识
     * @return 删除的用户登录信息的个数
     */
//    @Delete("delete from t_login where userid=#{userid}")
    Integer deleteLoginUserByUserid(@Param("id") String userid);

    /**
     * 根据人员唯一标识检索人员头像
     *
     * @param userid 人员唯一标识
     * @return 人员头像的图片名称
     */
    String selectUserAvatar(@Param("id") String userid);

    /**
     * 根据人员唯一标识修改人员头像
     *
     * @param userid 人员唯一标识
     * @param avatar 人员头像
     * @return 修改成功的记录条数
     */
    Integer updateUserAvatar(@Param("id") String userid, @Param("avatar") String avatar);
}

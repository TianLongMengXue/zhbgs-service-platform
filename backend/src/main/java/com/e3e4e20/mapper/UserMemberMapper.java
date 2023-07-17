package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.UserMemberEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: UserMemberMapper
Created: 2023年07月17日 09时19分21秒 星期一
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface UserMemberMapper {
    /**
     * 查询 t_member 中所有在职人员的全部信息
     * @return 所有在职人员的全部信息
     */
    List<UserMemberEntity> selectAllUserInfo();

    /**
     * 根据人员唯一标识从 t_member 中查询该在职人员的全部信息
     * @param userid 人员唯一标识
     * @return 指定在职人员的全部信息
     */
    UserMemberEntity selectUserInfoById (@Param("id") String userid);

    /**
     * 根据人员唯一标识修改 t_member 中在职人员的信息
     * @param userMemberEntity 在职人员的全部信息
     * @return 修改成功的记录的条数
     */
    Integer updateUserInfoById (UserMemberEntity userMemberEntity);

    /**
     * 向 t_member 中添加在职人员信息
     * @param userMemberEntity 在职人员的全部信息
     * @return 添加成功的记录条数
     */
    Integer insertUserInfo(UserMemberEntity userMemberEntity);

    /**
     * 根据人员唯一标识删除 t_member 中在职人员的全部信息
     * @param userid 人员唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserInfoById(@Param("id") String userid);
}

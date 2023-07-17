package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.UserInternshipEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: UserInternshipMapper
Created: 2023年07月17日 09时19分21秒 星期一
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface UserInternshipMapper {
    /**
     * 查询 t_internship 中所有见/实习人员的全部信息
     * @return 所有见/实习人员的全部信息
     */
    List<UserInternshipEntity> selectAllUserInfo();

    /**
     * 根据人员唯一标识从 t_internship 中查询该见/实习人员的全部信息
     * @param userid 人员唯一标识
     * @return 指定见/实习人员的全部信息
     */
    UserInternshipEntity selectUserInfoById (@Param("id") String userid);

    /**
     * 根据人员唯一标识修改 t_internship 中见/实习人员的信息
     * @param userInternshipEntity 见/实习人员的全部信息
     * @return 修改成功的记录的条数
     */
    Integer updateUserInfoById (UserInternshipEntity userInternshipEntity);

    /**
     * 向 t_internship 中添加见/实习人员信息
     * @param userInternshipEntity 见/实习人员的全部信息
     * @return 添加成功的记录条数
     */
    Integer insertUserInfo(UserInternshipEntity userInternshipEntity);

    /**
     * 根据人员唯一标识删除 t_internship 中见/实习人员的全部信息
     * @param userid 人员唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserInfoById(@Param("id") String userid);
}

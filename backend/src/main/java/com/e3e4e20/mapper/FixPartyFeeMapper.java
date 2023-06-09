package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.FixPartyFeeEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: FixPartyFeeMapper
Created: 2023年04月25日 17时28分04秒 星期二
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface FixPartyFeeMapper {
    /**
     * 查询全部的固定党费和用户唯一标识
     * @return 全部的固定党费和用户唯一标识
     */
    List<FixPartyFeeEntity> selectAllFixPartyFee ();

    /**
     * 根据用户唯一标识查询该用户的固定党费
     * @param userid 用户唯一标识
     * @return 该用户的固定党费
     */
    FixPartyFeeEntity selectFixPartyFeeById(@Param("id") String userid);

    /**
     * 修改指定用户的固定党费
     * @param fixPartyFeeEntity 用户唯一标识和新的固定党费
     * @return 修改成功的记录条数
     */
    int updateFixPartyFee (FixPartyFeeEntity fixPartyFeeEntity);

    /**
     * 添加缴纳固定党费的成员
     * @param fixPartyFeeEntity 用户唯一标识和固定党费
     * @return 添加成功的记录条数
     */
    int insertFixPartyFee (FixPartyFeeEntity fixPartyFeeEntity);

    /**
     * 删除缴纳固定党费的成员
     * @param userid 用户唯一标识
     * @return 删除成功的记录条数
     */
    int deleteFixPartyFeeById (@Param("id") String userid);
}

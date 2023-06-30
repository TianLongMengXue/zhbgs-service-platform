package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.PartyBranchEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: PartyBranchMapper
Created: 2023年04月27日 09时43分05秒 星期四
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface PartyBranchMapper {
    /**
     * 查询出全部的党支部名称
     * @return 全部的党支部名称
     */
    List<PartyBranchEntity> selectAllPartyBranch ();

    /**
     * 根据党支部唯一标识获取党支部名称
     * @param uuid 党支部唯一标识
     * @return 党支部名称
     */
    String selectPartyBranchNameById(@Param("id") String uuid);

    /**
     * 根据党支部名称的唯一标识查询党支部信息
     * @param uuid 党支部唯一标识
     * @return 党支部信息
     */
    PartyBranchEntity selectPartyBranchById (@Param("id") String uuid);

    /**
     * 添加党支部名称
     * @param partyBranchEntity 党支部名称唯一标识和党支部名称
     * @return 添加成功的记录条数
     */
    int insertPartyBranch (PartyBranchEntity partyBranchEntity);

    /**
     * 修改党支部名称
     * @param partyBranchEntity 党支部名称唯一标识和新的党支部名称
     * @return 修改成功过的记录条数
     */
    int updatePartyBranch (PartyBranchEntity partyBranchEntity);

    /**
     * 删除党支部
     * @param uuid 党支部唯一标识
     * @return 删除成功的记录条数
     */
    int deletePartyBranch (@Param("id") String uuid);
}

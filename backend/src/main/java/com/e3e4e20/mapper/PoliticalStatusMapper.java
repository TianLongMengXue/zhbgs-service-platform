package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.PoliticalStatusEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: PoliticalStatusMapper
Created: 2023年04月27日 10时02分27秒 星期四
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface PoliticalStatusMapper {
    /**
     * 检索全部的政治面貌
     * @return 全部的政治面貌
     */
    List<PoliticalStatusEntity> selectAllPoliticalStatus();

    /**
     * 根据政治面貌名称唯一标识检索政治面貌名称
     * @param uuid 政治面貌名称唯一标识
     * @return 政治面貌名称
     */
    PoliticalStatusEntity selectPoliticalStatusById(@Param("id") String uuid);

    /**
     * 修改政治面貌
     * @param politicalStatusEntity 政治面貌唯一标识和政治面貌
     * @return 修改成功的记录条数
     */
    int updatePoliticalStatus (PoliticalStatusEntity politicalStatusEntity);

    /**
     * 添加政治面貌
     * @param politicalStatusEntity 政治面貌的唯一标识和政治面貌
     * @return 添加成功的记录条数
     */
    int insertPoliticalStatus (PoliticalStatusEntity politicalStatusEntity);

    /**
     * 删除政治面貌
     * @param uuid 政治面貌唯一标识
     * @return 删除成功的记录条数
     */
    int deletePoliticalStatus (@Param("id") String uuid);
}

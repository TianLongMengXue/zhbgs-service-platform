package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.PostEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: PostMapper
Created: 2023年04月27日 10时51分24秒 星期四
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface PostMapper {
    /**
     * 检索全部的岗位名称
     *
     * @return 全部的岗位名称
     */
    List<PostEntity> selectAllPost();

    /**
     * 根据岗位唯一标识检索岗位名称
     *
     * @param uuid 岗位唯一标识
     * @return 岗位名称
     */
    String selectPostNameById(@Param("id") String uuid);

    /**
     * 根据岗位唯一标识检索岗位信息
     * @param uuid 岗位唯一标识
     * @return 岗位信息
     */
    PostEntity selectPostById(@Param("id") String uuid);

    /**
     * 修改岗位名称
     *
     * @param postEntity 岗位名称和岗位唯一标识
     * @return 修改成功的记录条数
     */
    Integer updatePostName(PostEntity postEntity);

    /**
     * 添加岗位
     *
     * @param postEntity 岗位名称和岗位唯一标识
     * @return 添加成功的记录条数
     */
    Integer insertPost(PostEntity postEntity);

    /**
     * 删除岗位
     *
     * @param uuid 岗位唯一标识
     * @return 删除成功的记录条数
     */
    Integer deletePost(@Param("id") String uuid);
}

package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.PostEntity;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.mapper.PostMapper;
import com.e3e4e20.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/*
Filename: PostServiceImplement
Created: 2023年05月05日 09时47分07秒 星期五
Author: 天龙梦雪
*/
@Service(value = "postService")
public class PostServiceImplement implements PostService {
    @Resource(type = PostMapper.class)
    private PostMapper postMapper;
    private final Logger logger = LoggerFactory.getLogger("Class: PostServiceImplement ");

    @Override
    @Transactional
    public String getPostNameById(String uuid) {
        String postName = null;
        try {
            postName = postMapper.selectPostNameById(uuid);
        } catch (Exception exception) {
            logger.error("getPostNameById: " + exception.getMessage());
        }
        if (null != postName) {
            logger.debug("getPostNameById: 岗位唯一标识: " + uuid + ",对应的岗位名称: " + postName);
        } else {
            logger.error("getPostNameById: 不存在岗位唯一标识: " + uuid + ",对应的岗位名称!");
            throw new ErrorMessageException("请确认该岗位是否存在!");
        }
        return postName;
    }

    @Override
    public boolean postIsNotNull(String uuid) {
        PostEntity postInfo = null;
        try {
            postInfo = postMapper.selectPostById(uuid);
        } catch (Exception exception) {
            logger.error("postIsNotNull: " + exception.getMessage());
        }
        if (null != postInfo) {
            logger.debug("postIsNotNull: 岗位信息为: " + postInfo);
            return true;
        } else {
            logger.error("postIsNotNull: 不存在岗位唯一标识: " + uuid + ",对应的岗位!");
            return false;
        }
    }
}

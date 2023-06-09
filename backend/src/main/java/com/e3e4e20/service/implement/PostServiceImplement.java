package com.e3e4e20.service.implement;

import com.e3e4e20.mapper.PostMapper;
import com.e3e4e20.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    private final Logger logger = LoggerFactory.getLogger("Class:PostServiceImplement");

    @Override
    public String getPostNameById(String uuid) {
        String postName = null;
        try {
            postName = postMapper.selectPostById(uuid);
            logger.debug("岗位唯一标识:"+uuid+"对应的岗位名称:"+postName);
        } catch (Exception e) {
            logger.error("不存在岗位唯一标识:"+uuid+"对应的岗位名称!");
            return null;
        }
        return postName;
    }
}

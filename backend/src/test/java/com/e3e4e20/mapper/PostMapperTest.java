package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.PostEntity;
import com.e3e4e20.utils.Uuid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
Filename: PostMapperTest
Created: 2023年05月11日 17时01分49秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class PostMapperTest {
    @Autowired
    private PostMapper postMapper;
    @Test
    void testInsertItem () {
        String[] postName = {"在职在编人员","编外人员","离/退休人员","实习/见习生"};
        int result = 0, count = 0;
        for (String name : postName) {
            PostEntity postEntity = new PostEntity();
            postEntity.setId(new Uuid().createUuid());
            postEntity.setPostName(name);
            result = postMapper.insertPost(postEntity);
            count += result;
        }
        System.out.println("count="+count);
    }
    @Test
    void testSelectAll () {
        List<PostEntity> postEntityList = postMapper.selectAllPost();
        System.out.println(postEntityList.toString());
    }
}

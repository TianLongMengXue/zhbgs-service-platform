package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.RootMenuEntity;
import com.e3e4e20.utils.Uuid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/*
Filename: RootMenuMapperTest
Created: 2023年04月13日 11时09分55秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class RootMenuMapperTest {
    @Autowired
    private RootMenuMapper rootMenuMapper;
    @Test
    void testInsertMenuItem () {
        RootMenuEntity rootMenuEntity = new RootMenuEntity();
        rootMenuEntity.setId(new Uuid().createUuid());
        rootMenuEntity.setOrder(1);
        rootMenuEntity.setIcon("fa-flag");
        rootMenuEntity.setMenuInfo("党务工作");
        rootMenuEntity.setUrl("/console/party");
        rootMenuEntity.setMenuName("party");
        int result = rootMenuMapper.insertMenu(rootMenuEntity);
        System.out.println(rootMenuEntity.toString()+",result="+result);
    }
    @Test
    void testInsertMenuItem2 () {
        RootMenuEntity rootMenuEntity = new RootMenuEntity();
        rootMenuEntity.setId(new Uuid().createUuid());
        rootMenuEntity.setOrder(2);
        rootMenuEntity.setIcon("fa-institution");
        rootMenuEntity.setMenuInfo("政务工作");
        rootMenuEntity.setUrl("/console/chief");
        rootMenuEntity.setMenuName("chief");
        int result = rootMenuMapper.insertMenu(rootMenuEntity);
        System.out.println(rootMenuEntity.toString()+",result="+result);
    }
    @Test
    void testSelectAllMenuItem () {
        List<RootMenuEntity> rootMenuEntityList = rootMenuMapper.selectAllItem();
        System.out.println(rootMenuEntityList.toString());
    }
}

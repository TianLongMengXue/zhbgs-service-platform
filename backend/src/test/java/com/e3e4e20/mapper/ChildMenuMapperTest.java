package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.ChildMenuEntity;
import com.e3e4e20.utils.Uuid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
Filename: ChildMenuMapperTest
Created: 2023年05月11日 16时05分01秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class ChildMenuMapperTest {
    @Autowired
    private ChildMenuMapper childMenuMapper;

    @Test
    void testInsertChildMenu() {
        ChildMenuEntity childMenuEntity = new ChildMenuEntity();
        childMenuEntity.setUuid(new Uuid().createUuid());
        childMenuEntity.setMenuName("party-fee");
        childMenuEntity.setMenuInfo("党费");
        childMenuEntity.setOrder(2);
        childMenuEntity.setUrl("/console/party-fee");
        childMenuEntity.setParentId("bcec11ce207f40efa66a046970c2b480");
        childMenuEntity.setIcon("fa-rmb");
        int result = childMenuMapper.insertMenuItem(childMenuEntity);
        System.out.println(childMenuEntity.toString() + "result=" + result);
    }
    @Test
    void testInsertChildMenu2() {
        ChildMenuEntity childMenuEntity = new ChildMenuEntity();
        childMenuEntity.setUuid(new Uuid().createUuid());
        childMenuEntity.setMenuName("party-member");
        childMenuEntity.setMenuInfo("党员信息");
        childMenuEntity.setOrder(1);
        childMenuEntity.setUrl("/console/party-member");
        childMenuEntity.setParentId("bcec11ce207f40efa66a046970c2b480");
        childMenuEntity.setIcon("fa-address-book");
        int result = childMenuMapper.insertMenuItem(childMenuEntity);
        System.out.println(childMenuEntity.toString() + "result=" + result);
    }
    @Test
    void testSelectChildMenu () {
        List<ChildMenuEntity> childMenuEntityList = childMenuMapper.selectAllMenuItemByParentId("bcec11ce207f40efa66a046970c2b480");
        System.out.println(childMenuEntityList.toString());
    }
}

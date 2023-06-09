package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.PoliticalStatusEntity;
import com.e3e4e20.utils.Uuid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: PoliticalStatusMapperTest
Created: 2023年05月12日 09时42分01秒 星期五
Author: 天龙梦雪
*/
@SpringBootTest
public class PoliticalStatusMapperTest {
    @Autowired
    private PoliticalStatusMapper politicalStatusMapper;

    @Test
    void testInsertItem() {
        String[] politicalStatusName = {"中共党员", "中共预备党员", "共青团员", "民革党员", "民盟盟员", "民建会员", "民进会员", "农工党党员", "致公党党员", "九三学社社员", "台盟盟员", "无党派人士", "群众"};
        int result = 0, count = 0;
        for (String name : politicalStatusName) {
            PoliticalStatusEntity politicalStatusEntity = new PoliticalStatusEntity();
            politicalStatusEntity.setUuid(new Uuid().createUuid());
            politicalStatusEntity.setPoliticalStatus(name);
            result = politicalStatusMapper.insertPoliticalStatus(politicalStatusEntity);
            count += result;
        }
        System.out.println("count = " + count);
    }
}

package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.PartyBranchEntity;
import com.e3e4e20.utils.Uuid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: PartyBranchMapperTest
Created: 2023年05月12日 09时03分28秒 星期五
Author: 天龙梦雪
*/
@SpringBootTest
public class PartyBranchMapperTest {
    @Autowired
    private PartyBranchMapper partyBranchMapper;

    @Test
    void testInsertItem() {
        String[] partyBranchName = {"第一党支部", "第二党支部", "第三党支部", "第四党支部", "第五党支部"};
        int result = 0, count = 0;
        for (String name : partyBranchName) {
            PartyBranchEntity partyBranchEntity = new PartyBranchEntity();
            partyBranchEntity.setId(new Uuid().createUuid());
            partyBranchEntity.setPartyName(name);
            result = partyBranchMapper.insertPartyBranch(partyBranchEntity);
            count += result;
        }
        System.out.println("count = " + count);
    }
    @Test
    void testSelectAll () {
        System.out.println(partyBranchMapper.selectAllPartyBranch().toString());
    }
}

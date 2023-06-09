package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.UserInfoEntity;
import com.e3e4e20.utils.SnowFlake;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
Filename: UserInfoMapperTest
Created: 2023年04月13日 23时21分13秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    public void testAddUserinfo (){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserid(String.valueOf(new SnowFlake().nextId()));
        userInfoEntity.setName("荆辞雪");
        userInfoEntity.setPostId("0d81f1998367439f8fd11811cbc1eb11");
        userInfoEntity.setDepartmentId("d1c8ca699303419bb4bcad4111d2fb9a");
        userInfoEntity.setPoliticalStatusId("a90acfc1c91947e5a7a34c722879be66");
        userInfoEntity.setPartyBranchId("none");
        System.out.println(userInfoEntity.toString());
        int res = userInfoMapper.insertUserInfo(userInfoEntity);
        System.out.println(res);
    }
    @Test
    public void testSelectAllUserInfo () {
        List<UserInfoEntity> userInfoEntityList = userInfoMapper.selectAllUserInfo();
        System.out.println(userInfoEntityList.toString());
    }
    @Test
    void testSelectUserInfoByUserid () {
        System.out.println(userInfoMapper.selectUserInfoByUserid("1656857313497415680"));
    }
    @Test
    void testUpdateUserInfoByUserid () {
        /*SupernumeraryEntity userInfoEntity = new SupernumeraryEntity(
                "1646864467847360512",
                "雪梦龙天",
                "室公办合综",
                "员党共中",
                "生习见"
        );
        System.out.println(userInfoMapper.updateUserInfoByUserid(userInfoEntity));*/
    }
    @Test
    public void testDeleteUserInfoByUserid () {
        int res = userInfoMapper.deleteUserInfoByUserid("1646864911705378816");
        System.out.println(res);
    }
}

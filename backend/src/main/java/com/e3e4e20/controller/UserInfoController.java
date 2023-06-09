package com.e3e4e20.controller;

import com.e3e4e20.entity.mapper.UserInfoEntity;
import com.e3e4e20.exception.FailureMessageException;
import com.e3e4e20.service.*;
import com.e3e4e20.service.implement.*;
import com.e3e4e20.utils.ResponseData;
import com.e3e4e20.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Filename: UserInfoController
Created: 2023年04月11日 19时40分20秒 星期二
Author: 天龙梦雪
*/
@CrossOrigin
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Resource(name = "userInfoService")
    private final UserInfoService userInfoService = new UserInfoServiceImplement();
    @Resource(name = "loginService")
    private final LoginService loginService = new LoginServiceImplement();
    @Resource(name = "loginTimeService")
    private final LoginTimeService loginTimeService = new LoginTimeServiceImplement();
    @Resource(name = "departmentService")
    private final DepartmentService departmentService = new DepartmentServiceImplement();
    @Resource(name = "postService")
    private final PostService postService = new PostServiceImplement();
    @Resource(name = "politicalStatusService")
    private final PoliticalStatusService politicalStatusService = new PoliticalStatusServiceImplement();
    @Resource(name = "partyBranchService")
    private final PartyBranchService partyBranchService = new PartyBranchServiceImplement();
    private final Logger logger = LoggerFactory.getLogger("Class:UserInfoController");

    /**
     * 添加新成员的基本信息,这里默认添加的人员在数据库中没有重复,不做人员是否重复添加的校验
     *
     * @param userInfoWithoutUserid 新成员的基本信息
     * @return 成功与否
     */
    @PostMapping("/add")
    public ResponseData addUserInfo(@RequestBody Map<String, String> userInfoWithoutUserid) {
        logger.debug("addUserInfo:新增人员的基本信息:" + userInfoWithoutUserid.toString());
        // 封装实体类,前端传递的过来的数据中使用的均为对应的唯一标识字段
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        String userid = String.valueOf(new SnowFlake().nextId());
        logger.debug("addUserInfo:新增人员的唯一标识:" + userid);
        userInfoEntity.setUserid(userid);
        userInfoEntity.setName(String.valueOf(userInfoWithoutUserid.get("name")));
        userInfoEntity.setDepartmentId(String.valueOf(userInfoWithoutUserid.get("department")));
        userInfoEntity.setPoliticalStatusId(String.valueOf(userInfoWithoutUserid.get("politicalStatus")));
        userInfoEntity.setPostId(String.valueOf(userInfoWithoutUserid.get("position")));
        userInfoEntity.setPartyBranchId(String.valueOf(userInfoWithoutUserid.get("partyBranch")));
        logger.debug("addUserInfo:新增人员的实体类:" + userInfoEntity.toString());
        boolean result = false;
        try {
            result = userInfoService.addUserinfo(userInfoEntity);
        } catch (FailureMessageException e) {
            logger.error("addUserInfo:ERROR:" + e.toString());
        }
        if (result) {
            logger.debug("addUserInfo:" + userInfoEntity.toString() + ",添加人员信息成功!");
            return ResponseData.SUCCESS("添加人员信息成功!", null);
        } else {
            logger.error("addUserInfo:" + userInfoEntity.toString() + ",添加人员信息失败!");
            return ResponseData.ERROR("添加人员信息失败!");
        }
    }

    /**
     * 删除人员基本信息
     * 1、删除人员基本信息之前,需要检索该用户是否具有登录权限,若是有需要删除登录基本信息
     * 2、删除人员信息之前,还需要删除该人员的登录时间记录
     * 3、删除人员信息之前,还需要撤销该用户所有被授予的权限(暂未实现)
     *
     * @param userid 人员唯一标识
     * @return 成功与否
     */
    @PostMapping("/del")
    public ResponseData deleteUserInfo(String userid) {
        /*logger.debug("deleteUserInfo:需要删除人员信息的人员:" + userid);
        if (loginService.haveLoginPrivilege(userid)) {
            logger.debug("deleteUserInfo:人员:" + userid + "具有登录权限,先撤销登录权限!");
            if (!loginService.deleteLoginUser(userid)) {
                logger.error("deleteUserInfo:正在删除人员信息,但是该人员:" + userid + "具有登录权限,且撤销登录权限失败!");
                return ResponseData.ERROR("删除人员信息失败!");
            }
            if (!loginTimeService.terminateLoginTime(userid)) {
                logger.error("deleteUserInfo:正在删除人员信息,但是该人员:" + userid + "具有登录权限,且删除登录时间记录失败!");
                return ResponseData.ERROR("删除人员信息失败!");
            }
        }
        boolean result = false;
        try {
            result = userInfoService.deleteUserInfo(userid);
        } catch (FailureMessageException e) {
            logger.error("deleteUserInfo:" + e.toString());
        }
        if (result) {
            logger.debug("deleteUserInfo:删除人员:" + userid + "的基本信息成功!");
            return ResponseData.SUCCESS("删除人员信息成功!", null);
        } else {
            logger.error("deleteUserInfo:删除人员:" + userid + "的基本信息失败!");
            return ResponseData.ERROR("删除人员信息失败!");
        }*/
        return null;
    }

    /**
     * 修改用户基本信息
     *
     * @param userInfoEntity 用户的基本信息
     * @return 成功与否
     */
    @PostMapping("/alter")
    public ResponseData alterUserInfo(@RequestBody UserInfoEntity userInfoEntity) {
        logger.debug("alterUserInfo:" + userInfoEntity.toString());
        boolean result = false;
        try {
            result = userInfoService.modifyUserInfoById(userInfoEntity);
        } catch (FailureMessageException e) {
            logger.error("alterUserInfo:" + e.toString());
        }
        if (result) {
            logger.debug("alterUserInfo:人员:" + userInfoEntity.getUserid() + ",修改信息成功!");
            return ResponseData.SUCCESS("修改信息成功!", null);
        } else {
            logger.error("alterUserInfo:人员:" + userInfoEntity.getUserid() + "修改信息失败!");
            return ResponseData.ERROR("修改信息失败!");
        }
    }

    /**
     * 获取指定登录人员信息,用在本系统登录成功后首页显示
     * 1、检索用户信息表
     * 2、根据部门唯一标识获取部门名称
     * 3、根据岗位唯一标识获取岗位名称
     * 4、根据政治面貌唯一标识获取政治面貌名称
     * 5、根据党支部唯一标识获取党支部名称（非党员没有所属党支部,实习/见习生没有所属党支部）
     * 6、获取人员上一次登录时间
     * 7、获取人员可操作的菜单表项
     * 8、获取用户头像图片（需要设置默认的图片作为人员头像）,这里需要将application.yaml中的
     *    图片路径和数据库中的图片名称拼接,然后转换为base64编码,返回给前端
     *
     * @param userid 人员唯一标识
     * @return 人员的基本信息
     */
    @PostMapping("/get")
    public ResponseData getUserInfo(String userid) throws ParseException {
        logger.debug("getUserInfo:获取人员:" + userid + "的基本信息!");
        UserInfoEntity userInfoEntity = userInfoService.getUserInfoByUserid(userid);
        if (null == userInfoEntity) {
            logger.error("getUserInfo:人员:" + userid + "的基本信息不存在!");
            return ResponseData.ERROR("获取信息失败!");
        } else {
            logger.debug("getUserInfo:人员:" + userid + "的基本信息为" + userInfoEntity);
            Map<String, String> userInfo = new HashMap<>();
            // userinfo.put("id", userInfoEntity.getUserid());
            userInfo.put("name", userInfoEntity.getName());
            userInfo.put("department", departmentService.getDepartmentNameById(userInfoEntity.getDepartmentId()));
            userInfo.put("time", loginTimeService.getLastLoginTime(userInfoEntity.getUserid()));
            userInfo.put("post", postService.getPostNameById(userInfoEntity.getPostId()));
            String politicalStatusName = politicalStatusService.getPoliticalStatusName(userInfoEntity.getPoliticalStatusId());
            userInfo.put("politicalStatus", politicalStatusName);
            if ((politicalStatusName.equals("中共党员")
                    || politicalStatusName.equals("中共预备党员"))
                    && !userInfoEntity.getPartyBranchId().equals("none")) {
                userInfo.put("partyBranch", partyBranchService.getPartyBranchName(userInfoEntity.getPartyBranchId()));
            }
            return ResponseData.SUCCESS("获取信息成功!", userInfo);
        }
    }

    /**
     * 获取本系统内存储的全部人员的基本信息
     * 1、检索用户信息表
     * 2、根据部门唯一标识获取部门名称（离/退休人员没有所属部门）
     * 3、根据岗位唯一标识获取岗位名称
     * 4、根据政治面貌唯一标识获取政治面貌名称
     * 5、根据党支部唯一标识获取党支部名称（非党员没有所属党支部）
     *
     * @return 全部人员的基本信息
     */
    @PostMapping("/all")
    public ResponseData getAllUserInfo() {
        logger.debug("/userinfo/all:获取本系统内存储的全部人员信息!");
        List<UserInfoEntity> userInfoEntityLists = userInfoService.selectAllUserInfo();
        if (null == userInfoEntityLists || 0 == userInfoEntityLists.size()) {
            logger.error("获取本系统内存储的全部人员信息失败!");
            return ResponseData.ERROR("获取人员信息失败");
        } else {
            List<Map<String, String>> userInfoLists = new ArrayList<>();
            for (UserInfoEntity userInfoEntity : userInfoEntityLists) {
                Map<String, String> userInfo = new HashMap<>();
                // userinfo.put("id", userInfoEntity.getUserid());
                userInfo.put("name", userInfoEntity.getName());
                userInfo.put("department", departmentService.getDepartmentNameById(userInfoEntity.getDepartmentId()));
                userInfo.put("post", postService.getPostNameById(userInfoEntity.getPostId()));
                String politicalStatusName = politicalStatusService.getPoliticalStatusName(userInfoEntity.getPoliticalStatusId());
                userInfo.put("politicalStatus", politicalStatusName);
                if (politicalStatusName.contains("党员") || userInfoEntity.getPartyBranchId() != null) {
                    userInfo.put("partyBranch", partyBranchService.getPartyBranchName(userInfoEntity.getPartyBranchId()));
                }
                userInfoLists.add(userInfo);
            }
            return ResponseData.SUCCESS("获取本系统内存储的全部人员信息成功!", userInfoLists);
        }
    }
}
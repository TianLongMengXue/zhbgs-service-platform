package com.e3e4e20.controller;

import com.e3e4e20.entity.mapper.UserInfoEntity;
import com.e3e4e20.exception.FailureMessageException;
import com.e3e4e20.service.*;
import com.e3e4e20.service.implement.*;
import com.e3e4e20.utils.ResponseData;
import com.e3e4e20.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"人员信息管理"}, value = "人员信息管理控制器")
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
    private final Logger log = LoggerFactory.getLogger("Class: UserInfoController ");

    /**
     * 添加新成员的基本信息,这里默认添加的人员在数据库中没有重复,不做人员是否重复添加的校验
     * 1、校验所属部门/政治面貌/岗位信息/所属党支部的唯一标识是否存在
     * 2、封装人员基本信息实体类,并执行数据库存储
     *
     * @param userInfoWithoutUserid 新成员的基本信息
     * @return 成功与否
     */
    @PostMapping("/add")
    @ApiOperation("添加人员基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名"),
            @ApiImplicitParam(name = "department", value = "所属部门"),
            @ApiImplicitParam(name = "politicalStatus", value = "政治面貌"),
            @ApiImplicitParam(name = "post", value = "岗位名称"),
            @ApiImplicitParam(name = "partyBranch", value = "所属党支部")
    })
    public ResponseData addUserInfo(@RequestBody Map<String, String> userInfoWithoutUserid) {
        log.info("addUserInfo: 新增人员的基本信息 : " + userInfoWithoutUserid.toString());
        // 获取需要检验的信息: 部门、政治面貌、岗位名称、所属党支部
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName(String.valueOf(userInfoWithoutUserid.get("name")));
        // 校验部门信息
        userInfoEntity.setDepartment(String.valueOf(userInfoWithoutUserid.get("department")));
        if (!departmentService.departmentIsNotNull(userInfoEntity.getDepartment())) {
            log.error("addUserInfo: 指定: " + userInfoEntity.getDepartment() + "部门不存在!");
            throw new FailureMessageException("新增人员信息失败,请确认部门是否正确!");
        }
        // 校验政治面貌
        userInfoEntity.setPoliticalStatus(String.valueOf(userInfoWithoutUserid.get("politicalStatus")));
        if (!politicalStatusService.politicalStatusIsNotNull(userInfoEntity.getPoliticalStatus())) {
            log.error("addUserInfo: 指定: " + userInfoEntity.getPoliticalStatus() + ",相关信息不存在!");
            throw new FailureMessageException("新增人员信息失败,请确认填写的政治面貌信息是否正确!");
        }
        // 校验岗位
        userInfoEntity.setPost(String.valueOf(userInfoWithoutUserid.get("post")));
        if (!postService.postIsNotNull(userInfoEntity.getPost())) {
            log.error("addUserInfo: 指定: " + userInfoEntity.getPost() + ",相关信息不存在!");
            throw new FailureMessageException("新增人员信息失败,请确认填写的岗位信息是否正确!");
        }
        // 校验党支部
        userInfoEntity.setPartyBranch(String.valueOf(userInfoWithoutUserid.get("partyBranch")));
        // 没有所属党支部(none),就不需要执行校验
        if (!userInfoEntity.getPartyBranch().equals("none")) {
            // 校验所属党支部是否存在
            if (!partyBranchService.partyBranchIsNotNull(userInfoEntity.getPartyBranch())) {
                log.error("addUserInfo: 指定: " + userInfoEntity.getPartyBranch() + ",相关信息不存在!");
                throw new FailureMessageException("新增人员信息失败,请确认填写的党支部名称是否正确!");
            }
        }
        // 封装实体类,前端传递的过来的数据中使用的均为对应的唯一标识字段
        String userid = String.valueOf(new SnowFlake().nextId());
        log.info("addUserInfo: 新增人员的唯一标识: " + userid);
        userInfoEntity.setId(userid);
        log.info("addUserInfo: 新增人员的实体类: " + userInfoEntity);
        userInfoService.addUserinfo(userInfoEntity);
        return ResponseData.SUCCESS("新增人员信息成功!", userInfoEntity);
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
        /*log.info("deleteUserInfo:需要删除人员信息的人员:" + userid);
        if (loginService.haveLoginPrivilege(userid)) {
            log.debug("deleteUserInfo:人员:" + userid + "具有登录权限,先撤销登录权限!");
            if (!loginService.deleteLoginUser(userid)) {
                log.error("deleteUserInfo:正在删除人员信息,但是该人员:" + userid + "具有登录权限,且撤销登录权限失败!");
                return ResponseData.ERROR("删除人员信息失败!");
            }
            if (!loginTimeService.terminateLoginTime(userid)) {
                log.error("deleteUserInfo:正在删除人员信息,但是该人员:" + userid + "具有登录权限,且删除登录时间记录失败!");
                return ResponseData.ERROR("删除人员信息失败!");
            }
        }
        boolean result = false;
        try {
            result = userInfoService.deleteUserInfo(userid);
        } catch (FailureMessageException e) {
            log.error("deleteUserInfo:" + e.toString());
        }
        if (result) {
            log.debug("deleteUserInfo:删除人员:" + userid + "的基本信息成功!");
            return ResponseData.SUCCESS("删除人员信息成功!", null);
        } else {
            log.error("deleteUserInfo:删除人员:" + userid + "的基本信息失败!");
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
        log.debug("alterUserInfo:" + userInfoEntity.toString());
        boolean result = false;
        try {
            result = userInfoService.modifyUserInfoById(userInfoEntity);
        } catch (FailureMessageException e) {
            log.error("alterUserInfo:" + e.toString());
        }
        if (result) {
            log.debug("alterUserInfo:人员:" + userInfoEntity.getId() + ",修改信息成功!");
            return ResponseData.SUCCESS("修改信息成功!", null);
        } else {
            log.error("alterUserInfo:人员:" + userInfoEntity.getId() + "修改信息失败!");
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
     * 图片路径和数据库中的图片名称拼接,然后转换为base64编码,返回给前端
     *
     * @param userid 人员唯一标识
     * @return 人员的基本信息
     */
    @PostMapping("/get")
    public ResponseData getUserInfo(String userid) throws ParseException {
        log.debug("getUserInfo:获取人员:" + userid + "的基本信息!");
        UserInfoEntity userInfoEntity = userInfoService.getUserInfoByUserid(userid);
        if (null == userInfoEntity) {
            log.error("getUserInfo:人员:" + userid + "的基本信息不存在!");
            return ResponseData.ERROR("获取信息失败!");
        } else {
            log.debug("getUserInfo:人员:" + userid + "的基本信息为" + userInfoEntity);
            Map<String, String> userInfo = new HashMap<>();
            // userinfo.put("id", userInfoEntity.getUserid());
            userInfo.put("name", userInfoEntity.getName());
            userInfo.put("department", departmentService.getDepartmentNameById(userInfoEntity.getDepartment()));
            userInfo.put("time", loginTimeService.getLastLoginTime(userInfoEntity.getId()));
            userInfo.put("post", postService.getPostNameById(userInfoEntity.getPost()));
            String politicalStatusName = politicalStatusService.getPoliticalStatusName(userInfoEntity.getPoliticalStatus());
            userInfo.put("politicalStatus", politicalStatusName);
            if ((politicalStatusName.equals("中共党员")
                    || politicalStatusName.equals("中共预备党员"))
                    && !userInfoEntity.getPartyBranch().equals("none")) {
                userInfo.put("partyBranch", partyBranchService.getPartyBranchName(userInfoEntity.getPartyBranch()));
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
        log.debug("/userinfo/all:获取本系统内存储的全部人员信息!");
        List<UserInfoEntity> userInfoEntityLists = userInfoService.selectAllUserInfo();
        if (null == userInfoEntityLists || 0 == userInfoEntityLists.size()) {
            log.error("获取本系统内存储的全部人员信息失败!");
            return ResponseData.ERROR("获取人员信息失败");
        } else {
            List<Map<String, String>> userInfoLists = new ArrayList<>();
            for (UserInfoEntity userInfoEntity : userInfoEntityLists) {
                Map<String, String> userInfo = new HashMap<>();
                // userinfo.put("id", userInfoEntity.getUserid());
                userInfo.put("name", userInfoEntity.getName());
                userInfo.put("department", departmentService.getDepartmentNameById(userInfoEntity.getDepartment()));
                userInfo.put("post", postService.getPostNameById(userInfoEntity.getPost()));
                String politicalStatusName = politicalStatusService.getPoliticalStatusName(userInfoEntity.getPoliticalStatus());
                userInfo.put("politicalStatus", politicalStatusName);
                if (politicalStatusName.contains("党员") || userInfoEntity.getPartyBranch() != null) {
                    userInfo.put("partyBranch", partyBranchService.getPartyBranchName(userInfoEntity.getPartyBranch()));
                }
                userInfoLists.add(userInfo);
            }
            return ResponseData.SUCCESS("获取本系统内存储的全部人员信息成功!", userInfoLists);
        }
    }
}

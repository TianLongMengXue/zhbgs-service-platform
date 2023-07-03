package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.LoginTimeEntity;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.mapper.LoginTimeMapper;
import com.e3e4e20.service.LoginTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
Filename: LoginTimeServiceImplement
Created: 2023年05月08日 16时05分57秒 星期一
Author: 天龙梦雪
*/
@Service(value = "loginTimeService")
public class LoginTimeServiceImplement implements LoginTimeService {
    @Resource(type = LoginTimeMapper.class)
    private LoginTimeMapper loginTimeMapper;
    private final Logger log = LoggerFactory.getLogger("Class: LoginTimeServiceImplement ");

    @Override
    public String getLastLoginTime(String userid) {
        List<LoginTimeEntity> loginTimeList = getAllLoginTime(userid);
        if (0 == loginTimeList.size()) {
            log.info("getLastLoginTime: 人员: " + userid + ",当前为第一次登录,没有记录登录时间!");
            return null;
        } else if (1 == loginTimeList.size()) {
            LocalDateTime dateTime = loginTimeList.get(0).getTime();
            String loginTime = getFormatterDateTime(dateTime);
            log.info("getLastLoginTime: 人员:" + userid + ",历史登录次数仅一次,登录时间为: " + loginTime);
            return loginTime;
        } else {
            int index = getLastLoginTimeIndex(loginTimeList.get(0).getTime(), loginTimeList.get(1).getTime());
            LocalDateTime dateTime = loginTimeList.get(index).getTime();
            String lastLoginTime = getFormatterDateTime(dateTime);
            log.info("getLastLoginTime: 人员: " + userid + ",最近一次登录时间为: " + lastLoginTime);
            return lastLoginTime;
        }
    }

    @Override
    public boolean recordLoginTime(String userid) throws ErrorMessageException {
        // 获取当前人员全部的登录时间
        List<LoginTimeEntity> loginTimeList = getAllLoginTime(userid);
        // 获取当前时间
        LocalDateTime dateTimeNow = getCurrentDateTime();
        String loginTimeNow = getFormatterDateTime(dateTimeNow);
        // result 表示添加/修改登录时间是否成功,成功为 1 失败为 0
        int result = 0;
        // 若是当前人员的没有登录时间记录,那么就说明当前人员是第一次登录
        boolean firstLogin = (null == loginTimeList || 0 == loginTimeList.size());
        log.info("recordLoginTime: 当前是否为第一次登录: " + firstLogin);
        // 若是当前人员只有一次登录时间记录,那么就说明当前人员是第二次登录
        boolean secondLogin = false;
        // 这里的 if 是为了防止登录时间列表为空,导致的空指针错误
        if (!firstLogin) {
            secondLogin = (1 == loginTimeList.size());
        }
        log.info("recordLoginTime: 当前是否为第二次登录: " + secondLogin);
        if (firstLogin || secondLogin) {
        /*if ((null == loginTimeList) || (0 == loginTimeList.size()) || (1 == loginTimeList.size())) {*/
            if (firstLogin) {
            /*if ((null == loginTimeList) || (0 == loginTimeList.size())) {*/
                log.info("recordLoginTime: 人员: " + userid + ",当前为第一次登录,记录第一次登录时间: " + loginTimeNow);
            }
            if (secondLogin) {
            /*else {*/
                LocalDateTime dateTime = loginTimeList.get(0).getTime();
                String loginTime = getFormatterDateTime(dateTime);
                log.info("recordLoginTime: 人员: " + userid + "历史登录次数仅一次,登录时间为: " + loginTime + "," +
                        "记录第二次登录时间!");
            }
            try {
                result = loginTimeMapper.insertLoginTime(userid, dateTimeNow);
            } catch (Exception exception) {
                log.error("recordLoginTime: " + exception);
            }
            if (1 == result) {
                log.info("recordLoginTime: 为人员: " + userid + ",记录登录时间成功!");
                return true;
            } else {
                log.error("recordLoginTime: 为人员:" + userid + ",记录登录时间失败!");
                throw new ErrorMessageException("记录登录时间失败!");
            }
        } else {
            // 判断出获取的登录时间列表中,那一条记录是倒数第二次登录的时间记录
            int index = getLastLoginTimeIndex(loginTimeList.get(0).getTime(), loginTimeList.get(1).getTime());
            // 封装新的登录时间记录实体,用当前的登录时间替换登录时间列表中倒数第二次登录的时间记录
            LoginTimeEntity loginTimeEntity;
            loginTimeEntity = loginTimeList.get(index);
            loginTimeEntity.setTime(dateTimeNow);
            try {
                result = loginTimeMapper.updateLoginTime(loginTimeEntity);
            } catch (Exception exception) {
                log.error("recordLoginTime: " + exception);
            }
            if (1 == result) {
                log.debug("recordLoginTime: 修改人员: " + userid + ",最近两次登录时间中最久远的一次成功!");
                return true;
            } else {
                log.debug("recordLoginTime: 修改人员: " + userid + ",最近两次登录时间中最久远的一次失败!");
                throw new ErrorMessageException("记录登录时间失败!");
            }
        }
    }

    @Override
    public boolean terminateLoginTime(String userid) {
        List<LoginTimeEntity> loginTimeList = getAllLoginTime(userid);
        Integer number = loginTimeMapper.deleteLoginTime(userid);
        if (loginTimeList.size() != number) {
            log.error("terminateLoginTime: 删除的登录时间记录条数和查询到的登录时间条数不符,判定为删除登录时间记录失败!");
            return false;
        }
        if (0 == number) {
            log.debug("terminateLoginTime: 人员: " + userid + ",没有登录过本系统!");
        } else if (1 == number) {
            log.debug("terminateLoginTime: 人员: " + userid + ",仅登录过本系统一次!");
        } else {
            log.debug("terminateLoginTime: 人员: " + userid + ",最近两次登录时间为: " + loginTimeList.toString());
        }
        return true;
    }

    /**
     * 获取 LocalDateTime 当前的日期和时间
     *
     * @return LocalDateTime.now()
     */
    public LocalDateTime getCurrentDateTime() {
        // 获取当前时间
        return LocalDateTime.now();
    }

    /**
     * 将 LocalDateTime 类型的日期时间转换为 yyyy年MM月dd日 HH时mm分ss秒 格式的字符串
     *
     * @param dateTime LocalDateTime 类型的日期时间
     * @return yyyy年MM月dd日 HH时mm分ss秒 格式的字符串
     */
    public String getFormatterDateTime(LocalDateTime dateTime) {
        // 将时间转换为指定的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        return dateTime.format(formatter);
    }

    /**
     * 由于记录登录时间的时候,会将除了上一次登录的时间记录全部删除,确保每一个人员只保存最近两条登录时间记录,
     * 即一条本次登录的时间记录,一条上一次的登录时间记录
     * 由于人员在登录的时候,就会记录本次登录时间,并删除上一次登录时间之前的记录,
     * 所以当获取人员的登录时间记录时,登录时间最近的一次是本次登录的时间记录
     *
     * @param dateTime1 LocalDateTime 类型的日期时间
     * @param dateTime2 LocalDateTime 类型的日期时间
     * @return 两个 LocalDateTime 类型的日期时间中时间最久远的一次,若是不能判断则返回 null
     */
    private Integer getLastLoginTimeIndex(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        if (dateTime1.isAfter(dateTime2)) { // dateTime1 在 dateTime2 之后,那么 dateTime2 时间的更早（最久远）
            return 1;
        } else if (dateTime1.isBefore(dateTime2)) {
            return 0;
        } else {
            return 0;
        }
    }

    /**
     * 获取指定人员全部的登录时间
     *
     * @param userid 人员唯一标识
     * @return 指定人员全部的登录时间
     */
    public List<LoginTimeEntity> getAllLoginTime(String userid) {
        List<LoginTimeEntity> loginTimeList = null;
        try {
            loginTimeList = loginTimeMapper.selectAllItemByUserid(userid);
            if (null == loginTimeList || 0 == loginTimeList.size()) {
                log.debug("getAllLoginTime: 人员: " + userid + ",当前为第一次登录,没有记录登录时间!");
            } else {
                log.debug("人员: " + userid + "全部的登录时间为: " + loginTimeList);
            }
        } catch (Exception exception) {
            log.error("getAllLoginTime: " + exception.getMessage());
            log.debug("getAllLoginTime: 人员: " + userid + ",查询登录时间记录失败!");
        }
        return loginTimeList;
    }
}

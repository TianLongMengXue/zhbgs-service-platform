package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.PoliticalStatusEntity;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.mapper.PoliticalStatusMapper;
import com.e3e4e20.service.PoliticalStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/*
Filename: PoliticalStatusServiceImplement
Created: 2023年05月10日 17时14分55秒 星期三
Author: 天龙梦雪
*/
@Service(value = "politicalStatusService")
public class PoliticalStatusServiceImplement implements PoliticalStatusService {
    @Resource(type = PoliticalStatusMapper.class)
    private PoliticalStatusMapper politicalStatusMapper;
    private final Logger log = LoggerFactory.getLogger("Class: PoliticalStatusServiceImplement ");

    @Override
    @Transactional
    public String getPoliticalStatusName(String uuid) {
        String politicalStatusName = null;
        try {
            politicalStatusName = politicalStatusMapper.selectPoliticalStatusNameById(uuid);
        } catch (Exception exception) {
            log.error("getPoliticalStatusName: " + exception.getMessage());
        }
        if (null == politicalStatusName) {
            log.error("getPoliticalStatusName: 指定的政治面貌: " + uuid + "是不存在的!");
            throw new ErrorMessageException("请确认政治面貌是否正确!");
        } else {
            log.info("getPoliticalStatusName: 政治面貌: " + uuid + ",指定的名称为: " + politicalStatusName);
            return politicalStatusName;
        }
    }

    @Override
    @Transactional
    public boolean politicalStatusIsNotNull(String uuid) {
        PoliticalStatusEntity politicalStatusInfo = null;
        try {
            politicalStatusInfo = politicalStatusMapper.selectPoliticalStatusById(uuid);
        } catch (Exception exception) {
            log.error("politicalStatusIsNotNull: " + exception.getMessage());
        }
        if (null == politicalStatusInfo) {
            log.error("politicalStatusIsNotNull: 指定的政治面貌: " + uuid + "是不存在的!");
            return false;
        } else {
            log.info("politicalStatusIsNotNull: 政治面貌信息为:" + politicalStatusInfo);
            return true;
        }
    }
}

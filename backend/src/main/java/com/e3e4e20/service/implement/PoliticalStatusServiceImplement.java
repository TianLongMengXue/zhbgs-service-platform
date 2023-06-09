package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.PoliticalStatusEntity;
import com.e3e4e20.mapper.PoliticalStatusMapper;
import com.e3e4e20.service.PoliticalStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    private final Logger logger = LoggerFactory.getLogger("Class:PoliticalStatusServiceImplement");
    @Override
    public String getPoliticalStatusName(String uuid) {
        PoliticalStatusEntity politicalStatusEntity = null;
        try {
            politicalStatusEntity = politicalStatusMapper.selectPoliticalStatusById(uuid);
        } catch (Exception ignore) {
        }
        if (null == politicalStatusEntity){
            logger.error("政治面貌:"+uuid+"不存在");
            return null;
        } else {
            String politicalStatusName = politicalStatusEntity.getPoliticalStatus();
            logger.debug("政治面貌:"+uuid+",指定的名称为:"+politicalStatusName);
            return politicalStatusName;
        }
    }
}

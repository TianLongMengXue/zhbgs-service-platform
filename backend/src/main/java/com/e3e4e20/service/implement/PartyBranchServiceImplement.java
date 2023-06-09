package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.PartyBranchEntity;
import com.e3e4e20.mapper.PartyBranchMapper;
import com.e3e4e20.service.PartyBranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
Filename: PartyBranchServiceImplement
Created: 2023年05月10日 17时26分04秒 星期三
Author: 天龙梦雪
*/
@Service(value = "partyBranchService")
public class PartyBranchServiceImplement implements PartyBranchService {
    @Resource(type = PartyBranchMapper.class)
    private PartyBranchMapper partyBranchMapper;
    private final Logger logger = LoggerFactory.getLogger("Class:PartyBranchServiceImplement");
    @Override
    public String getPartyBranchName(String uuid) {
        PartyBranchEntity partyBranchEntity = null;
        try {
            partyBranchEntity = partyBranchMapper.selectPartyBranchById(uuid);
        } catch (Exception ignore) {
        }
        if (null == partyBranchEntity) {
            logger.error("指定党支部:"+uuid+"不存在!");
            return null;
        } else {
            String partyBranchName = partyBranchEntity.getPartyName();
            logger.debug("指定党支部:"+uuid+",名称为:"+partyBranchName);
            return partyBranchName;
        }
    }
}

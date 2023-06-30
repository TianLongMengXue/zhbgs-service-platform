package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.PartyBranchEntity;
import com.e3e4e20.mapper.PartyBranchMapper;
import com.e3e4e20.service.PartyBranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final Logger logger = LoggerFactory.getLogger("Class: PartyBranchServiceImplement ");

    @Override
    @Transactional
    public String getPartyBranchName(String uuid) {
        String partyBranchName = null;
        try {
            partyBranchName = partyBranchMapper.selectPartyBranchNameById(uuid);
        } catch (Exception exception) {
            logger.error("getPartyBranchName: "+exception.getMessage());
        }
        if (null == partyBranchName) {
            logger.error("getPartyBranchName: 指定党支部: " + uuid + ",不存在!");
            return null;
        } else {
            logger.debug("getPartyBranchName: 指定党支部:" + uuid + ",名称为: " + partyBranchName);
            return partyBranchName;
        }
    }

    @Override
    @Transactional
    public boolean partyBranchIsNotNull(String uuid) {
        PartyBranchEntity partyBranchInfo = null;
        try {
            partyBranchInfo = partyBranchMapper.selectPartyBranchById(uuid);
        } catch (Exception exception) {
            logger.error("partyBranchIsNotNull: "+exception.getMessage());
        }
        if (null == partyBranchInfo) {
            logger.error("partyBranchIsNotNull: 指定党支部: " + uuid + ",不存在!");
            return false;
        } else {
            logger.debug("partyBranchIsNotNull: 指定党支部信息为: " + partyBranchInfo);
            return true;
        }
    }
}

package com.e3e4e20.controller;

import com.e3e4e20.constant.ResultMessage;
import com.e3e4e20.entity.mapper.PowerEntity;
import com.e3e4e20.service.PowerService;
import com.e3e4e20.service.implement.PowerServiceImplement;
import com.e3e4e20.utils.ResponseData;
import org.springframework.web.bind.annotation.*;


/*
Filename: PowerController
Created: 2023年04月19日 09时42分43秒 星期三
Author: 天龙梦雪
*/
@CrossOrigin
@RestController
@RequestMapping("/power")
public class PowerController {
    private final PowerService powerService = new PowerServiceImplement();

    @PostMapping("check")
    public ResponseData checkPower(@RequestBody PowerEntity powerEntity) {
        if (powerService.checkPower(powerEntity)) {
            return ResponseData.SUCCESS(ResultMessage.CEHCK_USER_POWER_SUCCESS, null);
        } else {
            return ResponseData.ERROR(ResultMessage.CHECK_USER_POWER_FAILURE);
        }
    }
    @PostMapping("grant")
    public ResponseData grantPower (@RequestBody PowerEntity powerEntity) {
        if (powerService.grantPower(powerEntity)) {
            return ResponseData.SUCCESS(ResultMessage.GRANT_USER_POWER_SUCCESS, null);
        } else {
            return ResponseData.ERROR(ResultMessage.GRANT_USER_POWER_FAILURE);
        }
    }
    @PostMapping("annul")
    public ResponseData annulPower (@RequestBody PowerEntity powerEntity) {
        if (powerService.annulPower(powerEntity)) {
            return ResponseData.SUCCESS(ResultMessage.ANNUL_USER_POWER_SUCCESS, null);
        } else {
            return ResponseData.ERROR(ResultMessage.ANNUL_USER_POWER_FAILURE);
        }
    }
}

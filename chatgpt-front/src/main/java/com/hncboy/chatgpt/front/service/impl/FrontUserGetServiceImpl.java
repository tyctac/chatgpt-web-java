package com.hncboy.chatgpt.front.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hncboy.chatgpt.base.domain.entity.AccountBalanceDO;
import com.hncboy.chatgpt.base.enums.FrontUserRegisterTypeEnum;
import com.hncboy.chatgpt.base.service.AccountBalanceService;
import com.hncboy.chatgpt.base.util.SimpleCaptchaUtil;
import com.hncboy.chatgpt.front.domain.bo.JwtUserInfoBO;
import com.hncboy.chatgpt.front.domain.request.RegisterFrontUserForEmailRequest;
import com.hncboy.chatgpt.front.domain.vo.LoginInfoVO;
import com.hncboy.chatgpt.front.domain.vo.RegisterCaptchaVO;
import com.hncboy.chatgpt.front.domain.vo.UserBalanceVO;
import com.hncboy.chatgpt.front.domain.vo.UserInfoVO;
import com.hncboy.chatgpt.front.service.FrontUserGetService;
import com.hncboy.chatgpt.front.service.FrontUserService;
import com.hncboy.chatgpt.front.service.strategy.user.AbstractRegisterTypeStrategy;
import com.hncboy.chatgpt.front.util.FrontUserUtil;
import com.hncboy.chatgpt.front.util.ToolUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 前端用户服务
 * 用于处理注册/登录/获取用户信息等功能
 *
 * @author CoDeleven
 */
@Service
public class FrontUserGetServiceImpl implements FrontUserGetService {

    @Resource
    private   AccountBalanceService accountBalanceService;


    @Override
    public UserBalanceVO getUserBalance(FrontUserRegisterTypeEnum registerType, Integer extraInfoId){
        AccountBalanceDO balanceDo = accountBalanceService.getAccountBalanceByid(extraInfoId);


//        UserBalanceVO userBalanceVO = new UserBalanceVO();
//        userBalanceVO.setTokenLeft(balanceDo.getTokenLeft());
//        userBalanceVO.setBaseUserId(balanceDo.getUserId());
//        return userBalanceVO;
        UserBalanceVO userBalanceVO = ToolUtil.getBalance(balanceDo);
        return userBalanceVO;
    }

}

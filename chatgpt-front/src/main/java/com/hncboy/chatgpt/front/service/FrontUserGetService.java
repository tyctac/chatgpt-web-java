package com.hncboy.chatgpt.front.service;

import com.hncboy.chatgpt.base.enums.FrontUserRegisterTypeEnum;
import com.hncboy.chatgpt.front.domain.request.RegisterFrontUserForEmailRequest;
import com.hncboy.chatgpt.front.domain.vo.LoginInfoVO;
import com.hncboy.chatgpt.front.domain.vo.RegisterCaptchaVO;
import com.hncboy.chatgpt.front.domain.vo.UserBalanceVO;
import com.hncboy.chatgpt.front.domain.vo.UserInfoVO;

/**
 * 前端用户服务，提供注册、认证、授权等功能服务
 *
 * @author CoDeleven
 */
public interface FrontUserGetService {


    /**
     * get user balnace by uuid
     */
    UserBalanceVO getUserBalance(FrontUserRegisterTypeEnum registerType, Integer extraInfoId);

}

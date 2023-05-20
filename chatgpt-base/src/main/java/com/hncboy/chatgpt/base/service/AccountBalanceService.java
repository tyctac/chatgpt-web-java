package com.hncboy.chatgpt.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncboy.chatgpt.base.domain.entity.AccountBalanceDO;
import com.hncboy.chatgpt.base.domain.entity.ChatMessageDO;
import com.hncboy.chatgpt.base.domain.entity.EmailVerifyCodeDO;

/**
 * 邮箱服务
 *
 * @author CoDeleven
 */
public interface AccountBalanceService extends IService<AccountBalanceDO> {

    /**
     * get by id
     *
     * @param userid  验证码
     */
    AccountBalanceDO getAccountBalanceByid(Integer userid);

}
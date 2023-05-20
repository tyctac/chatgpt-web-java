package com.hncboy.chatgpt.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncboy.chatgpt.base.domain.entity.AccountBalanceDO;
import com.hncboy.chatgpt.base.mapper.AccountBalanceMapper;
import com.hncboy.chatgpt.base.service.AccountBalanceService;
import com.hncboy.chatgpt.base.service.EmailVerifyCodeService;
import org.springframework.stereotype.Service;

/**
 * 邮箱验证码核销记录业务实现类
 *
 * @author CoDeleven
 */
@Service
public class AccountBalanceServiceImpl extends ServiceImpl<AccountBalanceMapper, AccountBalanceDO>
        implements AccountBalanceService {


    @Override
    public AccountBalanceDO getAccountBalanceByid(Integer userid) {
        return getOne(new LambdaQueryWrapper<AccountBalanceDO>()
                .eq(AccountBalanceDO::getUserId, userid));

                //.gt(AccountBalanceDO::getExpireAt, new Date()));
    }

}





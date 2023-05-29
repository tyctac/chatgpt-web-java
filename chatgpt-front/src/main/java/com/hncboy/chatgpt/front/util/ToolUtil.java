package com.hncboy.chatgpt.front.util;

import com.hncboy.chatgpt.base.domain.entity.AccountBalanceDO;
import com.hncboy.chatgpt.base.enums.FrontUserBalanceTypeEnum;
import com.hncboy.chatgpt.front.domain.vo.UserBalanceVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ToolUtil {
    public static boolean checkVIPExpired(AccountBalanceDO accountBalanceDO) {
        int tokenLeft = accountBalanceDO.getTokenLeft();
        FrontUserBalanceTypeEnum balanceTypeEnum = accountBalanceDO.getBalanceType();
        String oriDate = accountBalanceDO.getOriginalDate();
        int daysTotal = accountBalanceDO.getDaysTotal();
        if (balanceTypeEnum == FrontUserBalanceTypeEnum.TOKEN) {
            if (tokenLeft > 0) {
                return true;
            }
            else {
                return false;
            }
        } else {
            try {
                Date oriDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oriDate);
                Date current = new Date();
                long miliseconds = current.getTime() - oriDt.getTime();
                long diffDay =  TimeUnit.DAYS.convert(miliseconds, TimeUnit.MILLISECONDS);
                if (diffDay > daysTotal){
                    return false;
                }
            }
            catch (ParseException e) {
                return false;
            }
        }

        return true;
    }

    public static UserBalanceVO getBalance(AccountBalanceDO accountBalanceDO) {
        UserBalanceVO userBalanceVO = new UserBalanceVO();
        int tokenLeft = accountBalanceDO.getTokenLeft();
        FrontUserBalanceTypeEnum balanceTypeEnum = accountBalanceDO.getBalanceType();
        String oriDate = accountBalanceDO.getOriginalDate();
        int daysTotal = accountBalanceDO.getDaysTotal();
        userBalanceVO.setBalanceType(balanceTypeEnum);
        if (balanceTypeEnum == FrontUserBalanceTypeEnum.TOKEN) {
            userBalanceVO.setTokenLeft(tokenLeft);
            return userBalanceVO;
        } else {
            try {
                Date oriDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oriDate);
                Date current = new Date();
                long miliseconds = current.getTime() - oriDt.getTime();
                int diffDay = (int) TimeUnit.DAYS.convert(miliseconds, TimeUnit.MILLISECONDS);

                userBalanceVO.setDays(daysTotal - diffDay);
                return userBalanceVO;
            }
            catch (ParseException e) {
                userBalanceVO.setDays((int) daysTotal);
                return userBalanceVO;
            }
        }

    }

}

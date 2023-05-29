package com.hncboy.chatgpt.base.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.hncboy.chatgpt.base.enums.FrontUserBalanceTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hncboy
 * @date 2023/3/25 16:19
 * 聊天记录表实体类
 */
@Data
@TableName("account_balance")
public class AccountBalanceDO {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;


    /**
     * tokenLeft
     */
    private Integer tokenLeft;


    /**
     * balanceType
     */
    private FrontUserBalanceTypeEnum balanceType;


    /**
     * daysLeft
     */
    private Integer daysLeft;

    /**
     * daysTotal
     */
    private Integer daysTotal;


    /**
     * originalData
     */
    private String originalDate;
}

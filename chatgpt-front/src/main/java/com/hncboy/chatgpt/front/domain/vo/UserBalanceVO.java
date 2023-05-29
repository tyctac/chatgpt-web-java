package com.hncboy.chatgpt.front.domain.vo;

import com.hncboy.chatgpt.base.enums.FrontUserBalanceTypeEnum;
import com.hncboy.chatgpt.base.enums.FrontUserRegisterTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录成功返回用户信息
 *
 * @author CoDeleven
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(title = "登录成功后返回前端用户信息")
public class UserBalanceVO {

    @Schema(title = "基础用户ID")
    private Long baseUserId;

    @Schema(title = "balance type")
    private FrontUserBalanceTypeEnum balanceType;

    @Schema(title = "token left")
    private Integer tokenLeft;

    @Schema(title = "days left")
    private Integer days;
}

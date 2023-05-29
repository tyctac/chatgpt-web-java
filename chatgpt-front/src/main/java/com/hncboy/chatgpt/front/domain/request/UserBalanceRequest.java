package com.hncboy.chatgpt.front.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端用户登录请求
 *
 * @author CoDeleven
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(title = "user balance left")
public class UserBalanceRequest {
    @Schema(title = "uuid")
    private String uuid;

}

package com.hncboy.chatgpt.front.handler.emitter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.hncboy.chatgpt.base.domain.entity.AccountBalanceDO;
import com.hncboy.chatgpt.base.handler.SensitiveWordHandler;
import com.hncboy.chatgpt.base.service.AccountBalanceService;
import com.hncboy.chatgpt.base.util.ObjectMapperUtil;
import com.hncboy.chatgpt.front.domain.request.ChatProcessRequest;
import com.hncboy.chatgpt.front.domain.vo.ChatReplyMessageVO;
import com.hncboy.chatgpt.front.util.FrontUserUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.List;

/**
 * @author hncboy
 * @date 2023/3/29 11:58
 * 敏感词检测
 */
@Slf4j
public class AccountBalanceEmitterChain extends AbstractResponseEmitterChain {

    @Resource
    private AccountBalanceService accountBalanceService;

    @Override
    public void doChain(ChatProcessRequest request, ResponseBodyEmitter emitter) {

        Integer id = FrontUserUtil.getUserId();
        AccountBalanceDO accountBalanceDO = accountBalanceService.getAccountBalanceByid(FrontUserUtil.getUserId());
        //AccountBalanceDO accountBalanceDO = accountBalanceService.getAccountBalanceByid(questionChatMessageDO.getUserId());
        Integer tokenLeft = accountBalanceDO.getTokenLeft();
        log.error("all balance of token left is " + tokenLeft.toString());

        List<String> prompts = SensitiveWordHandler.checkWord(request.getPrompt());
        List<String> systemMessages = SensitiveWordHandler.checkWord(request.getSystemMessage());
        try {
            // 取上一条消息的 parentMessageId 和 conversationId，使得敏感词检测未通过时不影响上下文
            ChatReplyMessageVO chatReplyMessageVO = ChatReplyMessageVO.onEmitterChainException(request);
            if (tokenLeft <0) {
                chatReplyMessageVO.setText(StrUtil.format("余额不足，请充值", prompts));
                emitter.send(ObjectMapperUtil.toJson(chatReplyMessageVO));
                emitter.complete();
                return;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (getNext() != null) {
            getNext().doChain(request, emitter);
        }
    }
}

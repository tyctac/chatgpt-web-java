package com.hncboy.chatgpt.front.api.apikey;

import cn.hutool.extra.spring.SpringUtil;
import com.hncboy.chatgpt.base.config.ChatConfig;
import com.hncboy.chatgpt.base.enums.ApiTypeEnum;
import com.hncboy.chatgpt.base.util.OkHttpClientUtil;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

/**
 * @author hncboy
 * @date 2023/3/24 16:09
 * ApiKey 聊天 Client 构建者
 */
@Slf4j
@UtilityClass
public class ApiKeyChatClientBuilder {

    /**
     * 构建 API 流式请求客户端
     *
     * @return OpenAiStreamClient
     */
    public OpenAiStreamClient buildOpenAiStreamClient() {
        ChatConfig chatConfig = SpringUtil.getBean(ChatConfig.class);

        OkHttpClient okHttpClient = OkHttpClientUtil.getInstance(
                ApiTypeEnum.API_KEY,
                chatConfig.getTimeoutMs(),
                chatConfig.getTimeoutMs(),
                chatConfig.getTimeoutMs(),
                getProxy()
        );

        log.info("api key is here===> " + chatConfig.getOpenaiApiKey().toString());
        log.info("api key is here===> " + chatConfig.getOpenaiApiBaseUrl().toString());
        OpenAiStreamClient oas = OpenAiStreamClient
                .builder()
                .apiHost(chatConfig.getOpenaiApiBaseUrl())
                .keyStrategy(new KeyRandomStrategy())
                .apiKey(Collections.singletonList(chatConfig.getOpenaiApiKey()))
                .okHttpClient(okHttpClient)
                .build();
        log.info("api key is here========> " + oas.getApiKey());

        return oas;
//        return OpenAiStreamClient.builder()
//                .apiKey(Collections.singletonList(chatConfig.getOpenaiApiKey()))
//                .okHttpClient(okHttpClient)
//                .apiHost(chatConfig.getOpenaiApiBaseUrl())
//                .build();
    }

    /**
     * 获取 Proxy
     *
     * @return Proxy
     */
    private Proxy getProxy() {
        ChatConfig chatConfig = SpringUtil.getBean(ChatConfig.class);
        // 国内需要代理
        Proxy proxy = Proxy.NO_PROXY;
        if (chatConfig.hasHttpProxy()) {
            log.info(" proxy is here===> " + chatConfig.getHttpProxyHost().toString() );
            log.info(" proxy port is here===> " + chatConfig.getHttpProxyPort() );

            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(chatConfig.getHttpProxyHost(), chatConfig.getHttpProxyPort()));

        }
        return proxy;
    }

    private Proxy getProxy2() {
        ChatConfig chatConfig = SpringUtil.getBean(ChatConfig.class);
        // 国内需要代理
        Proxy proxy = Proxy.NO_PROXY;
        if (chatConfig.hasHttpProxy()) {
            log.info(" proxy is here===> " + chatConfig.getHttpProxyHost().toString() );
            log.info(" proxy port is here===> " + chatConfig.getHttpProxyPort() );

//            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(chatConfig.getHttpProxyHost(), chatConfig.getHttpProxyPort()));
            InetSocketAddress proxyAddr = new InetSocketAddress(chatConfig.getHttpProxyHost(), chatConfig.getHttpProxyPort());
            proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(chatConfig.getHttpProxyHost(), chatConfig.getHttpProxyPort()));

        }
        return proxy;
    }
}

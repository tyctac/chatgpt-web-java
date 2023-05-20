package com.hncboy.chatgpt.front.api.apikey;

import cn.hutool.extra.spring.SpringUtil;
import com.hncboy.chatgpt.base.config.ChatConfig;
import com.hncboy.chatgpt.base.enums.ApiTypeEnum;
import com.hncboy.chatgpt.base.util.OkHttpClientUtil;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.billing.BillingUsage;
import com.unfbx.chatgpt.entity.billing.Subscription;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hncboy
 * @date 2023/3/24 16:09
 * ApiKey 聊天 Client 构建者
 */
@Slf4j
@UtilityClass
public class ApiKeyChatClientBuilder4 {

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

        log.info("api key is here===> " + chatConfig.getOpenaiApiKey4().toString());
        log.info("api key is here===> " + chatConfig.getOpenaiApiBaseUrl().toString());
        OpenAiStreamClient oas = OpenAiStreamClient
                .builder()
                .apiHost(chatConfig.getOpenaiApiBaseUrl())
                .keyStrategy(new KeyRandomStrategy())
                //.apiKey(Collections.singletonList(chatConfig.getOpenaiApiKey()))
                .apiKey(splitString(chatConfig.getOpenaiApiKey4(),","))
                .okHttpClient(okHttpClient)
                .build();
        log.info("api key is here========> " + oas.getApiKey());
        log.info("api model is here========> " + chatConfig.getOpenaiApiModel().toString());
        subscription(oas);
        return oas;
//        return OpenAiStreamClient.builder()
//                .apiKey(Collections.singletonList(chatConfig.getOpenaiApiKey()))
//                .okHttpClient(okHttpClient)
//                .apiHost(chatConfig.getOpenaiApiBaseUrl())
//                .build();
    }



    private void subscription(OpenAiStreamClient client ){
        Subscription subs = client.subscription();
        log.info("用户名：{}", subs.getAccountName());
        log.info("用户总余额（美元）：{}", subs.getHardLimitUsd());
        log.info("更多信息看------------>Subscription类");

        LocalDate start = LocalDate.of(2023, 3, 7);
        BillingUsage billingUsage = client.billingUsage(start, LocalDate.now());
        log.info("总使用金额（美分）：{}", billingUsage.getTotalUsage());
        log.info("更多信息看----------->BillingUsage类");

//        CreditGrantsResponse creditGrantsResponse = client.creditGrants();
//        log.info("账户总余额（美元）：{}", creditGrantsResponse.getTotalGranted());
//        log.info("账户总使用金额（美元）：{}", creditGrantsResponse.getTotalUsed());
//        log.info("账户总剩余金额（美元）：{}", creditGrantsResponse.getTotalAvailable());

    }

    private List<String> splitString(String str, String delimiter) {
        // Split the string using the specified delimiter and convert it to a list
        return Arrays.stream(str.split(delimiter))
                .collect(Collectors.toList());
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

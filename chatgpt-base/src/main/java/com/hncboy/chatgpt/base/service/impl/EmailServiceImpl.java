package com.hncboy.chatgpt.base.service.impl;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.hncboy.chatgpt.base.config.EmailConfig;
import com.hncboy.chatgpt.base.enums.EmailBizTypeEnum;
import com.hncboy.chatgpt.base.service.EmailService;
import com.hncboy.chatgpt.base.service.SysEmailSendLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 邮箱注册类型策略实现类
 *
 * @author CoDeleven
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    private final EmailConfig emailConfig;
    private final MailAccount mailAccount;
    private final SysEmailSendLogService emailLogService;

    public EmailServiceImpl(EmailConfig emailConfig, SysEmailSendLogService emailLogService) {
        this.emailConfig = emailConfig;
        this.emailLogService = emailLogService;

        mailAccount = new MailAccount();
        mailAccount.setHost(emailConfig.getHost());
        mailAccount.setPort(Integer.parseInt(emailConfig.getPort()));
        mailAccount.setFrom(emailConfig.getFrom());
        mailAccount.setUser(emailConfig.getUser());
        mailAccount.setAuth(emailConfig.getAuth());
        mailAccount.setDebug(true);
        mailAccount.setSslEnable(true);
        mailAccount.setPass(emailConfig.getPass());
        log.info("初始化邮箱账号完毕，配置信息为：{} ", emailConfig);
    }

    @Override
    public void sendForVerifyCode(String targetEmail, String verifyCode) {
        String url = emailConfig.getVerificationRedirectUrl().concat(verifyCode);
        String content = "点击下面的网址完成【smartAI】账号的注册：+\n" +
        "亲爱的用户，感谢您注册我们的网站。为了激活您的账号，请点击下面的链接，或者复制到浏览器中打开：\n" +
                 url +
                "如果您没有注册过我们的网站，请忽略这封邮件。\n" +
                "祝您使用愉快！\n" +
                "smartmind-ai tech support\n";

        // 记录日志
        try {
            String sendMsgId = this.sendMessage(targetEmail, content);
            emailLogService.createSuccessLogBySysLog(sendMsgId, mailAccount.getFrom(), targetEmail, EmailBizTypeEnum.REGISTER_VERIFY, content);
        } catch (Exception e) {
            emailLogService.createFailedLogBySysLog("", mailAccount.getFrom(), targetEmail, EmailBizTypeEnum.REGISTER_VERIFY, content, e.getMessage());
        }
    }

    protected String sendMessage(String targetEmail, String content) {
        return MailUtil.send(mailAccount, targetEmail, "【StarGPT】账号注册", content, false);
    }
}

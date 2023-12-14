package com.nanke.nanketop.contoller;

import com.nanke.nanketop.Util.EmailUtil;
import com.nanke.nanketop.Util.Json;
import com.nanke.nanketop.Util.LimitRequest;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackContoller {
    @Autowired
    private EmailUtil emailUtil;
    Json json=new Json();
    @LimitRequest(time = 180000,count = 1)
    @RequestMapping("/Feedback")
    public Json Feedback(String Content, String ContactInformation) {
        try {
            emailUtil.TemplateFeedbackEmail(Content,ContactInformation);
            json.json(200,"发送成功",null);
            return json;
        } catch (MessagingException e) {
            json.json(501,"发送失败",e);
            return json;
        }
    }
}
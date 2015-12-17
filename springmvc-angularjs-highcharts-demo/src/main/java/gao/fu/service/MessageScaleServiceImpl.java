package gao.fu.service;

import gao.fu.dao.MessageScaleDao;
import gao.fu.dao.WechatBasicDao;
import gao.fu.model.MessageScale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MessageScaleServiceImpl implements MessageScaleService {
    private final Logger logger = LoggerFactory.getLogger(MessageScaleServiceImpl.class);

    @Autowired
    private MessageScaleDao messageScaleDao;

    @Autowired
    private WechatBasicDao wechatBasicDao;

    @Override
    public List<MessageScale> getMessageScaleTrend(String token, String dateType, String start, String end) {
        return messageScaleDao.findMessageScaleBetweenDates(token, dateType, start, end);
    }

    @Override
    public List<MessageScale> getTopMessageScale(String dateType, String date, int top) {
        List<MessageScale> scales = messageScaleDao.findTopMessageScale(dateType, date, top);

//        logger.info("Top Message Scale:{}", scales);

        if (!scales.isEmpty()) {
            List<String> tokens = getTokens(scales);
//            logger.info("Tokens:{}", tokens);

            Map<String, String> map = wechatBasicDao.findTokenNameMap(tokens);

            for (MessageScale scale : scales) {
                scale.setName(map.get(scale.getToken()));
            }
        }
        return scales;
    }

    private List<String> getTokens(List<MessageScale> scales) {
        List<String> tokens = new ArrayList<>();
        for (MessageScale scale : scales) {
            tokens.add(scale.getToken());
        }
        return tokens;
    }
}

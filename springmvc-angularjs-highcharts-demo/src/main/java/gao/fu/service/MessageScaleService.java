package gao.fu.service;

import gao.fu.model.MessageScale;

import java.util.List;

public interface MessageScaleService {
    List<MessageScale> getMessageScaleTrend(String token, String dateType, String start, String end);

    List<MessageScale> getTopMessageScale(String dateType, String date, int top);
}

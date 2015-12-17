package gao.fu.dao;

import gao.fu.model.MessageScale;

import java.util.List;

public interface MessageScaleDao {
    List<MessageScale> findMessageScaleBetweenDates(String token, String dateType, String start, String end);

    List<MessageScale> findTopMessageScale(String dateType, String date, int top);
}

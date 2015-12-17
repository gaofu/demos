package gao.fu.dao;

import gao.fu.model.MessageScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class MessageScaleDaoImpl implements MessageScaleDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MessageScale> findMessageScaleBetweenDates(String token, String dateType, String start, String end) {
        Query query = query(where("token").is(token).and("date").gte(start).lte(end))
                .with(new Sort(Sort.Direction.ASC, "date"));
        String collection = getCollectionName(dateType);
        return mongoTemplate.find(query, MessageScale.class, collection);
    }

    @Override
    public List<MessageScale> findTopMessageScale(String dateType, String date, int top) {
        Query query = query(where("date").is(date))
                .with(new Sort(Sort.Direction.DESC, "count"))
                .limit(top + 1);

        String collection = getCollectionName(dateType);

        List<MessageScale> scales = mongoTemplate.find(query, MessageScale.class, collection);
        if (scales.size() > 0) {
            scales.remove(0);
        }

        return scales;
    }

    private String getCollectionName(String dateType) {
        return "wechat_message_scale_" + dateType;
    }
}

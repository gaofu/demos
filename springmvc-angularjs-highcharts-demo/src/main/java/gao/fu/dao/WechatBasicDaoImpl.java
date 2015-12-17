package gao.fu.dao;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WechatBasicDaoImpl extends SqlSessionDaoSupport implements WechatBasicDao {

    @Override
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public Map<String, String> findTokenNameMap(List<String> tokens) {
        final Map<String, String> result = new HashMap<>();

        getSqlSession().select("findTokenNameMap", new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                Map map = (Map) resultContext.getResultObject();
                result.put(map.get("token").toString(), map.get("name").toString());
            }
        });

        return result;
    }
}

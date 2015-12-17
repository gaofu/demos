package gao.fu.dao;

import java.util.List;
import java.util.Map;

public interface WechatBasicDao {
    Map<String, String> findTokenNameMap(List<String> tokens);
}

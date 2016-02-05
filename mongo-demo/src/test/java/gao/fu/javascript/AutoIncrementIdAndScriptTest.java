package gao.fu.javascript;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.data.mongodb.core.script.NamedMongoScript;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * (1) Test an auto-increment pattern for the _id field.
 *
 * (2) Test MongoDB Script and Spring Script Operations.
 *
 * Created by gaofu on 16-2-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class AutoIncrementIdAndScriptTest {

    @Autowired
    private MongoTemplate template;

    @Before
    public void setUp() throws Exception {
        ScriptOperations scriptOps = template.scriptOps();

        // 将JavaScript函数保存到MongoDB server，以便直接在客户端调用
        scriptOps.register(new NamedMongoScript("getNextSequence", "function(name){var ret=db.counters.findAndModify({query:{_id:name},update:{$inc:{seq:NumberLong(1)}},new:true});return ret.seq.floatApprox}"));
//        scriptOps.register(new NamedMongoScript("getNextSequence", "function(name){var ret=db.counters.findAndModify({query:{_id:name},update:{$inc:{seq:1}},new:true});return ret.seq}")); // 这样就会把seq变成双精度浮点型

        template.upsert(query(where("_id").is("userid")), update("seq", 0L), "counters");
    }


    @Test
    public void testClientScript() {
        final String origin = "Hello World";
        String script = "function(x){return x + \".\"}";

        ExecutableMongoScript mongoScript = new ExecutableMongoScript(script);

        ScriptOperations scriptOps = template.scriptOps();

        Object result1 = scriptOps.execute(mongoScript, origin);
        // Spring使用String.format()方法对字符串进行了处理
//        System.out.println(result1);
        assertEquals(String.format("'%s'", origin) + '.', result1);

        Object mongoEval = template.getDb().eval(script, origin);
//        System.out.println(mongoEval);
        assertEquals(origin + ".", mongoEval);

        Object result2 = scriptOps.execute(mongoScript, 3);
        assertEquals("3.", result2);
    }
    @Test
    public void testAutoIncrementIdAndStoredScript() {
        ScriptOperations scriptOps = template.scriptOps();

        boolean exists = scriptOps.exists("getNextSequence");
        assertTrue(exists);

        // JavaScript返回的总是双精度浮点型数字,所以需要转换
        User jack = new User(((Number) scriptOps.call("getNextSequence", "userid")).longValue(), "Jack");
        User rose = new User(((Number) scriptOps.call("getNextSequence", "userid")).longValue(), "Rose");
        template.insert(jack);
        template.insert(rose);

        assertEquals(1, jack.getId());
        assertEquals(2, rose.getId());

        DB db = template.getDb();
        Object eval = db.eval("getNextSequence('userid')");
        // JavaScript返回的总是双精度浮点型数字
        assertEquals(3.0d, eval);
    }


    /**
     * 注释掉此方法可以查看数据库中的集合数据。
     */
    @After
    public void tearDown() throws Exception {
        template.dropCollection("counters");
        template.dropCollection(User.class);
        template.getCollection("system.js").remove(new BasicDBObject("_id", "getNextSequence"));
    }
}
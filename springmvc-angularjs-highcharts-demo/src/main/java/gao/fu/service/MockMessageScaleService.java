package gao.fu.service;

import gao.fu.model.MessageScale;

import java.util.Arrays;
import java.util.List;

//@Service
public class MockMessageScaleService implements MessageScaleService {

    private List<MessageScale> trend;

    private List<MessageScale> top;

    {
        MessageScale one = new MessageScale();
        one.setToken("wxbfeae3065a5ee608");
        one.setCount(100);
        one.setSize(14700);
        one.setDate("2015-12-07");

        MessageScale two = new MessageScale();
        two.setToken("wxbfeae3065a5ee608");
        two.setCount(80);
        two.setSize(11840);
        two.setDate("2015-12-08");

        MessageScale three = new MessageScale();
        three.setToken("wxbfeae3065a5ee608");
        three.setCount(105);
        three.setSize(15540);
        three.setDate("2015-12-09");

        MessageScale four = new MessageScale();
        four.setToken("wxbfeae3065a5ee608");
        four.setCount(95);
        four.setSize(13775);
        four.setDate("2015-12-10");

        trend = Arrays.asList(one, two, three, four);

        MessageScale tOne = new MessageScale();
        tOne.setName("互赢网络");
        tOne.setToken("wxbfeae3065a5ee608");
        tOne.setCount(105);
        tOne.setSize(15540);
        tOne.setDate("2015-12-07");

        MessageScale tTwo = new MessageScale();
        tTwo.setName("米其林乘用车轮胎");
        tTwo.setToken("PLVV7OXXNM");
        tTwo.setCount(100);
        tTwo.setSize(14700);
        tTwo.setDate("2015-12-07");

        MessageScale tThree = new MessageScale();
        tThree.setName("长安福特微客服");
        tThree.setToken("7UOHYCCD9T");
        tThree.setCount(95);
        tThree.setSize(13775);
        tThree.setDate("2015-12-07");

        MessageScale tFour = new MessageScale();
        tFour.setName("FOTILE方太");
        tFour.setToken("MXQBQBQTKX");
        tFour.setCount(80);
        tFour.setSize(11840);
        tFour.setDate("2015-12-07");

        top = Arrays.asList(tOne, tTwo, tThree, tFour);
    }

    @Override
    public List<MessageScale> getMessageScaleTrend(String token, String dateType, String start, String end) {
        return trend;
    }

    @Override
    public List<MessageScale> getTopMessageScale(String dateType, String date, int top) {
        return this.top;
    }
}

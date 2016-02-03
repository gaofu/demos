package gao.fu;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;
import org.apache.thrift7.TException;

/**
 * Created by gaofu.
 * Created on 16-2-3.
 */
public class StormDRPCClient {
    public static void main(String[] args) {
        for (final String url : ReachTopology.TWEETERS_DB.keySet()) {
            new Thread(new Runnable() {
                public void run() {
                    DRPCClient client = new DRPCClient("localhost", 3772, 5000);
                    try {
                        String result = client.execute("reach", url);
//                        String result = client.execute("notReachFunctionThrowSocketTimeoutException", url);
                        System.out.println("URL:" + url + " reach:" + result);
                    } catch (TException | DRPCExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

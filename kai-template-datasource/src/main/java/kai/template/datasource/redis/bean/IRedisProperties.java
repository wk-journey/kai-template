package kai.template.datasource.redis.bean;

/**
 * Created by wangkai on 2018/9/8.
 */
public interface IRedisProperties {
    int getDatabase();

    String getPassword();

    int getTimeout();

    int getExpireSeconds();

    int getPoolMaxTotal();

    int getPoolMaxIdle();

    int getPoolMaxWaitMillis();

    int getPoolMinIdle();

    /**
     * 单机模式
     * @return
     */
    String getHost();
    /**
     * 单机模式
     * @return
     */
    String getPort();

    /**
     * 哨兵模式
     * @return
     */
    String getSentinelMaster();
    /**
     * 哨兵模式，多个使用逗号分隔
     * @return
     */
    String getSentinelNodes();

//    String getMasterNode();//主从模式
//
//    String getSlaveNodes();//主从模式，多个使用逗号分隔

    /**
     * 集群模式，多个使用逗号分隔
     * @return
     */
    String getClusterNodes();
    /**
     * 每隔多少毫秒扫描集群状态
     * @return
     */
    int getClusterScanInterval();
}

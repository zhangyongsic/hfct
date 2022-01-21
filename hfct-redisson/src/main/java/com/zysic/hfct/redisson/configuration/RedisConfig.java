package com.zysic.hfct.redisson.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisConfigProperties properties;

    @Bean
    public RedissonClient getRedisson(){
        Config config = new Config();
        if (properties.isClustered()){
            for (String node:properties.getCluster().getNodes()){
                config.useClusterServers().addNodeAddress(node)
                        .setPassword(properties.getPassword());
            }
        }else {
            config.useSingleServer()
                    .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
                    .setPassword(properties.getPassword())
                    .setDatabase(properties.getDatabase());
        }
        return Redisson.create(config);
    }
}

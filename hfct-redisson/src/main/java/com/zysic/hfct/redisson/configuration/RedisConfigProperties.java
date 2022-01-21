package com.zysic.hfct.redisson.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {
    private boolean clustered;
    private String host;
    private String port;
    private int database;
    private int maxActive;
    private int maxIdle;
    private int minIdle;
    private String password;
    private cluster cluster;

    public static class cluster {
        private List<String> nodes;

        public List<String> getNodes() {
            return nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }
    }
}

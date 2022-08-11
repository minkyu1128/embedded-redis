package cokr.xit.redis.server.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() {
//        redisServer = new RedisServer(redisPort);
        redisServer = RedisServer.builder()
                .port(redisPort)
//                .setting("maxheap 128M")    //윈도우에서만 동작
                .setting("maxmemory 256M")  //윈도우와 리눅스 둘 다 동작
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

}

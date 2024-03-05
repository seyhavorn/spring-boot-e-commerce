//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//
//@Configuration
//@Conditional(EnableRedisCondition.class)
//public class RedisConfiguration {
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//
//        return jedisConnectionFactory;
//    }
//
//}
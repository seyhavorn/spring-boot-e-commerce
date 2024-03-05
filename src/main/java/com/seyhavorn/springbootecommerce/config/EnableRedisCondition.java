//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//
//public class EnableRedisCondition implements Condition {
//    @Override
//    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        return Boolean.parseBoolean(context.getEnvironment().getProperty("redis.enabled"));
//    }
//}

//public class FallbackRedisCondition implements Condition {
//    @Override
//    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        return !Boolean.parseBoolean(context.getEnvironment().getProperty("redis.enabled"));
//    }
//}

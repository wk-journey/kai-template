package kai.template.datasource.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangkai on 2018/9/9.
 */
public abstract class AbstractRedisService implements RedisService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractRedisService.class);

    protected abstract RedisTemplate getRedisTemplate();

    @Override
    public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return getRedisTemplate().expire(key,timeout,timeUnit);
    }

    @Override
    public Boolean expireAt(String key, Date date) {
        return getRedisTemplate().expireAt(key,date);
    }

    @Override
    public Long getExpire(String key) {
        return getRedisTemplate().getExpire(key);
    }

    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return getRedisTemplate().getExpire(key,timeUnit);
    }

    @Override
    public void delete(String key) {
        getRedisTemplate().delete(key);
    }

    @Override
    public void delete(String... keys) {
        getRedisTemplate().delete(keys);
    }

    @Override
    public void delete(Collection<String> keys) {
        getRedisTemplate().delete(keys);
    }

    @Override
    public Boolean hasKey(String key) {
        return getRedisTemplate().hasKey(key);
    }

    @Override
    public void rename(String oldKey, String newKey) {
        getRedisTemplate().rename(oldKey,newKey);
    }

    @Override
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return getRedisTemplate().renameIfAbsent(oldKey,newKey);
    }

    @Override
    public <T> T get(String key) {
        return (T)getRedisTemplate().opsForValue().get(key);
    }

    @Override
    public <T> T get(String key, long start, long end) {
        return (T)getRedisTemplate().opsForValue().get(key,start,end);
    }

    @Override
    public <T> T getAndSet(String key, T value) {
        return (T)getRedisTemplate().opsForValue().getAndSet(key,value);
    }

    @Override
    public <T> T getBit(String key, long offset) {
        return (T)getRedisTemplate().opsForValue().getBit(key,offset);
    }

    @Override
    public <E> List<E> multiGet(Collection<String> keys) {
        return getRedisTemplate().opsForValue().multiGet(keys);
    }

    @Override
    public Integer append(String key, String value) {
        return getRedisTemplate().opsForValue().append(key,value);
    }

    @Override
    public void set(String key, Object value) {
        getRedisTemplate().opsForValue().set(key,value);
    }

    @Override
    public void set(String key, Object value, long offset) {
        getRedisTemplate().opsForValue().set(key,value,offset);
    }

    @Override
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        getRedisTemplate().opsForValue().set(key,value,timeout,timeUnit);
    }

    @Override
    public Boolean setBit(String key, long offset, boolean value) {
        return getRedisTemplate().opsForValue().setBit(key,offset,value);
    }

    @Override
    public Boolean setIfAbsent(String key, Object value) {
        return getRedisTemplate().opsForValue().setIfAbsent(key,value);
    }

    @Override
    public void multiSet(Map<String,Object> map) {
        getRedisTemplate().opsForValue().multiSet(map);
    }

    @Override
    public Boolean multiSetIfAbsent(Map<String,Object> map) {
        return getRedisTemplate().opsForValue().multiSetIfAbsent(map);
    }

    @Override
    public Long increment(String key) {
        return getRedisTemplate().opsForValue().increment(key,1L);
    }

    @Override
    public Double incrementBy(String key, double increment) {
        return getRedisTemplate().opsForValue().increment(key,increment);
    }

    @Override
    public Long incrementBy(String key, long increment) {
        return getRedisTemplate().opsForValue().increment(key,increment);
    }

    @Override
    public Long decrement(String key) {
        return getRedisTemplate().opsForValue().increment(key,-1L);
    }

    @Override
    public Double decrementBy(String key, Double decrement) {
        return getRedisTemplate().opsForValue().increment(key,-decrement);
    }

    @Override
    public Long decrementBy(String key, long decrement) {
        return getRedisTemplate().opsForValue().increment(key,-decrement);
    }

    @Override
    public void hDel(String key, String... hashKeys) {
        getRedisTemplate().opsForHash().delete(key,hashKeys);
    }

    @Override
    public <V> Map<String, V> hGetAll(String key) {
        return getRedisTemplate().opsForHash().entries(key);
    }

    @Override
    public <T> T hGet(String key, Object hashKey) {
        return (T)getRedisTemplate().opsForHash().get(key,hashKey);
    }

    @Override
    public <E> List<E> hGets(String key, Collection<String> hashKeys) {
        return getRedisTemplate().opsForHash().multiGet(key,hashKeys);
    }

    @Override
    public Long hIncrement(String key, String hashKey) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,1L);
    }

    @Override
    public Double hIncrementBy(String key, String hashKey, double increment) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,increment);
    }

    @Override
    public Long hIncrementBy(String key, String hashKey, long increment) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,increment);
    }

    @Override
    public Long hDecrement(String key, String hashKey) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,-1L);
    }

    @Override
    public Double hDecrementBy(String key, String hashKey, Double decrement) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,-decrement);
    }

    @Override
    public Long hDecrementBy(String key, String hashKey, long decrement) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,-decrement);
    }

    @Override
    public Boolean hHasHashKey(String key, String hashKey) {
        return getRedisTemplate().opsForHash().hasKey(key,hashKey);
    }

    @Override
    public <E> Set<E> hHashKeys(String key) {
        return getRedisTemplate().opsForHash().keys(key);
    }

    @Override
    public <E> List<E> hValues(String key) {
        return getRedisTemplate().opsForHash().values(key);
    }

    @Override
    public void hPut(String key, String hashKey, Object value) {
        getRedisTemplate().opsForHash().put(key,hashKey,value);
    }

    @Override
    public Boolean hPutIfAbsent(String key, String hashKey, Object value) {
        return getRedisTemplate().opsForHash().putIfAbsent(key,hashKey,value);
    }

    @Override
    public void hPutAll(String key, Map<String,Object> map) {
        getRedisTemplate().opsForHash().putAll(key,map);
    }

    @Override
    public <T> T lIndex(String key, Long index) {
        return (T)getRedisTemplate().opsForList().index(key,index);
    }

    @Override
    public Long lLen(String key) {
        return getRedisTemplate().opsForList().size(key);
    }

    @Override
    public void lSet(String key, long index, Object value) {
        getRedisTemplate().opsForList().set(key,index,value);
    }

    @Override
    public void lRemove(String key, long index, Object value) {
        getRedisTemplate().opsForList().remove(key,index,value);
    }

    @Override
    public <E> List<E> lRange(String key, long start, long end) {
        return getRedisTemplate().opsForList().range(key,start,end);
    }

    @Override
    public void lTrim(String key, long start, long end) {
        getRedisTemplate().opsForList().trim(key,start,end);
    }

    @Override
    public <T> T leftPop(String key) {
        return (T)getRedisTemplate().opsForList().leftPop(key);
    }

    @Override
    public <T> T leftPop(String key, Long timeout, TimeUnit timeUnit) {
        return (T)getRedisTemplate().opsForList().leftPop(key,timeout,timeUnit);
    }

    @Override
    public Long lLeftPush(String key, Object value) {
        return getRedisTemplate().opsForList().leftPush(key,value);
    }

    @Override
    public Long lLeftPush(String key, Object pivot, Object value) {
        return getRedisTemplate().opsForList().leftPush(key,pivot,value);
    }

    @Override
    public Long lLeftPushAll(String key, Object... values) {
        return getRedisTemplate().opsForList().leftPushAll(key,values);
    }

    @Override
    public Long lLeftPushAll(String key, Collection values) {
        return getRedisTemplate().opsForList().leftPushAll(key,values);
    }

    @Override
    public Long lLeftPushIfPresent(String key, Object value) {
        return getRedisTemplate().opsForList().leftPushIfPresent(key,value);
    }

    @Override
    public <T> T lRightPop(String key) {
        return (T)getRedisTemplate().opsForList().rightPop(key);
    }

    @Override
    public <T> T lRightPop(String key, Long timeout, TimeUnit timeUnit) {
        return (T)getRedisTemplate().opsForList().rightPop(key,timeout,timeUnit);
    }

    @Override
    public Long lRightPush(String key, Object value) {
        return getRedisTemplate().opsForList().rightPush(key,value);
    }

    @Override
    public Long lRightPush(String key, Object pivot, Object value) {
        return getRedisTemplate().opsForList().rightPush(key,pivot,value);
    }

    @Override
    public Long lRightPushAll(String key, Object... values) {
        return getRedisTemplate().opsForList().rightPushAll(key,values);
    }

    @Override
    public Long lRightPushAll(String key, Collection values) {
        return getRedisTemplate().opsForList().rightPushAll(key,values);
    }

    @Override
    public Long lRightPushIfPresent(String key, Object value) {
        return getRedisTemplate().opsForList().rightPushIfPresent(key,value);
    }

    @Override
    public <T> T lRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return (T)getRedisTemplate().opsForList().rightPopAndLeftPush(sourceKey,destinationKey);
    }

    @Override
    public <T> T lRightPopAndLeftPush(String sourceKey, String destinationKey, Long timeout, TimeUnit timeUnit) {
        return (T)getRedisTemplate().opsForList().rightPopAndLeftPush(sourceKey,destinationKey,timeout,timeUnit);
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return getRedisTemplate().opsForSet().add(key,values);
    }

    @Override
    public <E> Set<E> sDifference(String key, String otherKey) {
        return getRedisTemplate().opsForSet().difference(key,otherKey);
    }

    @Override
    public <E> Set<E> sDifference(String key, Collection<String> otherKeys) {
        return getRedisTemplate().opsForSet().difference(key,otherKeys);
    }

    @Override
    public Long sDifferenceAndStore(String key, String otherKey, String destKey) {
        return getRedisTemplate().opsForSet().differenceAndStore(key,otherKey,destKey);
    }

    @Override
    public Long sDifferenceAndStore(String key, Collection<String> otherKeys, String destKey) {
        return getRedisTemplate().opsForSet().differenceAndStore(key,otherKeys,destKey);
    }

    @Override
    public <E> Set<E> sIntersect(String key, String otherKey) {
        return getRedisTemplate().opsForSet().intersect(key,otherKey);
    }

    @Override
    public <E> Set<E> sIntersect(String key, Collection<String> otherKeys) {
        return getRedisTemplate().opsForSet().intersect(key,otherKeys);
    }

    @Override
    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return getRedisTemplate().opsForSet().intersectAndStore(key,otherKey,destKey);
    }

    @Override
    public Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return getRedisTemplate().opsForSet().intersectAndStore(key,otherKeys,destKey);
    }

    @Override
    public <E> Set<E> sUnion(String key, String otherKey) {
        return getRedisTemplate().opsForSet().union(key,otherKey);
    }

    @Override
    public <E> Set<E> sUnion(String key, Collection<String> otherKeys) {
        return getRedisTemplate().opsForSet().union(key,otherKeys);
    }

    @Override
    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return getRedisTemplate().opsForSet().unionAndStore(key,otherKey,destKey);
    }

    @Override
    public Long sUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return getRedisTemplate().opsForSet().unionAndStore(key,otherKeys,destKey);
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return getRedisTemplate().opsForSet().isMember(key,value);
    }

    @Override
    public <E> Set<E> sMembers(String key) {
        return getRedisTemplate().opsForSet().members(key);
    }

    @Override
    public Boolean sMove(String key, Object value, String destKey) {
        return getRedisTemplate().opsForSet().move(key,value,destKey);
    }

    @Override
    public <T> T sPop(String key) {
        return (T)getRedisTemplate().opsForSet().pop(key);
    }

    @Override
    public <T> T sRandomMember(String key) {
        return (T)getRedisTemplate().opsForSet().randomMember(key);
    }

    @Override
    public <E> List<E> sRandomMembers(String key, long count) {
        return getRedisTemplate().opsForSet().randomMembers(key,count);
    }

    @Override
    public <E> Set<E> sDistinctRandomMembers(String key, long count) {
        return getRedisTemplate().opsForSet().distinctRandomMembers(key,count);
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return getRedisTemplate().opsForSet().remove(key,values);
    }

    @Override
    public Boolean zAdd(String key, Object value, double score) {
        return getRedisTemplate().opsForZSet().add(key,value,score);
    }

    @Override
    public <T> Long zAdd(String key, Map<T, Double> values) {
        Iterator<T> iterator = values.keySet().iterator();
        Set<ZSetOperations.TypedTuple> tts = new HashSet<>();
        while (iterator.hasNext()){
            T v = iterator.next();
            Double score = values.get(v);
            ZSetOperations.TypedTuple tt =  new DefaultTypedTuple(v,score);
            tts.add(tt);
        }
        return getRedisTemplate().opsForZSet().add(key,tts);
    }

    @Override
    public Long zCount(String key, double min, double max) {
        return getRedisTemplate().opsForZSet().count(key,min,max);
    }

    @Override
    public Double zIncrementScore(String key, Object value, double increment) {
        return getRedisTemplate().opsForZSet().incrementScore(key,value,increment);
    }

    @Override
    public Double zDecrementScore(String key, Object value, double decrement) {
        return getRedisTemplate().opsForZSet().incrementScore(key,value,-decrement);
    }

    @Override
    public Long zIntersectAndStore(String key, String otherKey, String destKey) {
        return getRedisTemplate().opsForZSet().intersectAndStore(key,otherKey,destKey);
    }

    @Override
    public Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return getRedisTemplate().opsForZSet().intersectAndStore(key,otherKeys,destKey);
    }

    @Override
    public <E> Set<E> zRange(String key, long start, long end) {
        return getRedisTemplate().opsForZSet().range(key,start,end);
    }

    @Override
    public <E> Set<E> zRangeByScore(String key, double min, double max) {
        return getRedisTemplate().opsForZSet().rangeByScore(key,min,max);
    }

    @Override
    public <E> Set<E> zRangeByScore(String key, double min, double max, long offset, long count) {
        return getRedisTemplate().opsForZSet().rangeByScore(key,min,max,offset,count);
    }

    @Override
    public <E> Set<E> zReverseRange(String key, long start, long end) {
        return getRedisTemplate().opsForZSet().reverseRange(key,start,end);
    }

    @Override
    public <E> Set<E> zReverseRangeByScore(String key, double min, double max) {
        return getRedisTemplate().opsForZSet().reverseRangeByScore(key,min,max);
    }

    @Override
    public <E> Set<E> zReverseRangeByScore(String key, double min, double max, long offset, long count) {
        return getRedisTemplate().opsForZSet().reverseRangeByScore(key,min,max,offset,count);
    }

    @Override
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return getRedisTemplate().opsForZSet().unionAndStore(key,otherKey,destKey);
    }

    @Override
    public Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return getRedisTemplate().opsForZSet().unionAndStore(key,otherKeys,destKey);
    }

    @Override
    public Long zRank(String key, Object value) {
        return getRedisTemplate().opsForZSet().rank(key,value);
    }

    @Override
    public Long zReverseRank(String key, Object value) {
        return getRedisTemplate().opsForZSet().reverseRank(key,value);
    }

    @Override
    public Long zRemove(String key, Object value) {
        return getRedisTemplate().opsForZSet().remove(key,value);
    }

    @Override
    public Long zRemove(String key, Object... values) {
        return getRedisTemplate().opsForZSet().remove(key,values);
    }

    @Override
    public Long zRemove(String key, Collection<Object> values) {
        return getRedisTemplate().opsForZSet().remove(key,values);
    }

    @Override
    public Long zRemoveRange(String key, long start, long end) {
        return getRedisTemplate().opsForZSet().removeRange(key,start,end);
    }

    @Override
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return getRedisTemplate().opsForZSet().removeRangeByScore(key,min,max);
    }

    @Override
    public Double zScore(String key, Object value) {
        return getRedisTemplate().opsForZSet().score(key,value);
    }

    @Override
    public Long zCard(String key) {
        return getRedisTemplate().opsForZSet().zCard(key);
    }
}

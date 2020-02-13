package kai.template.datasource.redis.service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangkai on 2018/9/9.
 */
public interface RedisService {
    Boolean expire(String key, long timeout, TimeUnit timeUnit);
    Boolean expireAt(String key, Date date);
    Long getExpire(String key);
    Long getExpire(String key, TimeUnit timeUnit);
    void delete(String key);
    void delete(String... keys);
    void delete(Collection<String> keys);
    Boolean hasKey(String key);
    void rename(String oldKey, String newKey);
    Boolean renameIfAbsent(String oldKey, String newKey);

    /**  value **/
    <T> T get(String key);
    <T> T get(String key, long start, long end);
    <T> T getAndSet(String key, T value);
    <T> T getBit(String key, long offset);
    <E> List<E> multiGet(Collection<String> keys);
    Integer append(String key, String value);
    void set(String key, Object value);
    void set(String key, Object value, long offset);
    void set(String key, Object value, long timeout, TimeUnit timeUnit);
    Boolean setBit(String key, long offset, boolean value);
    Boolean setIfAbsent(String key, Object value);
    void multiSet(Map<String, Object> map);
    Boolean multiSetIfAbsent(Map<String, Object> map);
    Long increment(String key);
    Double incrementBy(String key, double increment);
    Long incrementBy(String key, long increment);
    Long decrement(String key);
    Double decrementBy(String key, Double decrement);
    Long decrementBy(String key, long decrement);

    /** Hash **/
    void hDel(String key, String... hashKeys);
    <V> Map<String,V> hGetAll(String key);
    <T> T hGet(String key, Object hashKey);
    <E> List<E> hGets(String key, Collection<String> hashKeys);
    Long hIncrement(String key, String hashKey);
    Double hIncrementBy(String key, String hashKey, double increment);
    Long hIncrementBy(String key, String hashKey, long increment);
    Long hDecrement(String key, String hashKey);
    Double hDecrementBy(String key, String hashKey, Double decrement);
    Long hDecrementBy(String key, String hashKey, long decrement);
    Boolean hHasHashKey(String key, String hashKey);
    <E> Set<E> hHashKeys(String key);
    <E> List<E> hValues(String key);
    void hPut(String key, String hashKey, Object value);
    Boolean hPutIfAbsent(String key, String hashKey, Object value);
    void hPutAll(String key, Map<String, Object> map);

    /** List **/
    <T> T lIndex(String key, Long index);
    Long lLen(String key);
    void lSet(String key, long index, Object value);
    void lRemove(String key, long index, Object value);
    <E> List<E> lRange(String key, long start, long end);
    void lTrim(String key, long start, long end);
    <T> T leftPop(String key);
    <T> T leftPop(String key, Long timeout, TimeUnit timeUnit);
    Long lLeftPush(String key, Object value);
    Long lLeftPush(String key, Object pivot, Object value);
    Long lLeftPushAll(String key, Object... values);
    Long lLeftPushAll(String key, Collection values);
    Long lLeftPushIfPresent(String key, Object value);
    <T> T lRightPop(String key);
    <T> T lRightPop(String key, Long timeout, TimeUnit timeUnit);
    Long lRightPush(String key, Object value);
    Long lRightPush(String key, Object pivot, Object value);
    Long lRightPushAll(String key, Object... values);
    Long lRightPushAll(String key, Collection values);
    Long lRightPushIfPresent(String key, Object value);
    <T> T lRightPopAndLeftPush(String sourceKey, String destinationKey);
    <T> T lRightPopAndLeftPush(String sourceKey, String destinationKey, Long timeout, TimeUnit timeUnit);

    /** Set **/
    Long sAdd(String key, Object... values);
    <E> Set<E>  sDifference(String key, String otherKey);
    <E> Set<E>  sDifference(String key, Collection<String> otherKeys);
    Long  sDifferenceAndStore(String key, String otherKey, String destKey);
    Long  sDifferenceAndStore(String key, Collection<String> otherKeys, String destKey);
    <E> Set<E>  sIntersect(String key, String otherKey);
    <E> Set<E>  sIntersect(String key, Collection<String> otherKeys);
    Long  sIntersectAndStore(String key, String otherKey, String destKey);
    Long  sIntersectAndStore(String key, Collection<String> otherKeys, String destKey);
    <E> Set<E>  sUnion(String key, String otherKey);
    <E> Set<E>  sUnion(String key, Collection<String> otherKeys);
    Long  sUnionAndStore(String key, String otherKey, String destKey);
    Long  sUnionAndStore(String key, Collection<String> otherKeys, String destKey);
    Boolean sIsMember(String key, Object value);
    <E> Set<E> sMembers(String key);
    Boolean sMove(String key, Object value, String destKey);
    <T> T sPop(String key);
    <T> T sRandomMember(String key);
    <E> List<E> sRandomMembers(String key, long count);
    <E> Set<E>  sDistinctRandomMembers(String key, long count);
    Long sRemove(String key, Object... values);

    /** SortedSet **/
    Boolean zAdd(String key, Object value, double score);
    <T> Long zAdd(String key, Map<T, Double> values);
    Long zCount(String key, double min, double max);
    Double zIncrementScore(String key, Object value, double increment);
    Double zDecrementScore(String key, Object value, double decrement);
    Long  zIntersectAndStore(String key, String otherKey, String destKey);
    Long  zIntersectAndStore(String key, Collection<String> otherKeys, String destKey);
    <E> Set<E> zRange(String key, long start, long end);
    <E> Set<E> zRangeByScore(String key, double min, double max);
    <E> Set<E> zRangeByScore(String key, double min, double max, long offset, long count);
    <E> Set<E> zReverseRange(String key, long start, long end);
    <E> Set<E> zReverseRangeByScore(String key, double min, double max);
    <E> Set<E> zReverseRangeByScore(String key, double min, double max, long offset, long count);
    Long  zUnionAndStore(String key, String otherKey, String destKey);
    Long  zUnionAndStore(String key, Collection<String> otherKeys, String destKey);
    Long zRank(String key, Object value);
    Long zReverseRank(String key, Object value);
    Long zRemove(String key, Object value);
    Long zRemove(String key, Object... values);
    Long zRemove(String key, Collection<Object> values);
    Long zRemoveRange(String key, long start, long end);
    Long zRemoveRangeByScore(String key, double min, double max);
    Double zScore(String key, Object value);
    Long zCard(String key);
}

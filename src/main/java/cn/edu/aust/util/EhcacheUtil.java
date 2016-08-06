package cn.edu.aust.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具,非线程安全,频繁改动的数据还是不要放入缓存
 */
public class EhcacheUtil {
    private static final CacheManager cachemanager = CacheManager.create();
    private static Cache cache;
    static{
        cache = cachemanager.getCache("Myehcache");
    }

    /**
     * 从缓存取出
     * @param key
     * @return
     * @throws Exception
     */
    public static Object get(String key) throws Exception{
        Element e = cache.get(key);
        if (e!=null){
            return e.getObjectValue();
        }
        return null;
    }

    /**
     * 存入缓存
     * @param key
     * @param value
     * @throws Exception
     */
    public static void put(String key,Object value) throws Exception{
        Element e = new Element(key,value);
        cache.put(e);
    }

    /**
     * 关闭该缓存
     */
    public static void close(){
        cachemanager.shutdown();
    }
}

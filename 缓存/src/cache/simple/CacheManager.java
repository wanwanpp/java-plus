package cache.simple;

import java.util.*;

//Description: 管理缓存

//可扩展的功能：当chche到内存溢出时必须清除掉最早期的一些缓存对象，这就要求对每个缓存对象保存创建时间

public class CacheManager {
    private static HashMap cacheMap = new HashMap();

    //单实例构造方法 
    private CacheManager() {
        super();
    }

    //获取布尔值的缓存 
    public static boolean getSimpleFlag(String key) {
        try {
            return (Boolean) cacheMap.get(key);
        } catch (NullPointerException e) {
            System.out.println("the key is not in the map");
            return false;
        }
    }

    public static long getServerStartdt(String key) {
        try {
            return (Long) cacheMap.get(key);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static boolean isCached(String key) {
        return cacheMap.get(key) != null;
    }

    //设置布尔值的缓存 
    public synchronized static boolean setSimpleFlag(String key, boolean flag) {
        if (isCached(key) && flag == getSimpleFlag(key)) {//假如为真不允许被覆盖
            return false;
        } else {
            cacheMap.put(key, flag);
            return true;
        }
    }

    //得到缓存。同步静态方法 
    private synchronized static Cache getCache(String key) {
        return (Cache) cacheMap.get(key);
    }

    //判断是否存在一个缓存 
    private synchronized static boolean hasCache(String key) {
        return cacheMap.containsKey(key);
    }

    //清除所有缓存 
    public synchronized static void clearAll() {
        cacheMap.clear();
    }

    //符合以type变量内容开头的key会被删除掉
    public synchronized static void clearAll(String type) {
        Iterator i = cacheMap.entrySet().iterator();
        String key;
        List arr = new ArrayList();
        try {
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.startsWith(type)) { //如果匹配则删除掉 
                    arr.add(key);
                }
            }
            for (int k = 0; k < arr.size(); k++) {
                clearKey((String) arr.get(k));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //清除指定的缓存 
    public synchronized static void clearKey(String key) {
        cacheMap.remove(key);
    }

    //载入缓存 
    public synchronized static void putCache(String key, Cache obj) {
        cacheMap.put(key, obj);
    }

    //获取缓存信息 
    public static Cache getCacheInfo(String key) {

        if (hasCache(key)) {
            Cache cache = getCache(key);
            if (cacheExpired(cache)) { //调用判断是否终止方法 
                cache.setExpired(true);
            }
            return cache;
        } else
            return null;
    }

    //载入缓存信息 
    public static void putCacheInfo(String key, Cache obj, long dt, boolean expired) {
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setTimeOut(dt + System.currentTimeMillis()); //设置多久后更新缓存 
        cache.setValue(obj);
        cache.setExpired(expired); //缓存默认载入时，终止状态为FALSE 
        cacheMap.put(key, cache);
    }

    //重写载入缓存信息方法 
    public static void putCacheInfo(String key, Cache obj, long dt) {
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setTimeOut(dt + System.currentTimeMillis());
        cache.setValue(obj);
        cache.setExpired(false);
        cacheMap.put(key, cache);
    }

    //判断缓存是否终止 
    public static boolean cacheExpired(Cache cache) {
        if (null == cache) { //传入的缓存不存在
            throw new NullPointerException("the cache is null");
//            return false;
        }
        long now = System.currentTimeMillis(); //系统当前的毫秒数
        long cacheTime = cache.getTimeOut(); //缓存内的过期毫秒数

        if (cacheTime <= 0 || cacheTime > now) { //过期时间小于等于零时,或者过期时间大于当前时间时，则为FALSE
            return false;
        } else { //大于过期时间 即过期 
            return true;
        }
    }

    //获取缓存中的大小 
    public static int getCacheSize() {
        return cacheMap.size();
    }

    //获取指定的类型的大小 
    public static int getCacheSize(String type) {
        int k = 0;
        Iterator i = cacheMap.entrySet().iterator();
        String key;
        try {
            while (i.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.indexOf(type) != -1) { //如果匹配则删除掉 
                    k++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return k;
    }

    //获取缓存对象中的所有键值名称 
    public static List<Object> getCacheAllkey() {
        List<Object> a = new ArrayList();
        try {
            Iterator i = cacheMap.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                a.add(entry.getKey());
            }
        } catch (Exception ex) {
        } finally {
            return a;
        }
    }

    //获取缓存对象中指定类型 的键值名称 
    public static ArrayList getCacheListkey(String type) {
        ArrayList a = new ArrayList();
        String key;
        try {
            Iterator i = cacheMap.entrySet().iterator();
            while (i.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.indexOf(type) != -1) {
                    a.add(key);
                }
            }
        } catch (Exception ex) {
        } finally {
            return a;
        }
    }

} 
package com.nfcx.luxinvpower.global.cache;

import com.nfcx.luxinvpower.tool.Tool;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RemoteSetCacheManager {
    private static final RemoteSetCacheManager instance = new RemoteSetCacheManager();
    private Map<String, RemoteSetCache> cacheMap = new ConcurrentHashMap();

    public static RemoteSetCacheManager getInstance() {
        return instance;
    }

    public void clearCache(String str) {
        if (Tool.isEmpty(str)) {
            return;
        }
        this.cacheMap.remove(str);
    }

    public JSONObject getCache(String str) {
        RemoteSetCache remoteSetCache;
        if (Tool.isEmpty(str) || (remoteSetCache = this.cacheMap.get(str)) == null) {
            return null;
        }
        if (System.currentTimeMillis() - remoteSetCache.getTime() < 600000) {
            return remoteSetCache.getResult();
        }
        this.cacheMap.remove(str);
        return null;
    }

    public void setCache(String str, JSONObject jSONObject) {
        RemoteSetCache remoteSetCache = this.cacheMap.get(str);
        if (remoteSetCache == null) {
            remoteSetCache = new RemoteSetCache();
        }
        if (remoteSetCache.getResult() == null) {
            remoteSetCache.setResult(jSONObject);
        } else {
            JSONObject result = remoteSetCache.getResult();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    result.put(next, jSONObject.get(next));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        remoteSetCache.setTime(System.currentTimeMillis());
        this.cacheMap.put(str, remoteSetCache);
    }
}

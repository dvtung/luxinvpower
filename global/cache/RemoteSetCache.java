package com.nfcx.luxinvpower.global.cache;

import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RemoteSetCache {
    private JSONObject result;
    private long time;

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public JSONObject getResult() {
        return this.result;
    }

    public void setResult(JSONObject jSONObject) {
        this.result = jSONObject;
    }
}

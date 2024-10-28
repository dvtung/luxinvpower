package com.nfcx.luxinvpower.global.bean.user;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class UserVisitRecord implements Serializable {
    private Long plantId;
    private String serialNum;

    public Long getPlantId() {
        return this.plantId;
    }

    public void setPlantId(Long l) {
        this.plantId = l;
    }

    public String getSerialNum() {
        return this.serialNum;
    }

    public void setSerialNum(String str) {
        this.serialNum = str;
    }
}

package com.nfcx.luxinvpower.view.updateFirmware.bean;

import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.tool.Tool;

/* loaded from: classes2.dex */
public class StandardUploadCheck {
    private boolean _12KNoPowerOffUpdateAllowed;
    private String standard;
    private Integer v1;
    private Integer v2;
    private Integer v3;

    public void fillByInverter(Inverter inverter) {
        String fwCode = inverter.getFwCode();
        boolean z = false;
        setStandard(fwCode.substring(0, 4));
        if (inverter.isLsp()) {
            setV1(Integer.valueOf(Integer.parseInt(fwCode.substring(9, 11), 16)));
            setV2(Integer.valueOf(Integer.parseInt(fwCode.substring(7, 9), 16)));
            setV3(Integer.valueOf(Integer.parseInt(fwCode.substring(5, 7), 16)));
            return;
        }
        if (inverter.isSnaSeries()) {
            setV1(Integer.valueOf(Integer.parseInt(fwCode.substring(7, 9), 16)));
            setV2(Integer.valueOf(Integer.parseInt(fwCode.substring(9, 11), 16)));
            setV3(Integer.valueOf(Integer.parseInt(fwCode.substring(5, 7), 16)));
            return;
        }
        if (inverter.isType6()) {
            int parseInt = Integer.parseInt(fwCode.substring(5, 7), 16);
            int parseInt2 = Integer.parseInt(fwCode.substring(7, 9), 16);
            if (!Tool.isEmpty(this.standard) && this.standard.endsWith("B") && parseInt >= 24 && parseInt2 >= 24) {
                z = true;
            }
            this._12KNoPowerOffUpdateAllowed = z;
            setV1(Integer.valueOf(z ? parseInt : parseInt2));
            if (this._12KNoPowerOffUpdateAllowed) {
                parseInt = parseInt2;
            }
            setV2(Integer.valueOf(parseInt));
            return;
        }
        setV1(Integer.valueOf(Integer.parseInt(fwCode.substring(5, 7), 16)));
        setV2(Integer.valueOf(Integer.parseInt(fwCode.substring(7, 9), 16)));
    }

    public boolean check12KNoPowerOffUpdateAllowed() {
        return this._12KNoPowerOffUpdateAllowed;
    }

    public String getStandard() {
        return this.standard;
    }

    public void setStandard(String str) {
        this.standard = str;
    }

    public Integer getV1() {
        return this.v1;
    }

    public void setV1(Integer num) {
        this.v1 = num;
    }

    public Integer getV2() {
        return this.v2;
    }

    public void setV2(Integer num) {
        this.v2 = num;
    }

    public Integer getV3() {
        return this.v3;
    }

    public void setV3(Integer num) {
        this.v3 = num;
    }
}

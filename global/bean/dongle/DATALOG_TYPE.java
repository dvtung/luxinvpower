package com.nfcx.luxinvpower.global.bean.dongle;

/* loaded from: classes2.dex */
public enum DATALOG_TYPE {
    WIFI { // from class: com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE.1
        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public String getResourceId() {
            return "phase.datalog.type.wifi";
        }

        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public int getTypeCode() {
            return 0;
        }
    },
    WLAN { // from class: com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE.2
        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public String getResourceId() {
            return "phase.datalog.type.wlan";
        }

        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public int getTypeCode() {
            return 1;
        }
    },
    _4G { // from class: com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE.3
        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public String getResourceId() {
            return "phase.datalog.type.4g";
        }

        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public int getTypeCode() {
            return 2;
        }
    },
    GPRS { // from class: com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE.4
        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public String getResourceId() {
            return "phase.datalog.type.gprs";
        }

        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public int getTypeCode() {
            return 3;
        }
    },
    ESP_WIFI { // from class: com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE.5
        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public String getResourceId() {
            return "phase.datalog.type.esp.wifi";
        }

        @Override // com.nfcx.luxinvpower.global.bean.dongle.DATALOG_TYPE
        public int getTypeCode() {
            return 5;
        }
    };

    public abstract String getResourceId();

    public abstract int getTypeCode();

    public static DATALOG_TYPE getEnumByTypeCode(int i) {
        for (DATALOG_TYPE datalog_type : values()) {
            if (datalog_type.getTypeCode() == i) {
                return datalog_type;
            }
        }
        return WIFI;
    }
}

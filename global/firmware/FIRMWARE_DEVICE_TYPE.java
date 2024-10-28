package com.nfcx.luxinvpower.global.firmware;

/* loaded from: classes2.dex */
public enum FIRMWARE_DEVICE_TYPE {
    SNA_3000_6000 { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.1
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "SNA 3000-6000";
        }
    },
    SNA_US_6000 { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.2
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "SNA-US 6000";
        }
    },
    SNA_12K { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.3
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "SNA 12K";
        }
    },
    SNA_US_12K { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.4
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "SNA-US 12K";
        }
    },
    LXP_3_6K_HYBRID_STANDARD { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.5
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "LXP-3-6K Hybrid (Standard)";
        }
    },
    LXP_3_6K_HYBRID_PARALLEL { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.6
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "LXP-3-6K Hybrid (Parallel)";
        }
    },
    LXP_3600_ACS_STANDARD { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.7
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "LXP_3600 ACS (Standard)";
        }
    },
    LXP_3600_ACS_PARALLEL { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.8
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "LXP_3600 ACS (Parallel)";
        }
    },
    LXP_LB_8_12K { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.9
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "LXP-LB-8-12K";
        }
    },
    LSP_100K { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.10
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "LSP-100K";
        }
    },
    LXP_HV_6K_HYBRID { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.11
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "LXP-HV-6K Hybrid";
        }
    },
    Lite_Stor { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.12
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "LiteStor";
        }
    },
    TRIP_6_20K { // from class: com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE.13
        @Override // com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE
        public String getText() {
            return "TRIP 6-20K";
        }
    };

    public abstract String getText();

    public static FIRMWARE_DEVICE_TYPE getEnumByName(String str) {
        for (FIRMWARE_DEVICE_TYPE firmware_device_type : values()) {
            if (firmware_device_type.name().equals(str)) {
                return firmware_device_type;
            }
        }
        return null;
    }

    public static String getEnumByText(String str) {
        for (FIRMWARE_DEVICE_TYPE firmware_device_type : values()) {
            if (firmware_device_type.getText().equals(str)) {
                return firmware_device_type.toString();
            }
        }
        return null;
    }
}

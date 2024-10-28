package com.nfcx.luxinvpower.protocol.lux;

import com.alibaba.fastjson2.internal.asm.Opcodes;

/* loaded from: classes2.dex */
public enum DEVICE_FUNCTION {
    R_HOLD { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.1
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 3;
        }
    },
    R_HOLD_ERROR { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.2
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 131;
        }
    },
    R_INPUT { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.3
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 4;
        }
    },
    R_INPUT_ERROR { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.4
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return Opcodes.IINC;
        }
    },
    W_SINGLE { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.5
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 6;
        }
    },
    W_SINGLE_ERROR { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.6
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 134;
        }
    },
    W_MULTI { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.7
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 16;
        }
    },
    W_MULTI_ERROR { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.8
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 144;
        }
    },
    UPDATE_PREPARE { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.9
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 33;
        }
    },
    UPDATE_SEND_DATA { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.10
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 34;
        }
    },
    UPDATE_RESET { // from class: com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION.11
        @Override // com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION
        public int getFunctionCode() {
            return 35;
        }
    };

    public abstract int getFunctionCode();

    public static DEVICE_FUNCTION getDeviceFunction(int i) {
        for (DEVICE_FUNCTION device_function : values()) {
            if (device_function.getFunctionCode() == i) {
                return device_function;
            }
        }
        return null;
    }
}

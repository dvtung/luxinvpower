package com.nfcx.luxinvpower.global.bean.param;

import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;

/* loaded from: classes2.dex */
public enum BattChgPARAM {
    HOLD_AC_CHARGE_START_BATTERY_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.1
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        public int getStartRegister() {
            return Opcodes.IFLE;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            if (inverter.isType6()) {
                return R.string.phrase_param_start_ac_charge_volt_12k;
            }
            if (inverter.isSnaSeries() || inverter.isHybird() || inverter.isAcCharger()) {
                return R.string.phrase_param_ac_charge_start_battery_voltage;
            }
            return -1;
        }
    },
    OFF_GRID_HOLD_MAX_GEN_CHG_BAT_CURR { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.2
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_bat_charge_current_limit;
        }

        public int getStartRegister() {
            return Opcodes.IFNULL;
        }
    },
    HOLD_AC_CHARGE_END_BATTERY_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.3
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        public int getStartRegister() {
            return Opcodes.IF_ICMPEQ;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            if (inverter.isType6()) {
                return R.string.phrase_param_stop_ac_chg_volt;
            }
            if (inverter.isSnaSeries()) {
                return R.string.phrase_param_ac_charge_end_battery_voltage;
            }
            return -1;
        }
    },
    _12K_HOLD_CHARGE_FIRST_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.4
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_charge_first_volt;
        }

        public int getStartRegister() {
            return 201;
        }
    },
    OFF_GRID_HOLD_GEN_CHG_START_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.5
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_charge_start_volt;
        }

        public int getStartRegister() {
            return Opcodes.MONITORENTER;
        }
    },
    OFF_GRID_HOLD_GEN_CHG_END_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.6
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_charge_end_volt;
        }

        public int getStartRegister() {
            return Opcodes.MONITOREXIT;
        }
    },
    HOLD_AC_CHARGE_START_BATTERY_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.7
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        public int getStartRegister() {
            return Opcodes.IF_ICMPNE;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            if (inverter.isType6()) {
                return R.string.phrase_param_start_ac_charge_soc_12k;
            }
            if (inverter.isSnaSeries()) {
                return R.string.phrase_param_ac_charge_start_battery_soc;
            }
            return -1;
        }
    },
    HOLD_AC_CHARGE_SOC_LIMIT { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.8
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_soc_limit;
        }

        public int getStartRegister() {
            return 67;
        }
    },
    HOLD_FORCED_CHG_SOC_LIMIT { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.9
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_chg_soc_limit;
        }

        public int getStartRegister() {
            return 75;
        }
    },
    OFF_GRID_HOLD_GEN_CHG_START_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.10
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_charge_start_soc;
        }

        public int getStartRegister() {
            return 196;
        }
    },
    OFF_GRID_HOLD_GEN_CHG_END_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.BattChgPARAM.11
        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.BattChgPARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_charge_end_soc;
        }

        public int getStartRegister() {
            return 197;
        }
    };

    public boolean checkEnabled(boolean z) {
        return z;
    }

    public abstract int getResourceId(Inverter inverter);

    public boolean checkInvisible(UserData userData) {
        return userData.getRole() == null;
    }
}

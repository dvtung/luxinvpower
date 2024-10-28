package com.nfcx.luxinvpower.global.bean.param;

import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.nfcx.luxinvpower.BuildConfig;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.BATTERY_TYPE;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.user.ROLE;

/* loaded from: classes2.dex */
public enum PARAM {
    HOLD_PV_INPUT_MODE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.1
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_pv_input_mode;
        }

        public int getStartRegister() {
            return 20;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_START_PV_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.2
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_start_pv_volt;
        }

        public int getStartRegister() {
            return 22;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
            }
            return false;
        }
    },
    HOLD_CONNECT_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.3
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_connect_time;
        }

        public int getStartRegister() {
            return 23;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_RECONNECT_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.4
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_reconnect_time;
        }

        public int getStartRegister() {
            return 24;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_CONN_LOW { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.5
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_conn_low;
        }

        public int getStartRegister() {
            return 25;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_CONN_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.6
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_conn_high;
        }

        public int getStartRegister() {
            return 26;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_GRID_FREQ_CONN_LOW { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.7
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_freq_conn_low;
        }

        public int getStartRegister() {
            return 27;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_GRID_FREQ_CONN_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.8
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_freq_conn_high;
        }

        public int getStartRegister() {
            return 28;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_LIMIT1_LOW { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.9
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_limit1_low;
        }

        public int getStartRegister() {
            return 29;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_VOLT_LIMIT1_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.10
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_limit1_high;
        }

        public int getStartRegister() {
            return 30;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_VOLT_LIMIT1_LOW_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.11
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_limit1_low_time;
        }

        public int getStartRegister() {
            return 31;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_LIMIT1_HIGH_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.12
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_limit1_high_time;
        }

        public int getStartRegister() {
            return 32;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_LIMIT2_LOW { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.13
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_limit2_low;
        }

        public int getStartRegister() {
            return 33;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_VOLT_LIMIT2_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.14
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_limit2_high;
        }

        public int getStartRegister() {
            return 34;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_VOLT_LIMIT2_LOW_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.15
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 35;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_LIMIT2_HIGH_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.16
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 36;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_LIMIT3_LOW { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.17
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_limit3_low;
        }

        public int getStartRegister() {
            return 37;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_VOLT_LIMIT3_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.18
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_limit3_high;
        }

        public int getStartRegister() {
            return 38;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_VOLT_LIMIT3_LOW_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.19
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 39;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_LIMIT3_HIGH_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.20
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 40;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_VOLT_MOV_AVG_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.21
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_volt_mov_avg_high;
        }

        public int getStartRegister() {
            return 41;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_FREQ_LIMIT1_LOW { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.22
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_freq_limit1_low;
        }

        public int getStartRegister() {
            return 42;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_FREQ_LIMIT1_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.23
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_freq_limit1_high;
        }

        public int getStartRegister() {
            return 43;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_FREQ_LIMIT1_LOW_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.24
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 44;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_FREQ_LIMIT1_HIGH_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.25
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 45;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_FREQ_LIMIT2_LOW { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.26
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_freq_limit2_low;
        }

        public int getStartRegister() {
            return 46;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_FREQ_LIMIT2_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.27
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_freq_limit2_high;
        }

        public int getStartRegister() {
            return 47;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_FREQ_LIMIT2_LOW_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.28
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 48;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_FREQ_LIMIT2_HIGH_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.29
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 49;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_FREQ_LIMIT3_LOW { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.30
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_freq_limit3_low;
        }

        public int getStartRegister() {
            return 50;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_FREQ_LIMIT3_HIGH { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.31
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_freq_limit3_high;
        }

        public int getStartRegister() {
            return 51;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_GRID_FREQ_LIMIT3_LOW_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.32
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 52;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_GRID_FREQ_LIMIT3_HIGH_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.33
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 53;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    INPUT_BATTERY_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.34
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole()) || ROLE.MAINTAIN.equals(userData.getRole());
        }
    },
    HOLD_MAX_Q_PERCENT_FOR_QV { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.35
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 54;
        }
    },
    HOLD_V1L { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.36
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 55;
        }
    },
    HOLD_V2L { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.37
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 56;
        }
    },
    HOLD_V1H { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.38
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 57;
        }
    },
    HOLD_V2H { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.39
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 58;
        }
    },
    HOLD_REACTIVE_POWER_CMD_TYPE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.40
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.reactive_power_type;
        }

        public int getStartRegister() {
            return 59;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_ACTIVE_POWER_PERCENT_CMD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.41
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 60;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_REACTIVE_POWER_PERCENT_CMD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.42
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.reactive_power_percent;
        }

        public int getStartRegister() {
            return 61;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_PF_CMD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.43
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 62;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_POWER_SOFT_START_SLOPE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.44
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 63;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    HOLD_CHARGE_POWER_PERCENT_CMD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.45
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_charge_power_percent_cmd;
        }

        public int getStartRegister() {
            return 64;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            return !currentInverter.isSnaSeries();
        }
    },
    HOLD_DISCHG_POWER_PERCENT_CMD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.46
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_discharge_power_percent_cmd;
        }

        public int getStartRegister() {
            return 65;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_AC_CHARGE_POWER_CMD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.47
        public int getStartRegister() {
            return 66;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return inverter.isType6() ? R.string.phrase_param_ac_charge_power_cmd_12k : R.string.phrase_param_ac_charge_power_cmd;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return currentInverter.isType6Series() ? (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true : !currentInverter.isSnaSeries();
        }
    },
    HOLD_AC_CHARGE_START_HOUR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.48
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 68;
        }
    },
    HOLD_AC_CHARGE_START_MINUTE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.49
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_hint_minute_range;
        }

        public int getStartRegister() {
            return 68;
        }
    },
    HOLD_AC_CHARGE_END_HOUR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.50
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_end_time;
        }

        public int getStartRegister() {
            return 69;
        }
    },
    HOLD_AC_CHARGE_END_MINUTE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.51
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_end_time;
        }

        public int getStartRegister() {
            return 69;
        }
    },
    HOLD_AC_CHARGE_START_HOUR_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.52
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_start_time_1;
        }

        public int getStartRegister() {
            return 70;
        }
    },
    HOLD_AC_CHARGE_START_MINUTE_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.53
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_start_time_1;
        }

        public int getStartRegister() {
            return 70;
        }
    },
    HOLD_AC_CHARGE_END_HOUR_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.54
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_end_time_1;
        }

        public int getStartRegister() {
            return 71;
        }
    },
    HOLD_AC_CHARGE_END_MINUTE_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.55
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_end_time_1;
        }

        public int getStartRegister() {
            return 71;
        }
    },
    HOLD_AC_CHARGE_START_HOUR_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.56
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_start_time_2;
        }

        public int getStartRegister() {
            return 72;
        }
    },
    HOLD_AC_CHARGE_START_MINUTE_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.57
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_start_time_2;
        }

        public int getStartRegister() {
            return 72;
        }
    },
    HOLD_AC_CHARGE_END_HOUR_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.58
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_end_time_2;
        }

        public int getStartRegister() {
            return 73;
        }
    },
    HOLD_AC_CHARGE_END_MINUTE_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.59
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_end_time_2;
        }

        public int getStartRegister() {
            return 73;
        }
    },
    HOLD_FORCED_CHG_SOC_LIMIT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.60
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_chg_soc_limit;
        }

        public int getStartRegister() {
            return 75;
        }
    },
    _12K_HOLD_CHARGE_FIRST_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.61
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_charge_first_volt;
        }

        public int getStartRegister() {
            return 201;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHG_POWER_CMD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.62
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_chg_power_cmd;
        }

        public int getStartRegister() {
            return 74;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return currentInverter.isType6Series() ? (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true : !currentInverter.isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_START_HOUR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.63
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_start_time;
        }

        public int getStartRegister() {
            return 76;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_START_MINUTE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.64
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_start_time;
        }

        public int getStartRegister() {
            return 76;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_END_HOUR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.65
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_end_time;
        }

        public int getStartRegister() {
            return 77;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_END_MINUTE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.66
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_end_time;
        }

        public int getStartRegister() {
            return 77;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_START_HOUR_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.67
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_start_time_1;
        }

        public int getStartRegister() {
            return 78;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_START_MINUTE_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.68
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_start_time_1;
        }

        public int getStartRegister() {
            return 78;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_END_HOUR_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.69
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_end_time_1;
        }

        public int getStartRegister() {
            return 79;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_END_MINUTE_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.70
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_end_time_1;
        }

        public int getStartRegister() {
            return 79;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_START_HOUR_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.71
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_start_time_2;
        }

        public int getStartRegister() {
            return 80;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_START_MINUTE_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.72
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_start_time_2;
        }

        public int getStartRegister() {
            return 80;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_END_HOUR_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.73
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_end_time_2;
        }

        public int getStartRegister() {
            return 81;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_CHARGE_END_MINUTE_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.74
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_charge_end_time_2;
        }

        public int getStartRegister() {
            return 81;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_FORCED_DISCHG_POWER_CMD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.75
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_dischg_power_cmd;
        }

        public int getStartRegister() {
            return 82;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true;
        }
    },
    HOLD_FORCED_DISCHG_SOC_LIMIT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.76
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_dischg_soc_limit;
        }

        public int getStartRegister() {
            return 83;
        }
    },
    HOLD_FORCED_DISCHARGE_START_HOUR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.77
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_start_time;
        }

        public int getStartRegister() {
            return 84;
        }
    },
    HOLD_FORCED_DISCHARGE_START_MINUTE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.78
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_start_time;
        }

        public int getStartRegister() {
            return 84;
        }
    },
    HOLD_FORCED_DISCHARGE_END_HOUR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.79
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_end_time;
        }

        public int getStartRegister() {
            return 85;
        }
    },
    HOLD_FORCED_DISCHARGE_END_MINUTE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.80
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_end_time;
        }

        public int getStartRegister() {
            return 85;
        }
    },
    HOLD_FORCED_DISCHARGE_START_HOUR_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.81
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_start_time_1;
        }

        public int getStartRegister() {
            return 86;
        }
    },
    HOLD_FORCED_DISCHARGE_START_MINUTE_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.82
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_start_time_1;
        }

        public int getStartRegister() {
            return 86;
        }
    },
    HOLD_FORCED_DISCHARGE_END_HOUR_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.83
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_end_time_1;
        }

        public int getStartRegister() {
            return 87;
        }
    },
    HOLD_FORCED_DISCHARGE_END_MINUTE_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.84
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_end_time_1;
        }

        public int getStartRegister() {
            return 87;
        }
    },
    HOLD_FORCED_DISCHARGE_START_HOUR_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.85
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_start_time_2;
        }

        public int getStartRegister() {
            return 88;
        }
    },
    HOLD_FORCED_DISCHARGE_START_MINUTE_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.86
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_start_time_2;
        }

        public int getStartRegister() {
            return 88;
        }
    },
    HOLD_FORCED_DISCHARGE_END_HOUR_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.87
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_end_time_2;
        }

        public int getStartRegister() {
            return 89;
        }
    },
    HOLD_FORCED_DISCHARGE_END_MINUTE_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.88
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_forced_discharge_end_time_2;
        }

        public int getStartRegister() {
            return 89;
        }
    },
    HOLD_EPS_VOLT_SET { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.89
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_eps_voltage_set;
        }

        public int getStartRegister() {
            return 90;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            if (userData.getCurrentInverter().isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck();
        }
    },
    HOLD_EPS_FREQ_SET { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.90
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_eps_frequency_set;
        }

        public int getStartRegister() {
            return 91;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            if (userData.getCurrentInverter().isAllInOne()) {
                return !ROLE.VIEWER.equals(role);
            }
            return ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck();
        }
    },
    HOLD_DELAY_TIME_FOR_QV_CURVE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.91
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 96;
        }
    },
    HOLD_DELAY_TIME_FOR_OVER_F_DERATE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.92
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 97;
        }
    },
    HOLD_LEAD_ACID_CHARGE_VOLT_REF { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.93
        public int getStartRegister() {
            return 99;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            if (inverter.isType6()) {
                return R.string.phrase_param_lead_acid_charge_volt_ref_12k;
            }
            if (inverter.isSnaSeries() || inverter.isHybird() || inverter.isAcCharger()) {
                return R.string.phrase_param_lead_acid_charge_volt_ref;
            }
            return -1;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAllInOne()) {
                return false;
            }
            return BATTERY_TYPE.LEAD_ACID.equals(currentInverter.getBatteryTypeFromModel());
        }
    },
    HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.94
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_lead_acid_discharge_cut_off_volt;
        }

        public int getStartRegister() {
            return 100;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isAllInOne();
        }
    },
    HOLD_LEAD_ACID_CHARGE_RATE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.95
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_charge_current;
        }

        public int getStartRegister() {
            return 101;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isType6Series()) {
                return (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true;
            }
            return false;
        }
    },
    HOLD_LEAD_ACID_DISCHARGE_RATE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.96
        public int getStartRegister() {
            return 102;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            if (inverter.isSnaSeries() || inverter.isLsp() || inverter.isHybird()) {
                return R.string.phrase_param_lead_acid_discharge_rate;
            }
            if (inverter.isType6()) {
                return R.string.phrase_param_dischg_current;
            }
            return -1;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return (!currentInverter.isType6Series() || currentInverter.isAllInOne()) ? currentInverter.isSnaSeries() : (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true;
        }
    },
    HOLD_FEED_IN_GRID_POWER_PERCENT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.97
        public int getStartRegister() {
            return 103;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            if (inverter.isType6()) {
                return R.string.phrase_param_feed_in_grid_power_percent_12k;
            }
            if (inverter.isSnaSeries()) {
                return R.string.phrase_param_feed_in_grid_power_percent;
            }
            if (inverter.isHybird() || inverter.isAcCharger()) {
                return R.string.phrase_param_feed_in_grid_power_percent_12k;
            }
            return -1;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isSnaSeries()) {
                return true;
            }
            if (currentInverter.isAllInOne()) {
                return false;
            }
            return (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true;
        }
    },
    HOLD_DISCHG_CUT_OFF_SOC_EOD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.98
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_dischg_cut_off_soc_eod;
        }

        public int getStartRegister() {
            return 105;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isAllInOne();
        }
    },
    HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_DISCHG { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.99
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.discharge_tempature_low_limit;
        }

        public int getStartRegister() {
            return 106;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_DISCHG { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.100
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.discharge_tempature_high_limit;
        }

        public int getStartRegister() {
            return 107;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_CHG { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.101
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.charge_temperature_low_limit;
        }

        public int getStartRegister() {
            return 108;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_CHG { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.102
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.charge_temperature_high_limit;
        }

        public int getStartRegister() {
            return 109;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_SET_MASTER_OR_SLAVE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.103
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_master_or_slave;
        }

        public int getStartRegister() {
            return 112;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isSnaSeries() || currentInverter.isHybird() || currentInverter.isAcCharger()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    HOLD_SET_COMPOSED_PHASE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.104
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_composed_phase;
        }

        public int getStartRegister() {
            return 113;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isSnaSeries() || currentInverter.isHybird() || currentInverter.isAcCharger()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    _12K_HOLD_OVF_DERATE_START_POINT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.105
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 115;
        }
    },
    HOLD_P_TO_USER_START_DISCHG { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.106
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.start_discharge_p_import_w;
        }

        public int getStartRegister() {
            return 116;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    MODEL_BIT_LITHIUM_TYPE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.107
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.battery_type_lithium;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_VBAT_START_DERATING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.108
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.on_grid_discharge_derate_vbatt_v;
        }

        public int getStartRegister() {
            return 118;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_CT_POWER_OFFSET { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.109
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ct_power_offset;
        }

        public int getStartRegister() {
            return 119;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true;
            }
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    HOLD_MAINTENANCE_COUNT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.110
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 122;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    HOLD_SOC_LOW_LIMIT_EPS_DISCHG { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.111
        public int getStartRegister() {
            return 125;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            if (inverter.isType6()) {
                return R.string.phrase_param_soc_low_limit_eps_dischg;
            }
            if (inverter.isSnaSeries()) {
                return R.string.fragment_remote_set_soc_low_limit_eps_dischg_button;
            }
            return -1;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isAllInOne();
        }
    },
    HOLD_OPTIMAL_CHG_DISCHG_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.112
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 126;
        }
    },
    HOLD_UVF_DERATE_START_POINT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.113
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 134;
        }
    },
    HOLD_OVF_DROOP_KOF { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.114
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.L2I;
        }
    },
    HOLD_SPEC_LOAD_COMPENSATE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.115
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.big_load_compensate_power;
        }

        public int getStartRegister() {
            return 137;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck();
            }
            return false;
        }
    },
    HOLD_FLOATING_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.116
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_floating_voltage;
        }

        public int getStartRegister() {
            return 144;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (!BATTERY_TYPE.LEAD_ACID.equals(currentInverter.getBatteryTypeFromModel())) {
                return false;
            }
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return true;
            }
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    HOLD_OUTPUT_CONFIGURATION { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.117
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_output_configuration;
        }

        public int getStartRegister() {
            return Opcodes.I2B;
        }
    },
    HOLD_NOMINAL_BATTERY_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.118
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_norminal_battery_voltage;
        }

        public int getStartRegister() {
            return Opcodes.LCMP;
        }
    },
    HOLD_EQUALIZATION_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.119
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_equalization_voltage;
        }

        public int getStartRegister() {
            return Opcodes.FCMPL;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    HOLD_EQUALIZATION_PERIOD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.120
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_equalization_period;
        }

        public int getStartRegister() {
            return Opcodes.FCMPG;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    HOLD_EQUALIZATION_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.121
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_equalization_time;
        }

        public int getStartRegister() {
            return Opcodes.DCMPL;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    HOLD_AC_FIRST_START_HOUR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.122
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_start_time_1;
        }

        public int getStartRegister() {
            return Opcodes.DCMPG;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_START_MINUTE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.123
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_start_time_1;
        }

        public int getStartRegister() {
            return Opcodes.DCMPG;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_END_HOUR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.124
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_end_time;
        }

        public int getStartRegister() {
            return Opcodes.IFEQ;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_END_MINUTE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.125
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_end_time;
        }

        public int getStartRegister() {
            return Opcodes.IFEQ;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_START_HOUR_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.126
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.IFNE;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_START_MINUTE_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.127
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_start_time_1;
        }

        public int getStartRegister() {
            return Opcodes.IFNE;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_END_HOUR_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.128
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_end_time_1;
        }

        public int getStartRegister() {
            return Opcodes.IFLT;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_END_MINUTE_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.129
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_end_time_1;
        }

        public int getStartRegister() {
            return Opcodes.IFLT;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_START_HOUR_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.130
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_start_time_2;
        }

        public int getStartRegister() {
            return Opcodes.IFGE;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_START_MINUTE_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.131
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_start_time_2;
        }

        public int getStartRegister() {
            return Opcodes.IFGE;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_END_HOUR_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.132
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_end_time_2;
        }

        public int getStartRegister() {
            return Opcodes.IFGT;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_FIRST_END_MINUTE_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.133
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_first_end_time_2;
        }

        public int getStartRegister() {
            return Opcodes.IFGT;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_AC_CHARGE_END_BATTERY_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.134
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_end_battery_soc;
        }

        public int getStartRegister() {
            return 161;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_BATTERY_WARNING_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.135
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_battery_warning_voltage;
        }

        public int getStartRegister() {
            return 162;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    HOLD_BATTERY_WARNING_RECOVERY_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.136
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_battery_warning_recovery_voltage;
        }

        public int getStartRegister() {
            return 163;
        }
    },
    HOLD_BATTERY_WARNING_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.137
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_battery_warning_soc;
        }

        public int getStartRegister() {
            return 164;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    HOLD_BATTERY_WARNING_RECOVERY_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.138
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_battery_warning_recovery_soc;
        }

        public int getStartRegister() {
            return 165;
        }
    },
    HOLD_BATTERY_LOW_TO_UTILITY_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.139
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_battery_low_to_utility_voltage;
        }

        public int getStartRegister() {
            return Opcodes.IF_ACMPNE;
        }
    },
    HOLD_BATTERY_LOW_TO_UTILITY_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.140
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_battery_low_to_utility_soc;
        }

        public int getStartRegister() {
            return Opcodes.GOTO;
        }
    },
    HOLD_AC_CHARGE_BATTERY_CURRENT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.141
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_chg_bat_current;
        }

        public int getStartRegister() {
            return 168;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    HOLD_ON_GRID_EOD_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.142
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_on_grid_eod_voltage;
        }

        public int getStartRegister() {
            return Opcodes.RET;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return (currentInverter.isType6Series() && !currentInverter.isAllInOne()) || currentInverter.isSnaSeries();
        }
    },
    HOLD_MAX_AC_INPUT_POWER { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.143
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_max_ac_input_power;
        }

        public int getStartRegister() {
            return Opcodes.ARETURN;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_MAX_GENERATOR_INPUT_POWER { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.144
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_gen_peak_shaving_power;
        }

        public int getStartRegister() {
            return Opcodes.RETURN;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return (currentInverter.isType6Series() && !currentInverter.isAllInOne()) || currentInverter.isSnaSeries();
        }
    },
    HOLD_VOLT_WATT_V1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.145
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.PUTFIELD;
        }
    },
    HOLD_VOLT_WATT_V2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.146
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.INVOKEVIRTUAL;
        }
    },
    HOLD_VOLT_WATT_DELAY_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.147
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.INVOKESPECIAL;
        }
    },
    HOLD_VOLT_WATT_P2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.148
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.INVOKESTATIC;
        }
    },
    HOLD_VREF { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.149
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.INVOKEINTERFACE;
        }
    },
    HOLD_VREF_ADJUSTMENT_TIME_CONSTANT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.150
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 186;
        }
    },
    HOLD_Q3 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.151
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.NEW;
        }
    },
    HOLD_Q4 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.152
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 188;
        }
    },
    HOLD_P1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.153
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 189;
        }
    },
    HOLD_P2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.154
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.ARRAYLENGTH;
        }
    },
    HOLD_P3 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.155
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.ATHROW;
        }
    },
    HOLD_UVF_DROOP_KUF { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.156
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return Opcodes.INSTANCEOF;
        }
    },
    OFF_GRID_HOLD_MAX_GEN_CHG_BAT_CURR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.157
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_bat_charge_current_limit;
        }

        public int getStartRegister() {
            return Opcodes.IFNULL;
        }
    },
    _12K_HOLD_STOP_DISCHG_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.158
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_stop_dischg_volt;
        }

        public int getStartRegister() {
            return 202;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return true;
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    _12K_HOLD_GRID_REGULATION { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.159
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_regulation;
        }

        public int getStartRegister() {
            return 203;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    _12K_HOLD_LEAD_CAPACITY { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.160
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }

        public int getStartRegister() {
            return 204;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (BATTERY_TYPE.LEAD_ACID.equals(currentInverter.getBatteryTypeFromModel()) && currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    _12K_HOLD_GRID_TYPE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.161
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_type;
        }

        public int getStartRegister() {
            return 205;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    _12K_HOLD_GRID_PEAK_SHAVING_POWER { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.162
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_peak_shaving_power;
        }

        public int getStartRegister() {
            return BuildConfig.VERSION_CODE;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    _12K_HOLD_GRID_PEAK_SHAVING_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.163
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_peak_shaving_soc_1;
        }

        public int getStartRegister() {
            return 207;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    _12K_HOLD_GRID_PEAK_SHAVING_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.164
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_peak_shaving_volt_1;
        }

        public int getStartRegister() {
            return 208;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    _12K_HOLD_SMART_LOAD_START_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.165
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_smart_load_start_volt;
        }

        public int getStartRegister() {
            return 213;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne() && currentInverter.getMachineType() != 1) {
                return true;
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
        }
    },
    _12K_HOLD_SMART_LOAD_END_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.166
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_smart_load_end_volt;
        }

        public int getStartRegister() {
            return 214;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne() && currentInverter.getMachineType() != 1) {
                return true;
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
        }
    },
    _12K_HOLD_SMART_LOAD_START_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.167
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_smart_load_start_soc;
        }

        public int getStartRegister() {
            return 215;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne() && currentInverter.getMachineType() != 1) {
                return true;
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
        }
    },
    _12K_HOLD_SMART_LOAD_END_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.168
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_smart_load_end_soc;
        }

        public int getStartRegister() {
            return 216;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne() && currentInverter.getMachineType() != 1) {
                return true;
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
        }
    },
    _12K_HOLD_START_PV_POWER { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.169
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_start_pv_power;
        }

        public int getStartRegister() {
            return 217;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne() && currentInverter.getMachineType() != 1) {
                return true;
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
        }
    },
    _12K_HOLD_GRID_PEAK_SHAVING_SOC_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.170
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_peak_shaving_soc_2;
        }

        public int getStartRegister() {
            return 218;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    _12K_HOLD_GRID_PEAK_SHAVING_VOLT_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.171
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_grid_peak_shaving_volt_2;
        }

        public int getStartRegister() {
            return 219;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    _12K_HOLD_AC_COUPLE_START_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.172
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_couple_start_soc;
        }

        public int getStartRegister() {
            return 220;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            ROLE role = userData.getRole();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return true;
            }
            if (!currentInverter.isSnaSeries()) {
                return false;
            }
            if (ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck()) {
                return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
            }
            return false;
        }
    },
    _12K_HOLD_AC_COUPLE_END_SOC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.173
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_couple_end_soc;
        }

        public int getStartRegister() {
            return 221;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            ROLE role = userData.getRole();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return true;
            }
            if (!currentInverter.isSnaSeries()) {
                return false;
            }
            if (ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck()) {
                return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
            }
            return false;
        }
    },
    _12K_HOLD_AC_COUPLE_START_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.174
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_couple_start_volt;
        }

        public int getStartRegister() {
            return 222;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            ROLE role = userData.getRole();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return true;
            }
            if (!currentInverter.isSnaSeries()) {
                return false;
            }
            if (ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck()) {
                return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
            }
            return false;
        }
    },
    _12K_HOLD_AC_COUPLE_END_VOLT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.175
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_couple_end_volt;
        }

        public int getStartRegister() {
            return 223;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            ROLE role = userData.getRole();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return true;
            }
            if (!currentInverter.isSnaSeries()) {
                return false;
            }
            if (ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck()) {
                return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() > 11;
            }
            return false;
        }
    },
    HOLD_LCD_VERSION { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.176
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.firmware_lcd_version;
        }

        public int getStartRegister() {
            return 224;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if ((currentInverter.isAcCharger() || currentInverter.isHybird()) && ROLE.ADMIN.equals(userData.getRole())) {
                return true;
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne() || currentInverter.isGen3_6K()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_CUSTOM_PASSWORD { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.177
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.lcd_password;
        }

        public int getStartRegister() {
            return 225;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    BASIC_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.178
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.basic_setting;
        }
    },
    ADVANCED_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.179
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.page_maintain_remote_title_advanced;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return true;
        }
    },
    RESET { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.180
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.page_maintain_remote_set_label_reset_set;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    APPLICATION_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.181
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.page_maintain_remote_set_label_application_set;
        }
    },
    GENERAL { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.182
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.general;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return true;
        }
    },
    EPS_OUTPUT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.183
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.eps_output;
        }
    },
    BASIC_PROTECTION_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.184
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.protection_setting;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    GRID_SELL { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.185
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.grid_sell;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.isGigabiz1User()) {
                return false;
            }
            Inverter currentInverter = userData.getCurrentInverter();
            return currentInverter.isHybird() || currentInverter.isAcCharger() || currentInverter.isType6Series();
        }
    },
    PARALLEL { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.186
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_parallel;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isHybird() || currentInverter.isAcCharger() || currentInverter.isType6Series()) {
                return true;
            }
            if (currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    ADVANCED_PROTECTION_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.187
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.protection_setting;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isType6()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    CT_METER_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.188
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.ct_meter_setting;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    PV_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.189
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.pv_setting;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    ALL_TO_DEFAULT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.190
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.page_maintain_remote_set_label_all_2_default;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true;
            }
            return false;
        }
    },
    RESET_TO_FACTORY { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.191
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.page_maintain_remote_set_label_reset_2_factory;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    BATTERY_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.192
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.battery_setting;
        }
    },
    GENERATOR_PORT_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.193
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.generator_port_setting;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series()) {
                return currentInverter.getMachineType() == 0;
            }
            if (currentInverter.isSnaSeries() && (ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck())) {
                return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() >= 11;
            }
            return false;
        }
    },
    GENERATOR_FUNCTION { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.194
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.generator_function;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series()) {
                return currentInverter.getMachineType() == 0;
            }
            if (currentInverter.isSnaSeries() && (ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck())) {
                return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() >= 11;
            }
            return false;
        }
    },
    AC_COUPLING_FUNCTION { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.195
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.ac_coupling_function;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !ROLE.VIEWER.equals(userData.getRole())) {
                return currentInverter.getMachineType() == 0;
            }
            if (currentInverter.isSnaSeries() && (ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck())) {
                return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() >= 11;
            }
            return false;
        }
    },
    SMART_LOAD_FUNCTION { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.196
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.smart_load_function;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series()) {
                return currentInverter.getMachineType() == 0;
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() >= 11;
        }
    },
    WORKING_MODE_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.197
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.working_mode_setting;
        }
    },
    FUNC_EPS_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.198
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_eps_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return true;
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    FUNC_OVF_LOAD_DERATE_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.199
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_ovf_load_derate_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return (ROLE.VIEWER.equals(role) || role.getInstallerLevelCheck()) ? false : true;
            }
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return (ROLE.VIEWER.equals(role) || role.getInstallerLevelCheck()) ? false : true;
            }
            return false;
        }
    },
    FUNC_DRMS_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.200
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_drms_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            return ((!currentInverter.isAcCharger() && !currentInverter.isHybird()) || ROLE.VIEWER.equals(role) || role.getInstallerLevelCheck()) ? (!currentInverter.isType6Series() || currentInverter.isAllInOne() || ROLE.VIEWER.equals(role) || role.getInstallerLevelCheck() || userData.getClusterId() >= 4) ? false : true : userData.getClusterId() < 4;
        }
    },
    FUNC_ANTI_ISLAND_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.201
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.anti_island_enable;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            if (!currentInverter.isType6() || currentInverter.isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    FUNC_NEUTRAL_DETECT_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.202
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.neutral_detect_enable;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    FUNC_GRID_ON_POWER_SS_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.203
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_grid_on_power_ss_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return (ROLE.VIEWER.equals(role) || role.getInstallerLevelCheck()) ? false : true;
            }
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return (ROLE.VIEWER.equals(role) || role.getInstallerLevelCheck()) ? false : true;
            }
            return false;
        }
    },
    FUNC_AC_CHARGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.204
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_ac_charge_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAllInOne()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return currentInverter.isType6Series() || currentInverter.isAcCharger() || currentInverter.isHybird();
        }
    },
    FUNC_SW_SEAMLESSLY_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.205
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_sw_seamlessly_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    FUNC_SET_TO_STANDBY { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.206
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_set_to_standby;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    FUNC_FORCED_DISCHG_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.207
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_forced_dischg_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return true;
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    FUNC_FORCED_CHG_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.208
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_forced_chg_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isType6Series();
        }
    },
    FUNC_ISO_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.209
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.iso_enable;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(role);
            }
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck();
            }
            if (currentInverter.isSnaSeries()) {
                return ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck();
            }
            return false;
        }
    },
    FUNC_GFCI_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.210
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.gfci_enable;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    FUNC_DCI_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.211
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.dci_enable;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return ROLE.ADMIN.equals(userData.getRole());
        }
    },
    FUNC_FEED_IN_GRID_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.212
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_feed_in_grid_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return true;
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    FUNC_LSP_SET_TO_STANDBY { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.213
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_set_to_standby;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isLsp()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_PV_GRID_OFF_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.214
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_pv_grid_off_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    FUNC_RUN_WITHOUT_GRID { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.215
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_run_without_grid_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isHybird() || currentInverter.isAcCharger()) {
                return (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true;
            }
            if (!currentInverter.isType6() || currentInverter.isAllInOne()) {
                return false;
            }
            return (userData.isGigabiz1User() && ROLE.VIEWER.equals(userData.getRole())) ? false : true;
        }
    },
    FUNC_MICRO_GRID_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.216
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_micro_grid_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isHybird() || currentInverter.isAcCharger()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    FUNC_BAT_SHARED { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.217
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_battery_shared_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isOffGrid()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return currentInverter.isType6() && !currentInverter.isAllInOne();
        }
    },
    FUNC_CHARGE_LAST { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.218
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_charge_last;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return (currentInverter.isAcCharger() || currentInverter.isHybird()) ? !ROLE.VIEWER.equals(userData.getRole()) || userData.isGigabiz1User() : !currentInverter.isType6Series() || currentInverter.isAllInOne();
        }
    },
    FUNC_BUZZER_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.219
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_buzzer_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_TAKE_LOAD_TOGETHER { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.220
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_take_load_together;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            return currentInverter.isSnaSeries();
        }
    },
    FUNC_GREEN_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.221
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_green_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isSnaSeries()) {
                return ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck();
            }
            return false;
        }
    },
    FUNC_CT_DIRECTION_REVERSED { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.222
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_ct_direction_reversed;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isType6Series()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() >= 11;
        }
    },
    FUNC_TOTAL_LOAD_COMPENSATION_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.223
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_total_load_compensation_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_PV_ARC_FAULT_CLEAR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.224
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.pv_arc_fault_clear;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6() || currentInverter.is7_10KDevice()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isSnaSeries()) {
                return false;
            }
            if (userData.getClusterId() == 4 || userData.getClusterId() == 100) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_PV_SELL_TO_GRID_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.225
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.export_pv_only;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    MODEL_BIT_MEASUREMENT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.226
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.measurement;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isHybird() || currentInverter.isAcCharger()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isType6() || currentInverter.is7_10KDevice() || currentInverter.isGen3_6K()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_GRID_PEAK_SHAVING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.227
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_grid_peak_shaving;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    BIT_CT_SAMPLE_RATIO { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.228
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.ct;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() >= 11;
        }
    },
    MODEL_BIT_METER_BRAND { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.229
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.meter;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    FUNC_GEN_PEAK_SHAVING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.230
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_gen_peak_shaving_power;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_BAT_CHARGE_CONTROL { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.231
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_bat_charge_control;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isType6Series();
        }
    },
    FUNC_BAT_DISCHARGE_CONTROL { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.232
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_bat_discharge_control;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return true;
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    FUNC_AC_COUPLING_FUNCTION { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.233
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.ac_coupling_function;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            ROLE role = userData.getRole();
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !ROLE.VIEWER.equals(userData.getRole())) {
                return currentInverter.getMachineType() == 0;
            }
            if (currentInverter.isSnaSeries() && (ROLE.ADMIN.equals(role) || role.getOwnerLevelCheck())) {
                return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() >= 11;
            }
            return false;
        }
    },
    FUNC_PV_ARC { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.234
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_pv_arc;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6() || currentInverter.is7_10KDevice()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isSnaSeries()) {
                return false;
            }
            if (userData.getClusterId() == 4 || userData.getClusterId() == 100) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_SMART_LOAD_ENABLE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.235
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_smart_load;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series()) {
                return currentInverter.getMachineType() == 0;
            }
            if (!currentInverter.isSnaSeries() || ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return currentInverter.getSubDeviceTypeValue() == 131 || currentInverter.getHardwareVersion() >= 11;
        }
    },
    FUNC_RSD_DISABLE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.236
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.rsd;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            if (!currentInverter.isSnaSeries()) {
                return false;
            }
            if (userData.getClusterId() == 4 || userData.getClusterId() == 100) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_ON_GRID_ALWAYS_ON { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.237
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_on_grid_always_on;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return true;
            }
            if (currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_RUN_WITHOUT_GRID_12K { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.238
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_run_without_grid_en_12k;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isType6Series() && !currentInverter.isAllInOne()) {
                return true;
            }
            if (currentInverter.isType6Series()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_N_PE_CONNECT_INNER_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.239
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_n_pe_connect_inner_en;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_RETAIN_SHUTDOWN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.240
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_retain_shutdown;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_RETAIN_STANDBY { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.241
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_retain_standby;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            return false;
        }
    },
    HOLD_OFFLINE_TIMEOUT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.242
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_offline_timeout;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird() || currentInverter.isSnaSeries()) {
                return ROLE.ADMIN.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_PARALLEL_DATA_SYNC_EN { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.243
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.parallel_setting_data_sync;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            if (ROLE.ADMIN.equals(userData.getRole())) {
                return true;
            }
            if (ROLE.VIEWER.equals(userData.getRole())) {
                return false;
            }
            return userData.getClusterId() == 4 || userData.getClusterId() == 100;
        }
    },
    BIT_AC_CHARGE_TYPE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.244
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.ac_charge_based_on;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return (currentInverter.isHybird() || currentInverter.isAcCharger()) ? false : true;
        }
    },
    FUNC_BATTERY_BACKUP_CTRL { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.245
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_battery_backup_ctrl;
        }
    },
    MODEL_BIT_BATTERY_TYPE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.246
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.battery_type;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    MODEL_BIT_LEAD_ACID_TYPE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.247
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.lead_acid_capacity_ah;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    BACKUP_MODE_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.248
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.backup_mode_setting;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    PEAK_SHAVING_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.249
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.peak_shaving_setting;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    AC_CHARGE_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.250
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.ac_charge_setting;
        }
    },
    AC_FIRST_MODE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.251
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.ac_first_mode;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return userData.getCurrentInverter().isSnaSeries();
        }
    },
    PV_CHARGE_PRIORITY { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.252
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.pv_charge_priority;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return true;
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    FORCED_DISCHARGE_SETTING { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.253
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.forced_discharge_setting;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return true;
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    SELF_CONSUMPTION { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.254
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_working_mode_0;
        }
    },
    INV_REBOOT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.255
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.restart_inverter;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    Battery_Charge_Discharge { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.256
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.battery_charge_discharge;
        }
    },
    HOLD_SYSTEM_CHARGE_SOC_LIMIT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.257
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.system_charge_soc_limit;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return !userData.getCurrentInverter().isAllInOne();
        }
    },
    _12K_HOLD_GRID_PEAK_SHAVING_POWER_2 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.258
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.peak_shaving_power_2_kw;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_AC_CHARGE_END_BATTERY_VOLTAGE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.259
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkEnabled(boolean z) {
            return z;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            return true;
        }

        public int getStartRegister() {
            return Opcodes.IF_ICMPEQ;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return (!inverter.isType6() && inverter.isSnaSeries()) ? R.string.phrase_param_ac_charge_end_battery_voltage : R.string.phrase_param_stop_ac_chg_volt;
        }
    },
    HOLD_SYSTEM_CHARGE_VOLT_LIMIT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.260
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.system_charge_volt_limit_v;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isHybird() || currentInverter.isAcCharger()) {
                return true;
            }
            return currentInverter.isType6Series() && !currentInverter.isAllInOne();
        }
    },
    HOLD_AC_CHARGE_SOC_LIMIT { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.261
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_ac_charge_soc_limit;
        }

        public int getStartRegister() {
            return 67;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            return currentInverter.isHybird() || currentInverter.isAcCharger() || currentInverter.isType6Series();
        }
    },
    _12K_HOLD_GEN_COOL_DOWN_TIME { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.262
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_bat_charge_current_limit;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return !ROLE.VIEWER.equals(userData.getRole());
        }
    },
    HOLD_COM_ADDR { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.263
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_param_com_addr;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            Inverter currentInverter = userData.getCurrentInverter();
            if (currentInverter.isAcCharger() || currentInverter.isHybird()) {
                return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
            }
            if (!currentInverter.isType6Series() || currentInverter.isAllInOne()) {
                return false;
            }
            return (ROLE.VIEWER.equals(userData.getRole()) || userData.getRole().getInstallerLevelCheck()) ? false : true;
        }
    },
    BIT_FAN_1_MAX_SPEED { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.264
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.fan_1_max_speed;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    BIT_FAN_2_MAX_SPEED { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.265
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.fan_2_max_speed;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_FAN_SPEED_SLOPE_CTRL_1 { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.266
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.fan_speed_slope_1;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FAN_SPEED_CONTROL { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.267
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return R.string.phrase_func_param_fan_speed_control;
        }

        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public boolean checkVisible(UserData userData) {
            if (userData.getCurrentInverter().isSnaSeries()) {
                return !ROLE.VIEWER.equals(userData.getRole());
            }
            return false;
        }
    },
    FUNC_ENERTEK_WORKING_MODE { // from class: com.nfcx.luxinvpower.global.bean.param.PARAM.268
        @Override // com.nfcx.luxinvpower.global.bean.param.PARAM
        public int getResourceId(Inverter inverter) {
            return -1;
        }
    };

    public boolean checkEnabled(boolean z) {
        return z;
    }

    public boolean checkVisible(UserData userData) {
        return false;
    }

    public abstract int getResourceId(Inverter inverter);

    public boolean checkInvisible(UserData userData) {
        if (userData.getRole() == null || userData.getCurrentInverter() == null) {
            return true;
        }
        return !checkVisible(userData);
    }
}

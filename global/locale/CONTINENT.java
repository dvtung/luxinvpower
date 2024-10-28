package com.nfcx.luxinvpower.global.locale;

import com.nfcx.luxinvpower.R;

/* loaded from: classes2.dex */
public enum CONTINENT {
    ASIA { // from class: com.nfcx.luxinvpower.global.locale.CONTINENT.1
        @Override // com.nfcx.luxinvpower.global.locale.CONTINENT
        public int getTextResourceId() {
            return R.string.continent_asia;
        }
    },
    EUROPE { // from class: com.nfcx.luxinvpower.global.locale.CONTINENT.2
        @Override // com.nfcx.luxinvpower.global.locale.CONTINENT
        public int getTextResourceId() {
            return R.string.continent_europe;
        }
    },
    AFRICA { // from class: com.nfcx.luxinvpower.global.locale.CONTINENT.3
        @Override // com.nfcx.luxinvpower.global.locale.CONTINENT
        public int getTextResourceId() {
            return R.string.continent_africa;
        }
    },
    OCEANIA { // from class: com.nfcx.luxinvpower.global.locale.CONTINENT.4
        @Override // com.nfcx.luxinvpower.global.locale.CONTINENT
        public int getTextResourceId() {
            return R.string.continent_oceania;
        }
    },
    NORTH_AMERICA { // from class: com.nfcx.luxinvpower.global.locale.CONTINENT.5
        @Override // com.nfcx.luxinvpower.global.locale.CONTINENT
        public int getTextResourceId() {
            return R.string.continent_north_america;
        }
    },
    SOUTH_AMERICA { // from class: com.nfcx.luxinvpower.global.locale.CONTINENT.6
        @Override // com.nfcx.luxinvpower.global.locale.CONTINENT
        public int getTextResourceId() {
            return R.string.continent_south_america;
        }
    };

    public abstract int getTextResourceId();

    public String getName() {
        return name();
    }
}

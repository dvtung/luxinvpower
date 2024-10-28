package com.nfcx.luxinvpower.global;

import android.content.Context;
import android.os.AsyncTask;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.bean.cluster.CLUSTER_GROUP;
import com.nfcx.luxinvpower.global.bean.cluster.Cluster;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.custom.mpChart.NoZeroValueFormatter;
import com.nfcx.luxinvpower.global.custom.mpChart.NumberAxisSOCValueFormatter;
import com.nfcx.luxinvpower.global.custom.mpChart.NumberAxisValueFormatter;
import com.nfcx.luxinvpower.global.custom.mpChart.TimeAxisValueFormatter;
import com.nfcx.luxinvpower.global.locale.CONTINENT;
import com.nfcx.luxinvpower.global.locale.TIMEZONE;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Custom;
import com.nfcx.luxinvpower.version.Version;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public class GlobalInfo {
    public static final int DATA_DATE_DAY_PICKER = 3;
    public static final int DATA_DATE_MONTH_PICKER = 4;
    public static final int DATA_DATE_YEAR_PICKER = 5;
    public static final int LOCAL_SET_DATE_DAY_PICKER = 0;
    public static final int LOCAL_SET_TIME_PICKER = 1;
    public static final int OVERVIEW_DATE_DAY_PICKER = 0;
    public static final int OVERVIEW_DATE_MONTH_PICKER = 1;
    public static final int OVERVIEW_DATE_YEAR_PICKER = 2;
    public static final int REMOTE_SET_DATE_DAY_PICKER = 6;
    public static final int REMOTE_SET_TIME_PICKER = 7;
    public static List<Property> batControlList;
    private static final GlobalInfo instance = new GlobalInfo();
    private List<Property> continents;
    private String currentClusterGroup;
    private List<Property> firstClusterServerIdProperties;
    private List<Property> firstClusterServerProperties;
    private boolean inited;
    private boolean initing;
    private String language;
    private List<Property> timezones;
    private UserData userData = new UserData();
    private Integer defaultTimezoneIndex = Integer.valueOf(TIMEZONE.ZERO.ordinal());
    private Map<Long, Cluster> firstClusterMap = new HashMap();
    private Map<Long, Cluster> secondClusterMap = new HashMap();
    private Map<Long, Cluster> indiaClusterMap = new HashMap();
    private IAxisValueFormatter timeAxisValueFormatter = new TimeAxisValueFormatter();
    private IAxisValueFormatter numberAxisValueFormatter = new NumberAxisValueFormatter();
    private IAxisValueFormatter numberAxisSOCValueFormatter = new NumberAxisSOCValueFormatter();
    private IValueFormatter noZeroValueFormatter = new NoZeroValueFormatter();

    public static GlobalInfo getInstance() {
        return instance;
    }

    public String getCustomDoname() {
        return Constants.CLUSTER_GROUP_INDIA.equals(this.currentClusterGroup) ? "http://ind.solarcloudsystem.com/WManage/" : !Tool.isEmpty(Custom.CUSTOM_DONAME) ? "http://na.solarcloudsystem.com/WManage/" : getBaseUrl();
    }

    public String getBaseUrl() {
        if (Constants.CLUSTER_GROUP_INDIA.equals(this.currentClusterGroup)) {
            Cluster cluster = this.indiaClusterMap.get(Long.valueOf(this.userData.getClusterId()));
            return cluster != null ? cluster.getClusterPrefixUrl() + "/WManage/" : Version.INIDA_DEFAULT_BASE_URL;
        }
        if (Constants.CLUSTER_GROUP_SECOND.equals(this.currentClusterGroup)) {
            Cluster cluster2 = this.secondClusterMap.get(Long.valueOf(this.userData.getClusterId()));
            return cluster2 != null ? cluster2.getClusterPrefixUrl() + "/WManage/" : Version.SECOND_DEFAULT_BASE_URL;
        }
        Cluster cluster3 = this.firstClusterMap.get(Long.valueOf(this.userData.getClusterId()));
        return cluster3 != null ? cluster3.getClusterPrefixUrl() + "/WManage/" : Version.DEFAULT_BASE_URL;
    }

    public String getMajorUrl() {
        return Constants.CLUSTER_GROUP_SECOND.equals(this.currentClusterGroup) ? Version.SECOND_MAJOR_URL : Version.MAJOR_URL;
    }

    public static boolean getHideClusterAtRegisterPage() {
        return Constants.CLUSTER_GROUP_INDIA.equals(getInstance().getCurrentClusterGroup());
    }

    public static CLUSTER_GROUP getClusterGroup() {
        if (Constants.CLUSTER_GROUP_INDIA.equals(getInstance().getCurrentClusterGroup())) {
            return CLUSTER_GROUP.INDIA;
        }
        return CLUSTER_GROUP.FIRST;
    }

    public static final int getDefaultClusterIdIndex() {
        return Constants.CLUSTER_GROUP_INDIA.equals(getInstance().getCurrentClusterGroup()) ? 0 : 1;
    }

    public static final long getDefaultClusterId() {
        return Constants.CLUSTER_GROUP_INDIA.equals(getInstance().getCurrentClusterGroup()) ? 200L : 2L;
    }

    public void initializeGlobalInfo(Context context, Locale locale) {
        if (this.inited) {
            return;
        }
        if (locale.getLanguage().indexOf("zh") >= 0) {
            this.language = "zh_CN";
        } else {
            this.language = "en";
        }
        Cluster cluster = new Cluster();
        cluster.setClusterId(1L);
        cluster.setClusterName("Asia");
        cluster.setClusterPrefixUrl("http://as.luxpowertek.com");
        cluster.setMajor(true);
        this.firstClusterMap.put(Long.valueOf(cluster.getClusterId()), cluster);
        Cluster cluster2 = new Cluster();
        cluster2.setClusterId(2L);
        cluster2.setClusterName("Europe");
        cluster2.setClusterPrefixUrl("http://eu.luxpowertek.com");
        cluster2.setMajor(false);
        this.firstClusterMap.put(Long.valueOf(cluster2.getClusterId()), cluster2);
        Cluster cluster3 = new Cluster();
        cluster3.setClusterId(3L);
        cluster3.setClusterName("Africa");
        cluster3.setClusterPrefixUrl("http://af.luxpowertek.com");
        cluster3.setMajor(false);
        this.firstClusterMap.put(Long.valueOf(cluster3.getClusterId()), cluster3);
        Cluster cluster4 = new Cluster();
        cluster4.setClusterId(4L);
        cluster4.setClusterName("America");
        cluster4.setClusterPrefixUrl("http://na.luxpowertek.com");
        cluster4.setMajor(true);
        this.firstClusterMap.put(Long.valueOf(cluster4.getClusterId()), cluster4);
        initializeFirstClusterServerIdProperties(context);
        initializeFirstClusterServerProperties(context);
        Cluster cluster5 = new Cluster();
        cluster5.setClusterId(100L);
        cluster5.setClusterName("America (Stand-alone)");
        cluster5.setClusterPrefixUrl("http://us.luxpowertek.com");
        cluster5.setMajor(true);
        this.secondClusterMap.put(Long.valueOf(cluster5.getClusterId()), cluster5);
        Cluster cluster6 = new Cluster();
        cluster6.setClusterId(200L);
        cluster6.setClusterName("India (Stand-alone)");
        cluster6.setClusterPrefixUrl("http://ind.luxpowertek.com");
        cluster6.setMajor(true);
        this.indiaClusterMap.put(Long.valueOf(cluster6.getClusterId()), cluster6);
        initializeContinents(context);
        initializeTimezones();
        String displayName = TimeZone.getDefault().getDisplayName(false, 0);
        int indexOf = displayName.indexOf(":");
        if (indexOf > 3) {
            try {
                TIMEZONE enumByNumber = TIMEZONE.getEnumByNumber(Integer.parseInt(displayName.substring(3, indexOf)));
                getInstance().defaultTimezoneIndex = Integer.valueOf(enumByNumber.ordinal());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList arrayList = new ArrayList();
        batControlList = arrayList;
        arrayList.add(new Property("-1", context.getString(R.string.phrase_please_select)));
        batControlList.add(new Property(Boolean.TRUE.toString(), "Volt"));
        batControlList.add(new Property(Boolean.FALSE.toString(), "SOC"));
        this.inited = true;
    }

    public void execGlobalInfoTask() {
        new GlobalInfoTask().execute("ASIA");
        new GlobalInfoTask().execute("AF");
        new GlobalInfoTask().execute("NA");
    }

    private void initializeFirstClusterServerIdProperties(Context context) {
        ArrayList arrayList = new ArrayList();
        this.firstClusterServerIdProperties = arrayList;
        arrayList.add(new Property(String.valueOf(1L), context.getString(R.string.continent_asia)));
        this.firstClusterServerIdProperties.add(new Property(String.valueOf(2L), context.getString(R.string.continent_europe)));
        this.firstClusterServerIdProperties.add(new Property(String.valueOf(3L), context.getString(R.string.continent_africa)));
        this.firstClusterServerIdProperties.add(new Property(String.valueOf(4L), context.getString(R.string.continent_north_america)));
    }

    public List<Property> getFirstClusterServerIds(Context context) {
        if (this.firstClusterServerIdProperties == null) {
            initializeFirstClusterServerIdProperties(context);
        }
        return this.firstClusterServerIdProperties;
    }

    private void initializeFirstClusterServerProperties(Context context) {
        ArrayList arrayList = new ArrayList();
        this.firstClusterServerProperties = arrayList;
        arrayList.add(new Property(Constants.DONGLE_SERVER_ASIA, context.getString(R.string.continent_asia)));
        this.firstClusterServerProperties.add(new Property(Constants.DONGLE_SERVER_EUROPE, context.getString(R.string.continent_europe)));
        this.firstClusterServerProperties.add(new Property(Constants.DONGLE_SERVER_AFRICA, context.getString(R.string.continent_africa)));
        this.firstClusterServerProperties.add(new Property(Constants.DONGLE_SERVER_USA, context.getString(R.string.continent_north_america)));
        this.firstClusterServerProperties.add(new Property(Constants.DONGLE_SERVER_LOCAL, context.getString(R.string.continent_local_server)));
    }

    public List<Property> getFirstClusterServers(Context context) {
        if (this.firstClusterServerProperties == null) {
            initializeFirstClusterServerProperties(context);
        }
        return this.firstClusterServerProperties;
    }

    public List<Property> getSecondClusterServers() {
        return new ArrayList();
    }

    public Map<Long, Cluster> getClusterMap() {
        if (Constants.INDIA_COUNTRY_CODE.equals(this.currentClusterGroup)) {
            return this.indiaClusterMap;
        }
        if (Constants.CLUSTER_GROUP_SECOND.equals(this.currentClusterGroup)) {
            return this.secondClusterMap;
        }
        return this.firstClusterMap;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getLanguageEnumName() {
        return "zh_CN".equals(this.language) ? "CHINESE" : "ENGLISH";
    }

    public UserData getUserData() {
        return this.userData;
    }

    public Integer getDefaultTimezoneIndex() {
        return this.defaultTimezoneIndex;
    }

    private void initializeTimezones() {
        this.timezones = new ArrayList();
        for (TIMEZONE timezone : TIMEZONE.values()) {
            this.timezones.add(new Property(timezone.name(), timezone.getText()));
        }
    }

    public List<Property> getTimezones() {
        if (this.timezones == null) {
            initializeTimezones();
        }
        return this.timezones;
    }

    private void initializeContinents(Context context) {
        this.continents = new ArrayList();
        for (CONTINENT continent : CONTINENT.values()) {
            this.continents.add(new Property(continent.name(), context.getString(continent.getTextResourceId())));
        }
    }

    public List<Property> getContinents(Context context) {
        if (this.continents == null) {
            initializeContinents(context);
        }
        return this.continents;
    }

    public static List<Property> getBatControlList() {
        return batControlList;
    }

    public IAxisValueFormatter getTimeAxisValueFormatter() {
        return this.timeAxisValueFormatter;
    }

    public IAxisValueFormatter getNumberAxisValueFormatter() {
        return this.numberAxisValueFormatter;
    }

    public IAxisValueFormatter getNumberAxisSOCValueFormatter() {
        return this.numberAxisSOCValueFormatter;
    }

    public IValueFormatter getNoZeroValueFormatter() {
        return this.noZeroValueFormatter;
    }

    public boolean isIniting() {
        return this.initing;
    }

    public boolean isInited() {
        return this.inited;
    }

    public String getCurrentClusterGroup() {
        return this.currentClusterGroup;
    }

    public void setCurrentClusterGroup(String str) {
        this.currentClusterGroup = str;
    }

    /* loaded from: classes2.dex */
    private static class GlobalInfoTask extends AsyncTask<String, Object, Void> {
        private GlobalInfoTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String[] strArr) {
            try {
                try {
                    GlobalInfo.getInstance().initing = true;
                    HashMap hashMap = new HashMap();
                    hashMap.put("language", GlobalInfo.getInstance().getLanguage());
                    String baseUrl = GlobalInfo.getInstance().getBaseUrl();
                    if ("ASIA".equals(strArr[0])) {
                        baseUrl = Version.DEFAULT_BASE_URL;
                    } else if ("AF".equals(strArr[0])) {
                        baseUrl = Version.AF_BASE_URL;
                    } else if ("NA".equals(strArr[0])) {
                        baseUrl = Version.NA_BASE_URL;
                    }
                    if (HttpTool.postJson(baseUrl + "api/global/buildGlobalInfo", hashMap).getBoolean("success") && !GlobalInfo.getInstance().inited) {
                        GlobalInfo.getInstance().inited = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                GlobalInfo.getInstance().initing = false;
                return null;
            } catch (Throwable th) {
                GlobalInfo.getInstance().initing = false;
                throw th;
            }
        }
    }
}

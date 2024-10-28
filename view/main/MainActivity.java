package com.nfcx.luxinvpower.view.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.global.custom.android.dialog.CustomTimePickerDialog;
import com.nfcx.luxinvpower.global.custom.android.dialog.DayDatePickerDialog;
import com.nfcx.luxinvpower.global.custom.android.dialog.DayOnDateSetListener;
import com.nfcx.luxinvpower.global.custom.android.dialog.MonthDatePickerDialog;
import com.nfcx.luxinvpower.global.custom.android.dialog.MonthOnDateSetListener;
import com.nfcx.luxinvpower.global.custom.android.dialog.TimePickerSetListener;
import com.nfcx.luxinvpower.global.custom.android.dialog.YearDatePickerDialog;
import com.nfcx.luxinvpower.global.custom.android.dialog.YearOnDateSetListener;
import com.nfcx.luxinvpower.view.main.fragment.lv1.AbstractItemFragment;
import com.nfcx.luxinvpower.view.main.fragment.lv1.Lv112KRemoteSetFragment;
import com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment;
import com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1EventFragment;
import com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment;
import com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment;
import com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1RemoteSetFragment;
import com.nfcx.luxinvpower.view.main.fragment.lv2.Lv2RemoteSetFragment;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;

/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity {
    public static final String USER_INFO = "userInfo";
    public static MainActivity instance;
    private int deviceType;
    private AbstractItemFragment[] fragmentList;
    private FragmentManager fragmentManager;
    private Lv112KRemoteSetFragment lv112KRemoteSetFragment;
    private Lv1DataFragment lv1DataFragment;
    private Lv1EventFragment lv1EventFragment;
    private Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment;
    private Lv1OverviewFragment lv1OverviewFragment;
    private Lv1RemoteSetFragment lv1RemoteSetFragment;
    private Lv2RemoteSetFragment lv2RemoteSetFragment;
    private int mFragment = 0;
    private int toFragment = 0;
    private Boolean useNewSettingPage;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        instance = this;
        if (!PLATFORM.LUX_POWER.equals(GlobalInfo.getInstance().getUserData().getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        getSupportActionBar().hide();
        this.lv1OverviewFragment = new Lv1OverviewFragment();
        this.lv1DataFragment = new Lv1DataFragment();
        this.lv1EventFragment = new Lv1EventFragment();
        this.lv2RemoteSetFragment = new Lv2RemoteSetFragment();
        this.lv1RemoteSetFragment = new Lv1RemoteSetFragment();
        this.lv1OffGridRemoteSetFragment = new Lv1OffGridRemoteSetFragment();
        this.lv112KRemoteSetFragment = new Lv112KRemoteSetFragment();
        Boolean valueOf = Boolean.valueOf(getSharedPreferences("userInfo", 0).getBoolean(Constants.useNewSettingPage, false));
        this.useNewSettingPage = valueOf;
        AbstractItemFragment[] abstractItemFragmentArr = new AbstractItemFragment[4];
        abstractItemFragmentArr[0] = this.lv1OverviewFragment;
        abstractItemFragmentArr[1] = this.lv1DataFragment;
        abstractItemFragmentArr[2] = this.lv1EventFragment;
        abstractItemFragmentArr[3] = valueOf.booleanValue() ? this.lv2RemoteSetFragment : this.lv1RemoteSetFragment;
        this.fragmentList = abstractItemFragmentArr;
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        if (GlobalInfo.getInstance().getUserData().isReadonly()) {
            bottomNavigationView.getMenu().removeItem(R.id.navigation_set);
        }
        this.fragmentManager = getSupportFragmentManager();
        NavigationUI.setupWithNavController(bottomNavigationView, Navigation.findNavController(this, R.id.nav_host_fragment_activity_main));
        this.lv1OverviewFragment.isAdded = true;
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.main.MainActivity.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code restructure failed: missing block: B:7:0x0067, code lost:
            
                return true;
             */
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public boolean onNavigationItemSelected(android.view.MenuItem r4) {
                /*
                    r3 = this;
                    com.nfcx.luxinvpower.view.main.MainActivity r0 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    androidx.fragment.app.FragmentManager r0 = com.nfcx.luxinvpower.view.main.MainActivity.access$000(r0)
                    androidx.fragment.app.FragmentTransaction r0 = r0.beginTransaction()
                    int r4 = r4.getItemId()
                    r1 = 1
                    switch(r4) {
                        case 2131231787: goto L58;
                        case 2131231788: goto L12;
                        case 2131231789: goto L47;
                        case 2131231790: goto L24;
                        case 2131231791: goto L13;
                        default: goto L12;
                    }
                L12:
                    goto L67
                L13:
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    r2 = 3
                    com.nfcx.luxinvpower.view.main.MainActivity.access$102(r4, r2)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$200(r4, r0)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$302(r4, r2)
                    goto L67
                L24:
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    r2 = 0
                    com.nfcx.luxinvpower.view.main.MainActivity.access$102(r4, r2)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$200(r4, r0)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$302(r4, r2)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment r4 = com.nfcx.luxinvpower.view.main.MainActivity.access$400(r4)
                    r4.refreshFragmentParams()
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment r4 = com.nfcx.luxinvpower.view.main.MainActivity.access$400(r4)
                    r4.m360xd9585229()
                    goto L67
                L47:
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    r2 = 2
                    com.nfcx.luxinvpower.view.main.MainActivity.access$102(r4, r2)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$200(r4, r0)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$302(r4, r2)
                    goto L67
                L58:
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$102(r4, r1)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$200(r4, r0)
                    com.nfcx.luxinvpower.view.main.MainActivity r4 = com.nfcx.luxinvpower.view.main.MainActivity.this
                    com.nfcx.luxinvpower.view.main.MainActivity.access$302(r4, r1)
                L67:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.main.MainActivity.AnonymousClass1.onNavigationItemSelected(android.view.MenuItem):boolean");
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - MainActivity onResume...");
        if (this.lv1OverviewFragment != null && this.mFragment == 0 && System.currentTimeMillis() - this.lv1OverviewFragment.getLastRefreshDataTime() > 30000) {
            this.lv1OverviewFragment.refreshData();
        }
        this.useNewSettingPage = Boolean.valueOf(getSharedPreferences("userInfo", 0).getBoolean(Constants.useNewSettingPage, false));
    }

    public void switchRemoteSetFragment(int i) {
        if (this.deviceType != i) {
            this.deviceType = i;
            AbstractItemFragment abstractItemFragment = this.mFragment == 3 ? this.fragmentList[3] : null;
            if (getSharedPreferences("userInfo", 0).getBoolean(Constants.useNewSettingPage, false)) {
                this.fragmentList[3] = this.lv2RemoteSetFragment;
            } else if (i == 3) {
                this.fragmentList[3] = this.lv1OffGridRemoteSetFragment;
            } else if (i == 6) {
                this.fragmentList[3] = this.lv112KRemoteSetFragment;
            } else {
                this.fragmentList[3] = this.lv1RemoteSetFragment;
            }
            if (this.mFragment == 3) {
                switchFragment(this.fragmentManager.beginTransaction(), abstractItemFragment, this.fragmentList[3]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchFragment(FragmentTransaction fragmentTransaction) {
        AbstractItemFragment[] abstractItemFragmentArr = this.fragmentList;
        int i = this.mFragment;
        AbstractItemFragment abstractItemFragment = abstractItemFragmentArr[i];
        int i2 = this.toFragment;
        AbstractItemFragment abstractItemFragment2 = abstractItemFragmentArr[i2];
        if (i == i2) {
            return;
        }
        switchFragment(fragmentTransaction, abstractItemFragment, abstractItemFragment2);
    }

    private void switchFragment(FragmentTransaction fragmentTransaction, AbstractItemFragment abstractItemFragment, AbstractItemFragment abstractItemFragment2) {
        if (abstractItemFragment.isAdded) {
            if (abstractItemFragment2.isAdded) {
                fragmentTransaction.hide(abstractItemFragment).show(abstractItemFragment2).commit();
                return;
            } else {
                fragmentTransaction.add(R.id.nav_host_fragment_activity_main, abstractItemFragment2).hide(abstractItemFragment).show(abstractItemFragment2).commit();
                abstractItemFragment2.isAdded = true;
                return;
            }
        }
        if (abstractItemFragment2.isAdded) {
            fragmentTransaction.add(R.id.nav_host_fragment_activity_main, abstractItemFragment).hide(abstractItemFragment).show(abstractItemFragment2).commit();
        } else {
            fragmentTransaction.add(R.id.nav_host_fragment_activity_main, abstractItemFragment).add(R.id.nav_host_fragment_activity_main, abstractItemFragment2).hide(abstractItemFragment).show(abstractItemFragment2).commit();
            abstractItemFragment2.isAdded = true;
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        EditText timeDateEditText;
        EditText timeTimeEditText;
        if (i == 0) {
            EditText powerLineChartTimeEditText = this.lv1DataFragment.getPowerLineChartTimeEditText();
            if (powerLineChartTimeEditText != null) {
                return new DayDatePickerDialog(this, new DayOnDateSetListener(powerLineChartTimeEditText), powerLineChartTimeEditText.getText().toString());
            }
            return null;
        }
        if (i == 1) {
            EditText energyChartTimeEditText = this.lv1DataFragment.getEnergyChartTimeEditText();
            if (energyChartTimeEditText != null) {
                return new MonthDatePickerDialog(this, new MonthOnDateSetListener(energyChartTimeEditText), energyChartTimeEditText.getText().toString());
            }
            return null;
        }
        if (i == 2) {
            EditText energyChartTimeEditText2 = this.lv1DataFragment.getEnergyChartTimeEditText();
            if (energyChartTimeEditText2 != null) {
                return new YearDatePickerDialog(this, new YearOnDateSetListener(energyChartTimeEditText2), energyChartTimeEditText2.getText().toString());
            }
            return null;
        }
        if (i == 6) {
            int i2 = this.deviceType;
            if (i2 == 6) {
                timeDateEditText = this.lv112KRemoteSetFragment.getTimeDateEditText();
            } else if (i2 == 3) {
                timeDateEditText = this.lv1OffGridRemoteSetFragment.getTimeDateEditText();
            } else {
                timeDateEditText = this.lv1RemoteSetFragment.getTimeDateEditText();
            }
            if (timeDateEditText != null) {
                return new DayDatePickerDialog(this, new DayOnDateSetListener(timeDateEditText), timeDateEditText.getText().toString());
            }
            return null;
        }
        if (i != 7) {
            return null;
        }
        int i3 = this.deviceType;
        if (i3 == 6) {
            timeTimeEditText = this.lv112KRemoteSetFragment.getTimeTimeEditText();
        } else if (i3 == 3) {
            timeTimeEditText = this.lv1OffGridRemoteSetFragment.getTimeTimeEditText();
        } else {
            timeTimeEditText = this.lv1RemoteSetFragment.getTimeTimeEditText();
        }
        if (timeTimeEditText != null) {
            return new CustomTimePickerDialog(this, new TimePickerSetListener(timeTimeEditText), timeTimeEditText.getText().toString());
        }
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.lv2RemoteSetFragment.canGoBackInWebView()) {
            this.lv2RemoteSetFragment.goBackInWebView();
            return;
        }
        startActivity(new Intent(this, (Class<?>) (ROLE.VIEWER.equals(GlobalInfo.getInstance().getUserData().getRole()) ? PlantListActivity.class : PlantOverviewActivity.class)));
        finish();
        MainActivity mainActivity = instance;
        if (mainActivity != null) {
            mainActivity.finish();
        }
    }
}

package com.nfcx.luxinvpower.view.local;

import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.connect.LocalConnect;
import com.nfcx.luxinvpower.connect.LocalConnectManager;
import com.nfcx.luxinvpower.connect.ble.BleAction;
import com.nfcx.luxinvpower.connect.ble.BluetoothLocalConnect;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.custom.android.dialog.CustomTimePickerDialog;
import com.nfcx.luxinvpower.global.custom.android.dialog.DayDatePickerDialog;
import com.nfcx.luxinvpower.global.custom.android.dialog.DayOnDateSetListener;
import com.nfcx.luxinvpower.global.custom.android.dialog.TimePickerSetListener;
import com.nfcx.luxinvpower.global.custom.navigation.BottomNavigationViewHelper;
import com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment;
import com.nfcx.luxinvpower.view.local.fragment.LocalOffGridSetFragment;
import com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment;
import com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class LocalActivity extends FragmentActivity implements BleAction {
    public static LocalActivity instance;
    private LocalViewPagerAdapter adapter;
    private BottomNavigationView bottomNavigationView;
    private int deviceType;
    private Local12KSetFragment local12KSetFragment;
    private LocalConnect localConnect;
    private String localConnectType;
    private LocalOffGridSetFragment localOffGridSetFragment;
    private LocalOverviewFragment localOverviewFragment;
    private LocalSetFragment localSetFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.local.LocalActivity.1
        @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_overview /* 2131231790 */:
                    LocalActivity.this.viewPager2.setCurrentItem(0);
                    return true;
                case R.id.navigation_set /* 2131231791 */:
                    LocalActivity.this.viewPager2.setCurrentItem(1);
                    return true;
                default:
                    return false;
            }
        }
    };
    private MenuItem menuItem;
    private ViewPager2 viewPager2;

    @Override // com.nfcx.luxinvpower.connect.ble.BleAction
    public void bleConnectClosed() {
    }

    @Override // com.nfcx.luxinvpower.connect.ble.BleAction
    public void bleConnected() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_local);
        this.localConnectType = getIntent().getStringExtra(Constants.LOCAL_CONNECT_TYPE);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        getWindow().addFlags(128);
        setRequestedOrientation(1);
        if (Constants.LOCAL_CONNECT_TYPE_BLUETOOTH.equals(this.localConnectType)) {
            LocalConnectManager.updateBluetoothLocalConnect(new BluetoothLocalConnect(this, (BluetoothDevice) getIntent().getParcelableExtra(Constants.KEY_BLE_DEVICE)));
            this.localConnect = LocalConnectManager.getLocalConnect(this.localConnectType);
        } else {
            this.localConnect = LocalConnectManager.getLocalConnect(this.localConnectType);
            WifiInfo connectionInfo = ((WifiManager) getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            String ssid = connectionInfo != null ? connectionInfo.getSSID() : null;
            if (ssid != null && ssid.length() == 12) {
                this.localConnect.setDatalogSn(ssid.substring(1, 11));
            } else {
                this.localConnect.setDatalogSn(Constants.DEFAULT_DATALOG_SN);
            }
        }
        this.viewPager2 = (ViewPager2) findViewById(R.id.viewpager);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        this.bottomNavigationView = bottomNavigationView;
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        this.bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        this.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.nfcx.luxinvpower.view.local.LocalActivity.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                if (LocalActivity.this.menuItem != null) {
                    LocalActivity.this.menuItem.setChecked(false);
                } else {
                    LocalActivity.this.bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                LocalActivity localActivity = LocalActivity.this;
                localActivity.menuItem = localActivity.bottomNavigationView.getMenu().getItem(i);
                LocalActivity.this.menuItem.setChecked(true);
            }
        });
        setupViewPager(this.viewPager2);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        System.out.println("LuxPowerLocalActivity onDestroy...");
        this.localConnect.close();
        this.localConnect = null;
    }

    private void setupViewPager(ViewPager2 viewPager2) {
        ArrayList arrayList = new ArrayList();
        LocalConnect localConnect = LocalConnectManager.getLocalConnect(this.localConnectType);
        LocalOverviewFragment localOverviewFragment = new LocalOverviewFragment(localConnect);
        this.localOverviewFragment = localOverviewFragment;
        arrayList.add(localOverviewFragment);
        LocalSetFragment localSetFragment = new LocalSetFragment(localConnect);
        this.localSetFragment = localSetFragment;
        arrayList.add(localSetFragment);
        this.localOffGridSetFragment = new LocalOffGridSetFragment(localConnect);
        this.local12KSetFragment = new Local12KSetFragment(localConnect);
        LocalViewPagerAdapter localViewPagerAdapter = new LocalViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), arrayList, this.localSetFragment, this.localOffGridSetFragment, this.local12KSetFragment);
        this.adapter = localViewPagerAdapter;
        viewPager2.setAdapter(localViewPagerAdapter);
    }

    public void switchLocalSetFragment(int i) {
        this.deviceType = i;
        this.adapter.switchLocalSetFragment(i);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        EditText timeDateEditText;
        EditText timeTimeEditText;
        if (i == 0) {
            int i2 = this.deviceType;
            if (i2 == 6) {
                timeDateEditText = this.local12KSetFragment.getTimeDateEditText();
            } else if (i2 == 3) {
                timeDateEditText = this.localOffGridSetFragment.getTimeDateEditText();
            } else {
                timeDateEditText = this.localSetFragment.getTimeDateEditText();
            }
            if (timeDateEditText != null) {
                return new DayDatePickerDialog(this, new DayOnDateSetListener(timeDateEditText), timeDateEditText.getText().toString());
            }
            return null;
        }
        if (i != 1) {
            return null;
        }
        int i3 = this.deviceType;
        if (i3 == 6) {
            timeTimeEditText = this.local12KSetFragment.getTimeTimeEditText();
        } else if (i3 == 3) {
            timeTimeEditText = this.localOffGridSetFragment.getTimeTimeEditText();
        } else {
            timeTimeEditText = this.localSetFragment.getTimeTimeEditText();
        }
        if (timeTimeEditText != null) {
            return new CustomTimePickerDialog(this, new TimePickerSetListener(timeTimeEditText), timeTimeEditText.getText().toString());
        }
        return null;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            GlobalInfo.getInstance().getUserData();
            startActivity(new Intent(this, (Class<?>) LoginActivity.class));
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }
}

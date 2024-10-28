package com.nfcx.luxinvpower.view.logout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;
import com.nfcx.luxinvpower.view.userCenter.EditUserActivity;
import com.nfcx.luxinvpower.view.userCenter.ModifyPasswordActivity;
import com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity;
import com.nfcx.luxinvpower.view.userCenter.UserCenterActivity;

/* loaded from: classes2.dex */
public class LogoutActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_logout);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        finish();
        return true;
    }

    public void exitButtonYes(View view) {
        doLogout(this);
    }

    public static void doLogout(Activity activity) {
        Intent intent = new Intent(activity, (Class<?>) LoginActivity.class);
        intent.putExtra("fromLogout", true);
        activity.startActivity(intent);
        activity.finish();
        if (PlantOverviewActivity.instance != null) {
            PlantOverviewActivity.instance.finish();
        }
        if (PlantListActivity.instance != null) {
            PlantListActivity.instance.finish();
        }
        if (MainActivity.instance != null) {
            MainActivity.instance.finish();
        }
        if (UserCenterActivity.instance != null) {
            UserCenterActivity.instance.finish();
        }
        if (NewUserCenterActivity.instance != null) {
            NewUserCenterActivity.instance.finish();
        }
        if (EditUserActivity.instance != null) {
            EditUserActivity.instance.finish();
        }
        if (ModifyPasswordActivity.instance != null) {
            ModifyPasswordActivity.instance.finish();
        }
    }

    public void exitButtonNo(View view) {
        finish();
    }
}

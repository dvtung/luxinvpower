package com.nfcx.luxinvpower.view.userCenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EditUserActivity extends Activity {
    public static EditUserActivity instance;
    private EditText addressEditText;
    private Spinner continentSpinner;
    private Spinner countrySpinner;
    private List<Property> countrys;
    private Button editUserButton;
    private EditText emailEditText;
    private boolean firstTimeLoadCountryBySelect;
    private boolean firstTimeLoadRegionBySelect;
    private EditText realNameEditText;
    private Spinner regionSpinner;
    private List<Property> regions;
    private EditText telNumberEditText;
    private Spinner timezoneSpinner;
    private EditText usernameEditText;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_user);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.EditUserActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditUserActivity.instance.finish();
            }
        });
        UserData userData = GlobalInfo.getInstance().getUserData();
        if (!PLATFORM.LUX_POWER.equals(userData.getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        this.firstTimeLoadRegionBySelect = true;
        this.firstTimeLoadCountryBySelect = true;
        EditText editText = (EditText) findViewById(R.id.usernameEditText);
        this.usernameEditText = editText;
        editText.setText(userData.getUsername());
        EditText editText2 = (EditText) findViewById(R.id.realNameEditText);
        this.realNameEditText = editText2;
        editText2.setText(userData.getRealName());
        EditText editText3 = (EditText) findViewById(R.id.emailEditText);
        this.emailEditText = editText3;
        editText3.setText(userData.getEmail());
        this.continentSpinner = (Spinner) findViewById(R.id.register_continentSpinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getContinents(this));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.continentSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        System.out.println("LuxPowerCurrentContinentIndex = " + userData.getCurrentContinentIndex());
        this.continentSpinner.setSelection(userData.getCurrentContinentIndex());
        this.continentSpinner.invalidate();
        this.regions = userData.getRegions();
        this.regionSpinner = (Spinner) findViewById(R.id.register_regionSpinner);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(instance, android.R.layout.simple_spinner_item, this.regions);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instance.regionSpinner.setAdapter((SpinnerAdapter) arrayAdapter2);
        System.out.println("LuxPowerCurrentRegionIndex = " + userData.getCurrentRegionIndex());
        this.regionSpinner.setSelection(userData.getCurrentRegionIndex());
        this.regionSpinner.invalidate();
        this.continentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.userCenter.EditUserActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (!EditUserActivity.instance.firstTimeLoadRegionBySelect) {
                    new ReloadRegionSpinnerDataTask().execute(((Property) EditUserActivity.instance.continentSpinner.getSelectedItem()).getName());
                } else {
                    EditUserActivity.instance.firstTimeLoadRegionBySelect = false;
                }
            }
        });
        this.countrys = userData.getCountrys();
        this.countrySpinner = (Spinner) findViewById(R.id.register_countrySpinner);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(instance, android.R.layout.simple_spinner_item, this.countrys);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instance.countrySpinner.setAdapter((SpinnerAdapter) arrayAdapter3);
        System.out.println("LuxPowerCurrentCountryIndex = " + userData.getCurrentCountryIndex());
        this.countrySpinner.setSelection(userData.getCurrentCountryIndex());
        this.countrySpinner.invalidate();
        this.regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.userCenter.EditUserActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (!EditUserActivity.instance.firstTimeLoadCountryBySelect) {
                    new ReloadCountrySpinnerDataTask().execute(((Property) EditUserActivity.instance.regionSpinner.getSelectedItem()).getName());
                } else {
                    EditUserActivity.instance.firstTimeLoadCountryBySelect = false;
                }
            }
        });
        this.timezoneSpinner = (Spinner) findViewById(R.id.register_timezoneSpinner);
        ArrayAdapter arrayAdapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getTimezones());
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.timezoneSpinner.setAdapter((SpinnerAdapter) arrayAdapter4);
        String timezone = userData.getTimezone();
        List<Property> timezones = GlobalInfo.getInstance().getTimezones();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= timezones.size()) {
                break;
            }
            if (timezones.get(i2).getName().equals(timezone)) {
                i = i2;
                break;
            }
            i2++;
        }
        this.timezoneSpinner.setSelection(i);
        this.timezoneSpinner.invalidate();
        EditText editText4 = (EditText) findViewById(R.id.telNumberEditText);
        this.telNumberEditText = editText4;
        editText4.setText(userData.getTelNumber());
        EditText editText5 = (EditText) findViewById(R.id.addressEditText);
        this.addressEditText = editText5;
        editText5.setText(userData.getAddress());
        this.editUserButton = (Button) findViewById(R.id.editUserButton);
    }

    public void clickUpdateButton(View view) {
        UserData userData = GlobalInfo.getInstance().getUserData();
        String obj = this.usernameEditText.getText().toString();
        if (Tool.isEmpty(obj)) {
            Toast.makeText(this, R.string.page_register_error_account_empty, 1).show();
            this.usernameEditText.requestFocus();
            return;
        }
        if (obj.length() < 4) {
            Toast.makeText(this, R.string.page_register_error_account_minLength, 1).show();
            this.usernameEditText.requestFocus();
        } else if (Tool.containInvalidCharacter(obj)) {
            Tool.alert(this, R.string.phrase_username_error_char_invalid);
            this.usernameEditText.requestFocus();
        } else if (obj.equals(userData.getUsername())) {
            execEditUser();
        } else {
            new CheckAccountExistTask().execute(obj);
        }
    }

    private void execEditUser() {
        execEditUser(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execEditUser(String str) {
        UserData userData = GlobalInfo.getInstance().getUserData();
        String obj = this.emailEditText.getText().toString();
        if (Tool.isEmpty(obj)) {
            Tool.alert(this, R.string.page_register_error_email_empty);
            this.emailEditText.requestFocus();
            return;
        }
        if (!Tool.isEmail(obj)) {
            Tool.alert(this, R.string.page_register_error_email_format);
            this.emailEditText.requestFocus();
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("userId", String.valueOf(userData.getUserId()));
        if (!Tool.isEmpty(str)) {
            hashMap.put("account", str);
        }
        hashMap.put("email", obj);
        Property property = (Property) this.countrySpinner.getSelectedItem();
        if (property == null) {
            return;
        }
        hashMap.put("country", property.getName());
        Property property2 = (Property) this.timezoneSpinner.getSelectedItem();
        if (property2 == null) {
            return;
        }
        hashMap.put("timezone", property2.getName());
        hashMap.put("language", userData.getLanguage());
        hashMap.put("telNumber", this.telNumberEditText.getText().toString());
        hashMap.put("address", this.addressEditText.getText().toString());
        hashMap.put("realName", this.realNameEditText.getText().toString());
        this.editUserButton.setEnabled(false);
        new EditUserTask().execute(hashMap);
    }

    /* loaded from: classes2.dex */
    private static class CheckAccountExistTask extends AsyncTask<String, Object, String> {
        private String account;

        private CheckAccountExistTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String[] strArr) {
            try {
                this.account = strArr[0];
                HashMap hashMap = new HashMap();
                hashMap.put("account", this.account);
                return HttpTool.postString(GlobalInfo.getInstance().getBaseUrl() + "web/register/isAccountExist", hashMap, (Map<String, String>) null);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute((CheckAccountExistTask) str);
            try {
                if (str != null) {
                    if (!"true".equals(str)) {
                        EditUserActivity.instance.execEditUser(this.account);
                    } else {
                        Tool.alert(EditUserActivity.instance, R.string.page_register_error_account_exists);
                    }
                } else {
                    Tool.alert(EditUserActivity.instance, R.string.phrase_toast_network_error);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class EditUserTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private Map<String, String> paramIns;

        private EditUserTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                this.paramIns = mapArr[0];
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/system/user/editJson", this.paramIns);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((EditUserTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.getBoolean("success")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(EditUserActivity.instance);
                            builder.setTitle(R.string.phrase_message).setIcon(android.R.drawable.ic_dialog_info).setMessage(R.string.page_edit_user_success).setNegativeButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.EditUserActivity.EditUserTask.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    UserData userData = GlobalInfo.getInstance().getUserData();
                                    String str = (String) EditUserTask.this.paramIns.get("account");
                                    if (!Tool.isEmpty(str)) {
                                        userData.setUsername(str);
                                    }
                                    userData.setEmail((String) EditUserTask.this.paramIns.get("email"));
                                    userData.setCurrentContinentIndex(EditUserActivity.instance.continentSpinner.getSelectedItemPosition());
                                    userData.setCurrentRegionIndex(EditUserActivity.instance.regionSpinner.getSelectedItemPosition());
                                    userData.setRegions(EditUserActivity.instance.regions);
                                    userData.setCurrentCountryIndex(EditUserActivity.instance.countrySpinner.getSelectedItemPosition());
                                    userData.setCountrys(EditUserActivity.instance.countrys);
                                    if (EditUserActivity.instance.countrys.size() > userData.getCurrentCountryIndex()) {
                                        userData.setCountryText(((Property) EditUserActivity.instance.countrys.get(userData.getCurrentCountryIndex())).getValue());
                                    }
                                    String str2 = (String) EditUserTask.this.paramIns.get("timezone");
                                    userData.setTimezone(str2);
                                    userData.setTimezoneText(Tool.getTimezoneText(str2));
                                    userData.setTelNumber((String) EditUserTask.this.paramIns.get("telNumber"));
                                    userData.setAddress((String) EditUserTask.this.paramIns.get("address"));
                                    userData.setRealName((String) EditUserTask.this.paramIns.get("realName"));
                                    EditUserActivity.instance.finish();
                                }
                            });
                            builder.show();
                        } else {
                            Tool.alert(EditUserActivity.instance, R.string.phrase_toast_unknown_error);
                        }
                    } else {
                        Tool.alert(EditUserActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (EditUserActivity.instance == null || EditUserActivity.instance.isDestroyed()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (EditUserActivity.instance == null || EditUserActivity.instance.isDestroyed()) {
                        return;
                    }
                }
                EditUserActivity.instance.editUserButton.setEnabled(true);
            } catch (Throwable th) {
                if (EditUserActivity.instance != null && !EditUserActivity.instance.isDestroyed()) {
                    EditUserActivity.instance.editUserButton.setEnabled(true);
                }
                throw th;
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class ReloadRegionSpinnerDataTask extends AsyncTask<String, Object, JSONArray> {
        private ReloadRegionSpinnerDataTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONArray doInBackground(String... strArr) {
            HashMap hashMap = new HashMap();
            hashMap.put("continent", String.valueOf(strArr[0]));
            hashMap.put("language", GlobalInfo.getInstance().getLanguage());
            try {
                return HttpTool.postJsonArray(GlobalInfo.getInstance().getBaseUrl() + "locale/region", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONArray jSONArray) {
            super.onPostExecute((ReloadRegionSpinnerDataTask) jSONArray);
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        arrayList.add(new Property(jSONObject.getString("value"), jSONObject.getString("text")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (EditUserActivity.instance == null || EditUserActivity.instance.isDestroyed()) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(EditUserActivity.instance, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            EditUserActivity.instance.regionSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            EditUserActivity.instance.regions = arrayList;
        }
    }

    /* loaded from: classes2.dex */
    private static class ReloadCountrySpinnerDataTask extends AsyncTask<String, Object, JSONArray> {
        private ReloadCountrySpinnerDataTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONArray doInBackground(String... strArr) {
            HashMap hashMap = new HashMap();
            hashMap.put("region", String.valueOf(strArr[0]));
            hashMap.put("language", GlobalInfo.getInstance().getLanguage());
            try {
                return HttpTool.postJsonArray(GlobalInfo.getInstance().getBaseUrl() + "locale/country", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONArray jSONArray) {
            super.onPostExecute((ReloadCountrySpinnerDataTask) jSONArray);
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        arrayList.add(new Property(jSONObject.getString("value"), jSONObject.getString("text")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (EditUserActivity.instance == null || EditUserActivity.instance.isDestroyed()) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(EditUserActivity.instance, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            EditUserActivity.instance.countrySpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            EditUserActivity.instance.countrys = arrayList;
        }
    }
}

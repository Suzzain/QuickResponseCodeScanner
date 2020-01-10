package com.example.quickresponsecodescanner;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

public class AboutDialog extends Dialog {
    private static final String TAG = AboutDialog.class.getName();

    private Context mContext = null;

    public AboutDialog(Context context) {
        super(context);

        mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.about);
        loadTheme();

        TextView tv = (TextView) findViewById(R.id.info_version);
        String packageName = getContext().getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = getContext().getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            String appInfo = "SecScanQR";
            String versionInfo = "Version " +
                    packageInfo.versionName + " (Build " +
                    Integer.toString(packageInfo.versionCode) + ")";
            tv.setText(appInfo + "\n" + versionInfo);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Call to getPackageInfo() failed! => ", e);
        }

    }

    private void loadTheme(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        String history_setting = prefs.getString("pref_day_night_mode", "");
        if(history_setting.equals("1")){
            mContext.setTheme(R.style.darktheme);
        } else {
        }
    }
}

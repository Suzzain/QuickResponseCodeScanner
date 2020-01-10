package com.example.quickresponsecodescanner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ThirdPartyActivity extends AppCompatActivity {
final Activity activity = this;
private String qrcode = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party);
        zxingScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Intent intent = getIntent();

        if (result != null) {
            if (result.getContents() == null) {
                setResult(RESULT_CANCELED, intent);
                finish();
                return;
            } else {
                qrcode = result.getContents();
                intent.putExtra("SCAN_RESULT", qrcode);
                setResult(RESULT_OK, intent);
                finish();
                return;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void zxingScan() {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt((String) getResources().getText(R.string.xzing_label));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String camera_setting = prefs.getString("pref_camera", "");
        if(camera_setting.equals("1")){
            integrator.setCameraId(1);
        } else {
            integrator.setCameraId(0);
        }

        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        try {
            integrator.initiateScan();
        } catch (ArithmeticException e){

        }
    }
}

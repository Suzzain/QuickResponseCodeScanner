package utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickresponsecodescanner.R;

public class ButtonHandler {
    public static void resetScreenInformation(TextView tvInformation, TextView tvFormat, TextView mLabelInformation, TextView mLabelFormat, String qrcode, String format, BottomNavigationView buttonContainer){
        tvInformation.setText(R.string.default_text);
        tvFormat.setText("");
        tvFormat.setVisibility(View.GONE);
        mLabelInformation.setVisibility(View.GONE);
        mLabelFormat.setVisibility(View.GONE);
        qrcode = "";
        format = "";
        buttonContainer.setVisibility(View.INVISIBLE);
    }
    public static void copyToClipboard(TextView tv, String qrcode, Activity activity){
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(tv.getText(), qrcode);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(activity, activity.getResources().getText(R.string.notice_clipoard), Toast.LENGTH_LONG).show();
    }

    public static void shareTo(String qrcode, Activity activity){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, qrcode);
        sendIntent.setType("text/plain");
        activity.startActivity(Intent.createChooser(sendIntent, activity.getResources().getText(R.string.send_to)));
    }

    public static void openInWeb(String qrcode, Activity activity){
        if(qrcode.equals("")){
            Toast.makeText(activity.getApplicationContext(), activity.getResources().getText(R.string.error_scan_first), Toast.LENGTH_SHORT).show();
        } else {
            try {
                Uri uri = Uri.parse(qrcode);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            } catch (Exception e){
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                String search_engine = prefs.getString("pref_search_engine", "");
                String tempUrl;
                switch (search_engine) {
                    case "1":
                        tempUrl = "https://www.bing.com/search?q=";
                        break;
                    case "2":
                        tempUrl = "https://duckduckgo.com/?q=";
                        break;
                    case "3":
                        tempUrl = "http://www.google.com/#q=";
                        break;
                    case "4":
                        tempUrl = "https://www.qwant.com/?q=";
                        break;
                    case "5":
                        tempUrl = "https://lite.qwant.com/?q=";
                        break;
                    case "6":
                        tempUrl = "https://www.startpage.com/do/dsearch?query=";
                        break;
                    case "7":
                        tempUrl = "https://search.yahoo.com/search?p=";
                        break;
                    case "8":
                        tempUrl = "https://www.yandex.ru/search/?text=";
                        break;
                    default:
                        tempUrl = "http://www.google.com/#q=";
                        break;
                }
                Uri uri = Uri.parse(tempUrl + qrcode);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        }
    }

    public static void openInWeb(String qrcode, String format, Activity activity){
        if(qrcode.equals("")){
            Toast.makeText(activity.getApplicationContext(), activity.getResources().getText(R.string.error_scan_first), Toast.LENGTH_SHORT).show();
        } else {
            try {
                Uri uri = Uri.parse(qrcode);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            } catch (Exception e){
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                String search_engine = prefs.getString("pref_search_engine", "");
                String barcode_engine = prefs.getString("pref_barcode_search_engine", "");
                String tempUrl;
                if(barcode_engine.equals("0")){
                    switch (search_engine) {
                        case "1":
                            tempUrl = "https://www.bing.com/search?q=";
                            break;
                        case "2":
                            tempUrl = "https://duckduckgo.com/?q=";
                            break;
                        case "3":
                            tempUrl = "http://www.google.com/#q=";
                            break;
                        case "4":
                            tempUrl = "https://www.qwant.com/?q=";
                            break;
                        case "5":
                            tempUrl = "https://lite.qwant.com/?q=";
                            break;
                        case "6":
                            tempUrl = "https://www.startpage.com/do/dsearch?query=";
                            break;
                        case "7":
                            tempUrl = "https://search.yahoo.com/search?p=";
                            break;
                        case "8":
                            tempUrl = "https://www.yandex.ru/search/?text=";
                            break;
                        default:
                            tempUrl = "http://www.google.com/#q=";
                            break;
                    }
                } else {
                    if (format.equals("QR_CODE") || format.equals("AZTEC")) {
                        switch (search_engine) {
                            case "1":
                                tempUrl = "https://www.bing.com/search?q=";
                                break;
                            case "2":
                                tempUrl = "https://duckduckgo.com/?q=";
                                break;
                            case "3":
                                tempUrl = "http://www.google.com/#q=";
                                break;
                            case "4":
                                tempUrl = "https://www.qwant.com/?q=";
                                break;
                            case "5":
                                tempUrl = "https://lite.qwant.com/?q=";
                                break;
                            case "6":
                                tempUrl = "https://www.startpage.com/do/dsearch?query=";
                                break;
                            case "7":
                                tempUrl = "https://search.yahoo.com/search?p=";
                                break;
                            case "8":
                                tempUrl = "https://www.yandex.ru/search/?text=";
                                break;
                            default:
                                tempUrl = "http://www.google.com/#q=";
                                break;
                        }
                    } else {
                        switch (barcode_engine) {
                            case "1":
                                tempUrl = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=";
                                break;
                            case "2":
                                tempUrl = "https://www.codecheck.info/product.search?q=";
                                break;
                            default:
                                tempUrl = "http://www.google.com/#q=";
                                break;
                        }
                    }
                }

                Uri uri = Uri.parse(tempUrl + qrcode);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        }
    }

    public static void createContact(String qrcode, Activity activity) {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        String information[] = qrcode.split("\\r?\\n");
        String notes = "";

        for(int i = 0; i < information.length; i++){
            if (information[i].contains("N:")){
                String[] separeted = information[i].split(":");
                String name = separeted[1].replace(";", " ");
                intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
            } else if(information[i].contains("ORG")){
                String[] separeted = information[i].split(":");
                intent.putExtra(ContactsContract.Intents.Insert.COMPANY, separeted[1]);
            } else if(information[i].contains("URL")){
                String[] separeted = information[i].split(":");
                notes = notes + "\n" + separeted[1];
            } else if(information[i].contains("EMAIL")){
                String[] separeted = information[i].split(":");
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, separeted[1]);
            } else if(information[i].contains("TEL")){
                String[] separeted = information[i].split(":");

                if(separeted[0].contains("CELL")){
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                    intent.putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, separeted[1]);
                } else if(separeted[0].contains("WORK")){
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                    intent.putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE, separeted[1]);
                } else if(separeted[0].contains("HOME")){
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, separeted[1]);
                } else if(separeted[0].contains("VOICE")){
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_OTHER);
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, separeted[1]);
                }
            } else if(information[i].contains("ADR")){
                String[] separeted = information[i].split(":");
                String[] adr = separeted[1].split(";");
                notes = notes + "\n" + adr[2] + "\n" + adr[3] + "\n" + adr[4] + "\n" + adr[5] + "\n" + adr[6];
            }
            intent.putExtra(ContactsContract.Intents.Insert.NOTES, notes);
        }
        activity.startActivity(intent);
    }
}

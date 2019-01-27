
package com.xiaomai.sms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.IllegalViewOperationException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsHandlerModule extends ReactContextBaseJavaModule {

    final String SMS_URI_ALL = "content://sms/";
    final String SMS_URI_INBOX = "content://sms/inbox";
    final String SMS_URI_SEND = "content://sms/sent";
    final String SMS_URI_DRAFT = "content://sms/draft";

    public SmsHandlerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "SmsHandler";
    }

    private WritableMap transform(String[] keys, Cursor cs) {
        WritableMap sms = Arguments.createMap();
        for (int i = 0, n = keys.length; i < n; i++) {
            String key = keys[i];
            int index = cs.getColumnIndex(key);
            if (key == "date" || key == "date_sent") {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date d = new Date(Long.parseLong(cs.getString(index)));
                sms.putString(key, dateFormat.format(d));
            } else {
                String value = cs.getString(index);
                sms.putString(key, value);
            }
        }
        return sms;
    }

    @ReactMethod
    public void getSmsInPhone(Callback callback) {
        // Promise promise
        // 短信列表
        WritableArray smss = Arguments.createArray();
        try {
            ContentResolver cr = super.getCurrentActivity().getContentResolver();
            String[] projection = new String[] { "_id", "address", "person", "date_sent", "type", "read", "status",
                    "service_center", "locked", "body", "date" };
            Uri uri = Uri.parse(SMS_URI_ALL);
            Cursor cur = cr.query(uri, projection, null, null, "date desc");
            if (cur.moveToFirst()) {
                do {
                    WritableMap sms = this.transform(projection, cur);
                    smss.pushMap(sms);
                } while (cur.moveToNext());
            }
        } catch (SQLiteException ex) {
            Log.e("SQLiteException", ex.getMessage().toString());
        }
        WritableMap sms = Arguments.createMap();
        callback.invoke(smss);
    }

}

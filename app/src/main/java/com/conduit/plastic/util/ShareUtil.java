/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.conduit.plastic.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.conduit.plastic.permission.OnPermissionCallback;
import com.conduit.plastic.permission.PermissionManager;

import java.util.List;

public class ShareUtil {

    /**
     * 分享
     */
    public static void share(Context context, String content){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享"));
    }

    /**
     * 反馈
     */
    public static void feedback(Context context, String email) {
        Uri uri = Uri.parse("mailto:" + email);
        final Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (infos == null || infos.size() <= 0){
            Toast.makeText(context,"分享", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(intent);
    }
    /**
     * 反馈
     */
    public static void callPhone(final Activity context, final String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        PermissionManager.instance().with(context).request(new OnPermissionCallback() {
            @Override
            public void onRequestAllow(String permissionName) {

                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onRequestRefuse(String permissionName) {


            }

            @Override
            public void onRequestNoAsk(String permissionName) {

            }
        }, Manifest.permission.CALL_PHONE);

    }
    public static void hideInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

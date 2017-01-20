package lifetime.apper.klc.lifetime.Service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.RealmModule;
import lifetime.apper.klc.lifetime.Auxiliary.paramStatic;
import lifetime.apper.klc.lifetime.Auxiliary.userPerferences;
import lifetime.apper.klc.lifetime.NowMainActivity;
import lifetime.apper.klc.lifetime.signUpUser;

/**
 * Created by c1103304 on 2017/1/12.
 * 在背景持續計算時間，並將剩餘時間儲存在 OUTPUT_REMAINDER_NUM
 *
 */

public class MyService extends Service{
    private Handler handler = new Handler();
    public static boolean nowState=false;
    public static long[] OUTPUT_REMAINDER_NUM;
    public static List<userPerferences> item;
    long[] maxnum;
    public static Realm realm;
    String[] name;
    int counts;
    @RealmModule(classes = {userPerferences.class})
    public static class Module {
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MYLOG","Service ON");
        // 配置資料庫
        realmSetting();
        // 取得資料 / 包含更新
        renew();

        handler.postDelayed(showTime, 100);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Runnable showTime = new Runnable() {
        public void run() {
            //設定service為已啟動
            nowState=true;

            Bundle message = new Bundle();

            long[] remaindernum = remainder();
            message.putLongArray("Key", remaindernum);
            Intent intent = new Intent("remaindertime");
            intent.putExtras(message);
            sendBroadcast(intent);
            OUTPUT_REMAINDER_NUM = remaindernum;
            Log.d("MYLOG", "remainder : " + remaindernum[0]);
//              Toast.makeText(getApplicationContext(), "更新資訊", Toast.LENGTH_LONG).show();
            handler.postDelayed(this, 1000);
        }
    };

    // 計算餘數
    @TargetApi(Build.VERSION_CODES.N)
    private long[] remainder(){
        Calendar mCalendar = Calendar.getInstance(Locale.getDefault());
        int n=0;
        long[] longtmp = new long[counts+1];
        for(long tmp:maxnum){
            long tmp1 = mCalendar.getTimeInMillis()/1000;
            Log.d("MYLOG", "tmp - now : " + tmp+" - "+tmp1);
            longtmp[n] = tmp - tmp1;
            Log.d("MYLOG", "tmp - now : " + longtmp[n]);
            ++n;
        }
        return longtmp;
    }


    private void realmSetting(){
        // Realm 基本屬性配置
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("database_name.realm")
                .setModules(new MyService.Module())
                .deleteRealmIfMigrationNeeded()
                .build();
        // 實例Realm，並設置其基本屬性config
        realm = Realm.getInstance(config);
        // 啟動Realm 資料庫
        realm.beginTransaction();

        RealmQuery<userPerferences> query = realm.where(userPerferences.class);
        RealmResults<userPerferences> result = query.findAll();
        item = new ArrayList<>();
        for (userPerferences d : result) {
            item.add(d);
        }

        //insertdata();
    }


    public void renew(){
        counts = item.size();
        maxnum = new long[counts+1];
        name = new String[counts+1];
        for(int i=0; i<counts;i++){
            maxnum[i] = item.get(i).getMaxSec();
            Log.d("MYLOG","Service ON"+maxnum[i]);
            name[i] = item.get(i).getName();
        }
    }
}

package lifetime.apper.klc.lifetime.Service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.Calendar;
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
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.RealmModule;
import lifetime.apper.klc.lifetime.Auxiliary.paramStatic;
import lifetime.apper.klc.lifetime.Auxiliary.staticParam;
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
    public static ArrayList<staticParam> tmp;
    public static Realm realm;
    public static int counts;
    RealmConfiguration config;
    @RealmModule(classes = {userPerferences.class})
    public static class Module {
    }

    @Override
    public void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MYLOG","Service ON");
        // 配置資料庫
        realmSetting();


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
            message.putBoolean("Key", true);
            Intent intent = new Intent("remaindertime");
            intent.putExtras(message);
            sendBroadcast(intent);

            scular();
//              Toast.makeText(getApplicationContext(), "更新資訊", Toast.LENGTH_LONG).show();
            handler.postDelayed(this, 1000);
        }
    };

    private void scular() {
        for (int i=0;i<counts;i++) {
            long max = tmp.get(i).getMax();
            tmp.get(i).setNow(paramStatic.getNowTimeSec(max));
            tmp.get(i).getPercent();
        }
    }


    public void realmSetting(){
        // Realm 基本屬性配置
        config = new RealmConfiguration.Builder(this)
                .name("database_name.realm")
                .setModules(new MyService.Module())
                .deleteRealmIfMigrationNeeded()
                .build();
        // 實例Realm，並設置其基本屬性config
        realm = Realm.getInstance(config);

        // 取得資料 / 包含更新
        renew();

    }


    public static void renew(){
        // 啟動Realm 資料庫
        realm.beginTransaction();
        RealmQuery<userPerferences> query = realm.where(userPerferences.class);
        RealmResults<userPerferences> result = query.findAll();
        tmp = new ArrayList<>();
        //if(result.size()>0) {
        for (int i = 0; i < result.size(); i++) {
            staticParam sp = new staticParam();
            long max = result.get(i).getMaxSec();
            sp.setName(result.get(i).getName());
            sp.setMax(max);
            sp.setNow(paramStatic.getNowTimeSec(max));
            sp.setPercent(paramStatic.long2int(result.get(i).getBornSec(),max));
            tmp.add(sp);
        }
        counts = tmp.size();
    }
}

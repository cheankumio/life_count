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

import java.util.Locale;

import lifetime.apper.klc.lifetime.Auxiliary.paramStatic;

/**
 * Created by c1103304 on 2017/1/12.
 * 在背景持續計算時間，並將剩餘時間儲存在 OUTPUT_REMAINDER_NUM
 *
 */

public class MyService extends Service{
    private Handler handler = new Handler();
    public static boolean nowState=false;
    public static long OUTPUT_REMAINDER_NUM;
    SharedPreferences sp;
    long maxnum = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MYLOG","Service ON");
        sp = getSharedPreferences("DATA",0);
        maxnum = sp.getLong("unMAXLife",0);
        paramStatic.uneditMAX = maxnum;
        handler.postDelayed(showTime, 1000);
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
            if(paramStatic.uneditMAX<2) {
                SharedPreferences sp = getSharedPreferences("DATA", 0);
                maxnum = sp.getLong("unMAXLife", 0);
                Log.d("MYLOG", "uneditMAX<2 : " + maxnum);
            }else{
                if(maxnum!=paramStatic.uneditMAX) maxnum = paramStatic.uneditMAX;
                Bundle message = new Bundle();
                long remaindernum = remainder();
                message.putLong("Key", remaindernum);
                Intent intent = new Intent("remaindertime");
                intent.putExtras(message);
                sendBroadcast(intent);
                paramStatic.lifesecNOW = remaindernum;
                OUTPUT_REMAINDER_NUM = remaindernum;
                Log.d("MYLOG", "uneditMAX<2 : " + remaindernum);
//              Toast.makeText(getApplicationContext(), "更新資訊", Toast.LENGTH_LONG).show();
            }
            handler.postDelayed(this, 1000);
        }
    };

    @TargetApi(Build.VERSION_CODES.N)
    private long remainder(){
        Calendar mCalendar = Calendar.getInstance(Locale.getDefault());
        return maxnum - mCalendar.getTimeInMillis();
    }
}

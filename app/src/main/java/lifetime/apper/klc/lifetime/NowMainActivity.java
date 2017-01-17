package lifetime.apper.klc.lifetime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;



/**
 * Created by c1103304 on 2017/1/12.
 */

public class NowMainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView remainsec,remainmin,remainhr,remainday,remainmon,str1;
    SharedPreferences sp;
    //Service過濾器
    IntentFilter filter;
    //Service廣播接收
    BroadcastReceiver receiver;
    long remainder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_main_layout);
        //註冊事件
        getLayoutElement();
        //取得使用者資訊
        getUserInfo();
        //註冊service監聽
        mBroadcastReceiver();


    }

    //Service廣播信息接收
    public void mBroadcastReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle message = intent.getExtras();
                remainder = message.getLong("Key");

                //元件顯示資訊更新
                updateElement();
            }
        };

        filter = new IntentFilter("remaindertime");
        registerReceiver(receiver,filter);

        StartService();
    }

    //取得使用者資訊
    public void getUserInfo(){
        sp = getSharedPreferences("DATA",0);
        long life = sp.getLong("MAXLife",0);
        progressBar.setMax(100);
        Log.d("MYLOG","life: "+life);
        if(life < 5){
            Intent intent = new Intent();
            intent.setClass(this,signUpUser.class);
            startActivity(intent);
        }else{
            paramStatic.year = sp.getInt("YEAR",0);
            paramStatic.month = sp.getInt("MONTH",0);
            paramStatic.day = sp.getInt("DAY",0);
            paramStatic.username = sp.getString("NAME","");
            paramStatic.lifesecMAX = sp.getLong("MAXLife",0);
            paramStatic.lifesecNOW = sp.getLong("PASSLife",0);
            paramStatic.uneditMAX = sp.getLong("unMAXLife",0);
        }
    }

    //覆寫onDestroy，在關閉APP後進行資料儲存
    @Override
    protected void onDestroy(){
        sp.edit().putLong("unMAXLife",paramStatic.uneditMAX)
                .putLong("PASSLife",paramStatic.lifesecNOW)
                .commit();
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    //註冊元件
    public void getLayoutElement(){
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        remainsec = (TextView)findViewById(R.id.remainderSec);
        remainmin = (TextView)findViewById(R.id.remainderMin);
        remainhr = (TextView)findViewById(R.id.remainderHr);
        remainday = (TextView)findViewById(R.id.remainderDay);
        remainmon = (TextView)findViewById(R.id.remainderMon);
        str1 = (TextView)findViewById(R.id.text1);
    }

    //判斷Service是否已經啟動過
    public void StartService(){
        if(MyService.nowState==false) {
            Intent mMyService = new Intent(NowMainActivity.this,MyService.class);
            startService(mMyService);
            MyService.nowState=true;
            Log.d("MYLOG","啟動Service");
        }else{
            Log.d("MYLOG","Service已經啟動過了");
        }
    }

    //更新使用者資訊
    public void updateElement(){
        long[] n = paramStatic.timescalur(remainder);
        progressBar.setProgress(paramStatic.long2int(remainder));
        remainsec.setText(n[0]+" 秒");
        remainmin.setText(n[1]+" 分鐘");
        remainhr.setText(n[2]+" 小時");
        remainday.setText(n[3]+" 天");
        remainmon.setText(n[4]+" 月");
        Log.d("MYLOG","剩餘: "+n[5]+" 年");
    }

    public void settingBtn(View view){
        Intent intent = new Intent();
        intent.setClass(this,signUpUser.class);
        startActivity(intent);
    }

}

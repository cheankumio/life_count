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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.RealmModule;
import lifetime.apper.klc.lifetime.Auxiliary.paramStatic;
import lifetime.apper.klc.lifetime.Auxiliary.staticParam;
import lifetime.apper.klc.lifetime.Auxiliary.userPerferences;
import lifetime.apper.klc.lifetime.Service.MyService;


/**
 * Created by c1103304 on 2017/1/12.
 */

public class NowMainActivity extends AppCompatActivity {
    ProgressBar progressBar;

    TextView remainsec,remainmin,remainhr,remainday,remainmon,str1;
    //Service過濾器
    IntentFilter filter;
    //Service廣播接收
    BroadcastReceiver receiver;
    SharedPreferences sp;
    long[] remainder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_main_layout);
        sp = getSharedPreferences("DATA",0);

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
                boolean b = message.getBoolean("Key");
                //元件顯示資訊更新
                if(b){ updateElement();b=false;}
            }
        };
        filter = new IntentFilter("remaindertime");
        registerReceiver(receiver,filter);
        StartService();
    }

    //取得使用者資訊
    public void getUserInfo(){
        if(sp.getInt("ID",0)<1){
            Intent intent = new Intent();
            intent.setClass(this,signUpUser.class);
            startActivity(intent);
        }
    }

    //覆寫onDestroy，在關閉APP後進行資料儲存
    @Override
    protected void onDestroy(){
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
        ArrayList<staticParam> ls = MyService.tmp;
        for(int i=0;i<MyService.counts;i++) {
                Log.d("Scular","NAME: "+ls.get(i).getName()+" remainder: "+ls.get(i).getNow()+" %: "+ls.get(i).getPercent());
            //progressBar.setProgress(paramStatic.long2int(newRemainder,maxnum));

//            remainsec.setText(n[0] + " 秒");
//            remainmin.setText(n[1] + " 分鐘");
//            remainhr.setText(n[2] + " 小時");
//            remainday.setText(n[3] + " 天");
//            remainmon.setText(n[4] + " 月");
//            Log.d("MYLOG", "剩餘: " + n[5] + " 年");
        }
    }

    public void settingBtn(View view){
        Intent intent = new Intent();
        intent.setClass(this,signUpUser.class);
        startActivity(intent);
    }




    private void insertdata(){
//        for(int i =0;i<200;i++) {
//            userPerferences d1 = new userPerferences();
//            d1.setId(i);
//            d1.setName("a"+i);
//            d1.setMaxSec(99995-i);
//            d1.setBornSec(4121+(i*2));
//            realm.copyToRealmOrUpdate(d1);
//        }
//        realm.commitTransaction();
        RealmQuery<userPerferences> query = MyService.realm.where(userPerferences.class);
        RealmResults<userPerferences> result = query.findAll();
        for (userPerferences d : result) {
            Log.d("MYLOG","ID: "+d.getId()+" ,Name: "+d.getName()+" ,Max: "+d.getMaxSec());
        }
    }

}

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
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
    LinearLayout[] ly;
    TextView[] name,remnum1,remnum2;
    ProgressBar[] ps;
    Animation as;
    ProgressBar ps1,ps2,ps3,ps4;
    ProgressBar progressBar;
    private GridView gridView;
    List<Map<String, Object>> items;
    Map<String, Object> item;
    SimpleAdapter adapter;
    TextView remainsec,remainmin,remainhr,remainday,remainmon,str1;
    //Service過濾器
    IntentFilter filter;
    //Service廣播接收
    BroadcastReceiver receiver;
    SharedPreferences sp;
    int usernum=0;
    long[] remainder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_main_layout);
        sp = getSharedPreferences("DATA",0);
        as = AnimationUtils.loadAnimation(this,R.anim.anim);
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
        usernum = sp.getInt("ID",0);
        if(usernum<1){
            Intent intent = new Intent();
            intent.setClass(this,signUpUser.class);
            startActivity(intent);
            ly[0].setVisibility(View.VISIBLE);
        }else{
            for (int i = 0; i < usernum; i++) {
                ly[i].setVisibility(View.VISIBLE);
                ly[i].setAnimation(as);
            }
            as.startNow();
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
        int[] lyid={R.id.ly1,R.id.ly2,R.id.ly3,R.id.ly4};
        int[] nameid={R.id.name1,R.id.name2,R.id.name3,R.id.name4};
        int[] remid1={R.id.remainder11,R.id.remainder21,R.id.remainder31,R.id.remainder41};
        int[] remid2={R.id.remainder12,R.id.remainder22,R.id.remainder32,R.id.remainder42};
        int[] psid={R.id.pb1,R.id.pb2,R.id.pb3,R.id.pb4};
        ly = new LinearLayout[4];
        name = new TextView[4];
        remnum1 = new TextView[4];
        remnum2 = new TextView[4];
        ps = new ProgressBar[4];
        for(int i=0;i<4;i++){
            ly[i] = (LinearLayout)findViewById(lyid[i]);
            ly[i].setVisibility(View.INVISIBLE);
            name[i] = (TextView)findViewById(nameid[i]);
            remnum1[i] = (TextView)findViewById(remid1[i]);
            remnum2[i] = (TextView)findViewById(remid2[i]);
            ps[i] = (ProgressBar)findViewById(psid[i]);
        }

//        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
//        remainsec = (TextView)findViewById(R.id.remainderSec);
//        remainmin = (TextView)findViewById(R.id.remainderMin);
//        remainhr = (TextView)findViewById(R.id.remainderHr);
//        remainday = (TextView)findViewById(R.id.remainderDay);
//        remainmon = (TextView)findViewById(R.id.remainderMon);
//        str1 = (TextView)findViewById(R.id.text1);
//        gridView = (GridView)findViewById(R.id.gridView);
//        gridView.setNumColumns(2);
//
//        items = new ArrayList<>();
//        item = new HashMap<>();
//        item.put("text", "Num: ");
//        items.add(item);
//        adapter = new SimpleAdapter(this,items,R.layout.dynamic_layout
//                ,new String[]{"text"},new int[]{R.id.textView});
//        gridView.setAdapter(adapter);
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
        Log.d("MYLOG",usernum+"");
        for(int i=0;i<MyService.counts;i++) {
                Log.d("Scular","NAME: "+ls.get(i).getName()+" remainder: "
                        +ls.get(i).getNow()+" %: "+ls.get(i).getPercent());
            name[i].setText(ls.get(i).getName());
            ps[i].setProgress(ls.get(i).getPercent());
            remnum1[i].setText(ls.get(i).getPercent()+" %");
            remnum2[i].setText("剩餘: "+(ls.get(i).getNow()/86400)+"天");

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
        usernum = sp.getInt("ID",0);
        Intent intent = new Intent();
        intent.setClass(this,signUpUser.class);
        startActivity(intent);
        ly[usernum].setVisibility(View.VISIBLE);

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

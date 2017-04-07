package lifetime.apper.klc.lifetime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

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
import lifetime.apper.klc.lifetime.Auxiliary.staticParam;
import lifetime.apper.klc.lifetime.Auxiliary.userPerferences;
import lifetime.apper.klc.lifetime.Service.MyService;


/**
 * Created by c1103304 on 2017/1/12.
 */

public class NowMainActivity extends AppCompatActivity {
    LinearLayout[] ly;
    TextView[] name,remnum1,remnum2,remnum3;
    IconRoundCornerProgressBar[] ps;
    Animation as;
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
        int[] remid3={R.id.hr1,R.id.hr2,R.id.hr3,R.id.hr4};
        int[] psid={R.id.pbs1,R.id.pbs2,R.id.pbs3,R.id.pbs4};
        ly = new LinearLayout[4];
        name = new TextView[4];
        remnum1 = new TextView[4];
        remnum2 = new TextView[4];
        remnum3 = new TextView[4];
        ps = new IconRoundCornerProgressBar[4];
        for(int i=0;i<4;i++){
            ly[i] = (LinearLayout)findViewById(lyid[i]);
            ly[i].setVisibility(View.INVISIBLE);
            name[i] = (TextView)findViewById(nameid[i]);
            remnum1[i] = (TextView)findViewById(remid1[i]);
            remnum2[i] = (TextView)findViewById(remid2[i]);
            remnum3[i] = (TextView)findViewById(remid3[i]);
            ps[i] = (IconRoundCornerProgressBar)findViewById(psid[i]);
        }
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
            long remainds = ls.get(i).getNow();
                Log.d("Scular","NAME: "+ls.get(i).getName()+" remainder: "
                        +remainds+" %: "+ls.get(i).getPercent());
            name[i].setText(ls.get(i).getName());
            ps[i].setProgress(ps[i].getMax()-ls.get(i).getPercent());
            remnum1[i].setText("人生悄悄的過了.. "+ls.get(i).getPercent()+" %");
            remnum2[i].setText("剩餘時間: "+(remainds/86400)+" 天");
            remnum3[i].setText(remainds/3600+" 小時\n"+remainds/60+" 分鐘");
        }
    }

    public void lnckick(View view){
        ArrayList<staticParam> ls = MyService.tmp;
        int i = 0;
        switch (view.getId()){
            case R.id.ly1:
                i=0;
                //MyService.delete(0);
                break;
            case R.id.ly2:
                i=1;
                //MyService.delete(1);
                break;
            case R.id.ly3:
                i=2;
                //MyService.delete(2);
                break;
            case R.id.ly4:
                i=3;
                //MyService.delete(3);
                break;
        }
        Intent in = new Intent();
        in.setClass(this,EditUserInfo.class);
        in.putExtra("block",i);
        startActivity(in);

        //ly[MyService.counts].setVisibility(View.INVISIBLE);
        //sp.edit().putInt("ID",MyService.counts).commit();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.addition:
                usernum = sp.getInt("ID",0);
                if(usernum<4) {
                    Intent intent = new Intent();
                    intent.setClass(this, signUpUser.class);
                    startActivityForResult(intent,77);
                    //ly[usernum].setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(this, "請購買付費完整版，可增加卡片數量上限。", Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case 77:
                if(resultCode!=0){
                    ly[usernum].setVisibility(View.VISIBLE);
                }
                break;
        }
    }

}

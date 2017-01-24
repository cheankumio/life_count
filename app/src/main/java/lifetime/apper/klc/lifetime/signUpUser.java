package lifetime.apper.klc.lifetime;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import io.realm.RealmQuery;
import io.realm.RealmResults;
import lifetime.apper.klc.lifetime.Auxiliary.paramStatic;
import lifetime.apper.klc.lifetime.Auxiliary.pickerdialogs;
import lifetime.apper.klc.lifetime.Auxiliary.staticParam;
import lifetime.apper.klc.lifetime.Auxiliary.userPerferences;
import lifetime.apper.klc.lifetime.Service.MyService;

/**
 * Created by c1103304 on 2017/1/12.
 * 第一次使用之使用者註冊畫面
 */

public class signUpUser extends AppCompatActivity {
    TextView mbornDate;
    EditText muserName,mwishAge;
    SharedPreferences sp;
    int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        sp = getSharedPreferences("DATA",0);
        id = sp.getInt("ID",0);
        setElemtent();
    }

    //註冊元件
    public void setElemtent(){
        mbornDate = (TextView)findViewById(R.id.bornDate);
        muserName = (EditText)findViewById(R.id.userName);
        mwishAge = (EditText)findViewById(R.id.wishlife);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //讀取使用者資訊，並存入SharePreferences
    public void savedata(View view){
        userPerferences d1 = new userPerferences();
        d1.setId(id);
        d1.setName(muserName.getText().toString());
        long[] tmplong = getLifeMax();
        d1.setMaxSec(tmplong[2]/1000);   //最大值 / 1000 縮小範圍
        d1.setBornSec(tmplong[3]/1000);  //出生年齡 / 1000 縮小範圍
        MyService.realm.copyToRealmOrUpdate(d1);
        MyService.realm.commitTransaction();
        MyService.renew();
        Log.d("MYLOG","maxlife: "+tmplong[1]+" passlife: "+tmplong[0]+" uneditmax: "+tmplong[2]);

        sp.edit().putInt("ID",++id).commit();


        finish();
    }

    //跳出選擇日期視窗
    public void chooseDate(View view){
        pickerdialogs mpickdialogs = new pickerdialogs();
        mpickdialogs.show(getFragmentManager(),"date_picker");
    }


    //計算生命最大值
    @TargetApi(Build.VERSION_CODES.N)
    public long[] getLifeMax(){

        int year = staticParam.year;
        int month = staticParam.month;
        int day = staticParam.day;
        int age = Integer.parseInt(mwishAge.getText().toString());

        Calendar mcalendarMAX = Calendar.getInstance(Locale.getDefault());
        //取得目前時間秒數
        long NowLife = mcalendarMAX.getTimeInMillis();

        //取得出生日期秒數
        mcalendarMAX.set(year,month,day,12,0);
        long BornLife = mcalendarMAX.getTimeInMillis();

        //取得最大壽命秒數
        mcalendarMAX.set(year+age,month,day,12,0);
        long MaxLife = mcalendarMAX.getTimeInMillis();
        long lifemax = MaxLife - BornLife;   // 最大年齡 - 出生年齡 = 總壽命長度
        long lifepass = NowLife - BornLife;  // 現在年齡 - 出生年齡 = 已過秒數

        long[] returnlong ={lifepass,lifemax,MaxLife,BornLife};
        Log.d("MYLOG",lifepass+", "+lifemax+" ,"+MaxLife+" ,"+BornLife);
        return returnlong;
    }



}

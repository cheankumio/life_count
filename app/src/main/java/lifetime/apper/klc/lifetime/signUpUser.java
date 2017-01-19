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

import lifetime.apper.klc.lifetime.Auxiliary.paramStatic;
import lifetime.apper.klc.lifetime.Auxiliary.pickerdialogs;

/**
 * Created by c1103304 on 2017/1/12.
 * 第一次使用之使用者註冊畫面
 */

public class signUpUser extends AppCompatActivity {
    TextView mbornDate;
    EditText muserName,mwishAge;
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        sp = getSharedPreferences("DATA",0);
        setElemtent();
    }

    //註冊元件
    public void setElemtent(){
        mbornDate = (TextView)findViewById(R.id.bornDate);
        muserName = (EditText)findViewById(R.id.userName);
        mwishAge = (EditText)findViewById(R.id.wishlife);
    }

    //讀取使用者資訊，並存入SharePreferences
    public void savedata(View view){
        paramStatic.username = muserName.getText().toString();
        paramStatic.endage = Integer.parseInt(mwishAge.getText().toString());
        long[] tmplong = getLifeMax();
        paramStatic.lifesecMAX = tmplong[1]; //最大壽命(秒)
        paramStatic.lifesecNOW = tmplong[0]; //已過壽命(秒)
        paramStatic.uneditMAX = tmplong[2]; //未修正過的最大值
        Log.d("MYLOG","maxlife: "+tmplong[1]+" passlife: "+tmplong[0]+" uneditmax: "+tmplong[2]);
        save2sharepreferences();
    }

    //跳出選擇日期視窗
    public void chooseDate(View view){
        pickerdialogs mpickdialogs = new pickerdialogs();
        mpickdialogs.show(getFragmentManager(),"date_picker");
    }

    //儲存資料
     public void save2sharepreferences(){
        sp.edit().putInt("YEAR",paramStatic.year)
                .putInt("MONTH",paramStatic.month)
                .putInt("DAY",paramStatic.day)
                .putString("NAME",paramStatic.username)
                .putLong("MAXLife",paramStatic.lifesecMAX)
                .putLong("unMAXLife",paramStatic.uneditMAX)
                .putLong("PASSLife",paramStatic.lifesecNOW)
                .commit();
         //資料儲存完畢後關閉目前Activity
         finish();
    }


    //計算生命最大值
    @TargetApi(Build.VERSION_CODES.N)
    public long[] getLifeMax(){

        int year = paramStatic.year;
        int month = paramStatic.month;
        int day = paramStatic.day;
        int age = paramStatic.endage;

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

        long[] returnlong ={lifepass,lifemax,MaxLife};
        return returnlong;
    }
}

package lifetime.apper.klc.lifetime.Auxiliary;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.Log;

import java.util.Locale;

import io.realm.RealmQuery;
import io.realm.RealmResults;
import lifetime.apper.klc.lifetime.NowMainActivity;
import lifetime.apper.klc.lifetime.Service.MyService;

/**
 * Created by c1103304 on 2017/1/12.
 * 儲存全域變數
 * 供各Activity使用
 */

public class paramStatic {
    public long lifesecMAX;
    public long uneditMAX;

    public long getLifesecMAX() {
        return lifesecMAX;
    }

    public void setLifesecMAX(long lifesecMAX) {
        this.lifesecMAX = lifesecMAX;
    }

    public long getUneditMAX() {
        return uneditMAX;
    }

    public void setUneditMAX(long uneditMAX) {
        this.uneditMAX = uneditMAX;
    }

    public long getLifesecNOW() {
        return lifesecNOW;
    }

    public void setLifesecNOW(long lifesecNOW) {
        this.lifesecNOW = lifesecNOW;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long lifesecNOW;
    public String username="";
    public  final String smoke="SMOKE";
    public  final String betel="BETEL";
    public  final String sport="SPORT";
    public int year,month,day;

    public static long[] timescalur(long remaindersec){
        long sec = remaindersec;
        long min = sec/60;
        long hr = min/60;
        long day = hr/24;
        long month = day/30;
        long year = month/12;
        long[] time= {sec,min,hr,day,month,year};
        return time;
    }

    //計算時間:  (現在時間 / 最大時間)*100 -> 轉換為百分比數值
    public static int long2int(long now, long max){
        float a = now/1000000;
        float b = max/1000000;
        return scalur(a,b);
    }
    @TargetApi(Build.VERSION_CODES.N)
    public static int long2int(long max){
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        long now = calendar.getTimeInMillis()/1000;
        float a = now/1000000;
        float b = max/1000000;
        return scalur(a,b);
    }
    @TargetApi(Build.VERSION_CODES.N)
    public static long getNowTimeSec(long max){
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        long now = (max - (calendar.getTimeInMillis()/1000));
        return now;
    }
    private static int scalur(float a, float b){
        long tmp = (long)((a/b)*100);
        int s = (Integer.parseInt(String.valueOf(tmp)));
        return s;
    }

    public void requery(int id){
        RealmQuery<userPerferences> query = MyService.realm.where(userPerferences.class);
        query.equalTo("id",id);
        RealmResults<userPerferences> result = query.findAll();
        for (userPerferences d : result) {
            Log.d("MYLOG","ID: "+d.getId()+" ,Name: "+d.getName()+" ,Max: "+d.getMaxSec()+" ,Born: "+d.getBornSec());
        }
    }
}

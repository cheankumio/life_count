package lifetime.apper.klc.lifetime;

import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.util.Log;

/**
 * Created by c1103304 on 2017/1/12.
 * 儲存全域變數
 * 供各Activity使用
 */

public class paramStatic {
    public static long lifesecMAX;
    public static long uneditMAX;
    public static long lifesecNOW;
    public static int endage;
    public static String username="";
    public  static final String smoke="SMOKE";
    public  static final String betel="BETEL";
    public  static final String sport="SPORT";
    public static int year,month,day;

    public static long[] timescalur(long remaindersec){
        long sec = remaindersec/1000;
        long min = sec/60;
        long hr = min/60;
        long day = hr/24;
        long month = day/30;
        long year = month/12;
        long[] time= {sec,min,hr,day,month,year};
        return time;
    }

    //計算時間:  (現在時間 / 最大時間)*100 -> 轉換為百分比數值
    public static int long2int(long now){
        float a = now/1000000;
        float b = lifesecMAX/1000000;
        return scalur(a,b);
    }
    public static int long2int(long now, long max){
        float a = now/1000000;
        float b = max/1000000;
        return scalur(a,b);
    }
    private static int scalur(float a, float b){
        long tmp = (long)((a/b)*100);
        Log.d("MYLOG","tmp: "+tmp+" now: "+a/b);
        int s = (Integer.parseInt(String.valueOf(tmp)));
        return s;
    }
}

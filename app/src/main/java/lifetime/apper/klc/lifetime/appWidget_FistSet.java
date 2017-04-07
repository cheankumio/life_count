package lifetime.apper.klc.lifetime;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import lifetime.apper.klc.lifetime.Auxiliary.staticParam;
import lifetime.apper.klc.lifetime.Service.MyService;
import lifetime.apper.klc.lifetime.Service.appWidget_Service;

/**
 * Created by 151305 on 2017/2/16.
 */

public class appWidget_FistSet extends Activity {
    LinearLayout[] ly;
    TextView[] name,remnum1,remnum2,remnum3;
    ProgressBar[] ps;
    Animation as;
    //Service過濾器
    IntentFilter filter;
    //Service廣播接收
    BroadcastReceiver receiver;
    SharedPreferences sp;
    int usernum=0;
    long[] remainder;

    private static final String PREFS_NAME = "lifetime.apper.klc.lifetime.lifeCard";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    /*
    *   進行更新函式.用於
    * */
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = appWidget_FistSet.this;
            int i = 0;
            switch (v.getId()){
                case R.id.ly1:
                    i=0;
                    break;
                case R.id.ly2:
                    i=1;
                    break;
                case R.id.ly3:
                    i=2;
                    break;
                case R.id.ly4:
                    i=3;
                    break;
            }
            context.startService(new Intent(context, appWidget_Service.class));


            // Set the result to CANCELED.  This will cause the widget host to cancel
            // out of the widget placement if the user presses the back button.

            // When the button is clicked, store the string locally

            //String widgetText = mAppWidgetText.getText().toString();
            //saveTitlePref(context, mAppWidgetId, widgetText);

            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            LifeCount_appWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
        prefs.apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.now_main_layout);
//        mAppWidgetText = (EditText) findViewById(R.id.appwidget_text);
//        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

//        mAppWidgetText.setText(loadTitlePref(lifeCardConfigureActivity.this, mAppWidgetId));


        sp = getSharedPreferences("DATA",0);
        as = AnimationUtils.loadAnimation(this,R.anim.anim);
        //註冊事件
        getLayoutElement();
        //取得使用者資訊
        getUserInfo();
        //註冊service監聽
        mBroadcastReceiver();


    }

    //註冊元件
    public void getLayoutElement(){
        int[] lyid={R.id.ly1,R.id.ly2,R.id.ly3,R.id.ly4};
        int[] nameid={R.id.name1,R.id.name2,R.id.name3,R.id.name4};
        int[] remid1={R.id.remainder11,R.id.remainder21,R.id.remainder31,R.id.remainder41};
        int[] remid2={R.id.remainder12,R.id.remainder22,R.id.remainder32,R.id.remainder42};
        int[] remid3={R.id.hr1,R.id.hr2,R.id.hr3,R.id.hr4};
        //int[] psid={R.id.pb1,R.id.pb2,R.id.pb3,R.id.pb4};
        ly = new LinearLayout[4];
        name = new TextView[4];
        remnum1 = new TextView[4];
        remnum2 = new TextView[4];
        remnum3 = new TextView[4];
        ps = new ProgressBar[4];
        for(int i=0;i<4;i++){
            ly[i] = (LinearLayout)findViewById(lyid[i]);
            ly[i].setVisibility(View.INVISIBLE);
            name[i] = (TextView)findViewById(nameid[i]);
            remnum1[i] = (TextView)findViewById(remid1[i]);
            remnum2[i] = (TextView)findViewById(remid2[i]);
            remnum3[i] = (TextView)findViewById(remid3[i]);
            //ps[i] = (ProgressBar)findViewById(psid[i]);

            ly[i].setOnClickListener(mOnClickListener);
        }

        //findViewById(R.id.button4).setVisibility(View.GONE);
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

    //判斷Service是否已經啟動過
    public void StartService(){
        if(MyService.nowState==false) {
            Intent mMyService = new Intent(appWidget_FistSet.this,MyService.class);
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
            //ps[i].setProgress(ls.get(i).getPercent());
            remnum1[i].setText("人生悄悄的過了.. "+ls.get(i).getPercent()+" %");
            remnum2[i].setText("剩餘時間: "+(remainds/86400)+" 天");
            remnum3[i].setText(remainds/3600+" 小時\n"+remainds/60+" 分鐘");
        }
    }


}

package lifetime.apper.klc.lifetime.Service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import lifetime.apper.klc.lifetime.Auxiliary.DrawUtils;
import lifetime.apper.klc.lifetime.LifeCount_appWidget;
import lifetime.apper.klc.lifetime.R;

import static lifetime.apper.klc.lifetime.LifeCount_appWidget.FILE_WIDGET_NAME;
/**
 * Created by 151305 on 2017/1/18.
 */

public class appWidget_Service extends Service {
    private Handler mHandler = null;
    private static int[] appWidgetIds={};
    private static HashMap appWidget_proportion=new HashMap();
    public static final String SP_BASE_KEY = "widgetid_";
    public static void setAppWidgetIds(Context context,int[] appWidgetIds_) {
        SharedPreferences sp = context.getSharedPreferences(FILE_WIDGET_NAME, Context.MODE_PRIVATE);
        for (int id : appWidgetIds) {
            sp.edit().putInt(SP_BASE_KEY + id,id);
        }
        sp.edit().commit();

        boolean haveId = false;
        if (appWidgetIds_ != null)
            if (appWidgetIds_.length > 0)
                haveId = true;


        if (haveId) {
            for (int Id : appWidgetIds_) {
                int[] Ids=appWidgetIds;
                Arrays.sort(Ids);
                if(Arrays.binarySearch(Ids,Id)<0){//沒找到Id，進行儲存
                    appWidgetIds=new int[Ids.length+1];
                    for(int i=0;i<appWidgetIds.length;i++){
                        if(i<Ids.length)
                            appWidgetIds[i]=Ids[i];
                        else
                            appWidgetIds[i]=Id;
                    }
                    appWidget_proportion.put(Id,1);
                }
            }
        }
    }
    public static void delAppWidgetIds(Context context ,int appWidgetIds_) {
        delAppWidgetIds(context,new int[]{appWidgetIds_});
    }
    public static void delAppWidgetIds(Context context ,int[] appWidgetIds_){
        SharedPreferences sp = context.getSharedPreferences(FILE_WIDGET_NAME, Context.MODE_PRIVATE);
        for (int id : appWidgetIds) {
            sp.edit().remove(SP_BASE_KEY + id);
        }
        sp.edit().commit();

        boolean haveId = false;
        if (appWidgetIds_ != null)
            if (appWidgetIds_.length > 0)
                haveId = true;


        if (haveId) {
            for (int Id : appWidgetIds_) {
                boolean findId=false;
                for(int i =0 ;i<appWidgetIds.length;i++)
                    if(appWidgetIds[i]==Id){
                        appWidgetIds[i]=appWidgetIds[appWidgetIds.length-1];
                        findId=true;
                    }

                if(findId){
                    int[] Ids=appWidgetIds;
                    appWidgetIds=new int[Ids.length-1];
                    for (int i = 0 ; i < (Ids.length-1) ; i ++)
                        appWidgetIds[i]=Ids[i];
                    appWidget_proportion.remove(Id);
                }
            }
            appWidgetIds = appWidgetIds_;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler();
        mHandler.post(mRunnable);
        return super.onStartCommand(intent, flags, startId);
    }
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            updateView();
            mHandler.postDelayed(this, 1000);
        }
    };
    private synchronized void updateView(){
        if(appWidgetIds.length>0) {
            for(int appWidgetId:appWidgetIds) {
                Object proportion = appWidget_proportion.get(appWidgetId);
                if (proportion != null) {
                    int count = (int)proportion;
                    if (count > 100)
                        count = 0;
                    count++;

                    Context context = getApplicationContext();
                    RemoteViews view = new RemoteViews(getPackageName(), R.layout.life_count_app_widget);


                    float c = ((float) count) / 100;
                    //繪制圖片
                    Bitmap BM = DrawUtils.TYPE_POINT.draw(context, c, "" + c, "文字2");
                    //Bitmap bitmapStorage = DrawUtils.TYPE_STORAGE.draw(context, WidgetUtils.storageUsedRat(), toDrawText[0], toDrawText[1]);
                    //更新圖片
                    view.setImageViewBitmap(R.id.test_imageView, BM);
                    //view.setImageViewBitmap(R.id.widget_imgv_storage, bitmapStorage);


                    //ComponentName thisWidget = new ComponentName(this, LifeCount_appWidget.class);
                    appWidget_proportion.put(appWidgetId,count);
                    AppWidgetManager manager = AppWidgetManager.getInstance(this);
                    manager.updateAppWidget(appWidgetId, view);
                }
            }
        }
    }
}

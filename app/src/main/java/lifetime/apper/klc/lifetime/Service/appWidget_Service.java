package lifetime.apper.klc.lifetime.Service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

import lifetime.apper.klc.lifetime.Auxiliary.DrawUtils;
import lifetime.apper.klc.lifetime.LifeCount_appWidget;
import lifetime.apper.klc.lifetime.R;

/**
 * Created by 151305 on 2017/1/18.
 */

public class appWidget_Service extends Service {
    private Handler mHandler = null;
    private int count=50;

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
    private void updateView(){
        Context context = getApplicationContext();
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.life_count_app_widget);
        if(count>100)
            count=0;
        count++;
        float c=((float) count)/100;
        //繪制圖片
        Bitmap BM = DrawUtils.TYPE_POINT.draw(context, c, ""+c, "文字2");
        //Bitmap bitmapStorage = DrawUtils.TYPE_STORAGE.draw(context, WidgetUtils.storageUsedRat(), toDrawText[0], toDrawText[1]);
        //更新圖片
        view.setImageViewBitmap(R.id.test_imageView, BM);
        //view.setImageViewBitmap(R.id.widget_imgv_storage, bitmapStorage);


        ComponentName thisWidget = new ComponentName(this, LifeCount_appWidget.class);

        AppWidgetManager manager = AppWidgetManager.getInstance(this);

        manager.updateAppWidget(thisWidget, view);
    }
}

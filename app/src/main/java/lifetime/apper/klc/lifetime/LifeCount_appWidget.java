package lifetime.apper.klc.lifetime;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import lifetime.apper.klc.lifetime.Service.appWidget_Service;

/**
 * Implementation of App Widget functionality.
 */
public class LifeCount_appWidget extends AppWidgetProvider {
    private static final String TAG = "smw";
    public static final String FILE_WIDGET_NAME = "widgetId.txt";
    public static final String SP_BASE_KEY = "widgetid_";
    private static final String ACTION_WALLPAPER_COLOR_CHANGE = "com.android.launcher3.WALLPAPER_MASTER_COLOR_CHANGE";//壁紙改變廣播
    private static final String EXTRA_WHITE_WALLPAPER = "whiteWallpaper";
    public static final String ISWHITEWALLER_FILENAME = "isWhiteWaller";
    public static final String ISWHITEWALLER = "isWhiteWaller";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.life_count_app_widget);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }

        //context.startService(new Intent(context, appWidget_Service.class));
    }
    @Override
    public void onEnabled(Context context) {
        context.startService(new Intent(context, appWidget_Service.class));
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        context.stopService(new Intent(context, appWidget_Service.class));
        context.getSharedPreferences(FILE_WIDGET_NAME, Context.MODE_PRIVATE).edit().clear();
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();

    }


    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        //清除id值
        SharedPreferences sp = context.getSharedPreferences(FILE_WIDGET_NAME, Context.MODE_PRIVATE);
        for (int id : appWidgetIds) {
            sp.edit().remove(SP_BASE_KEY + id).commit();
        }
    }
}


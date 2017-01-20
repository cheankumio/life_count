package lifetime.apper.klc.lifetime.Auxiliary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.DatePicker;

/**
 * Created by klc on 2017/1/7.
 */

public class DateSetting implements DatePickerDialog.OnDateSetListener{
    Context context;
    public DateSetting(Context context){
        this.context = context;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        staticParam.year = year;
        staticParam.month = month;
        staticParam.day = dayOfMonth;
    }
}

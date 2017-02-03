package lifetime.apper.klc.lifetime.Auxiliary;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import java.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import java.util.GregorianCalendar;

import lifetime.apper.klc.lifetime.Auxiliary.DateSetting;

/**
 * Created by klc on 2017/1/7.
 */

public class pickerdialogs extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DateSetting dateSetting = new DateSetting(getActivity());

        GregorianCalendar calendar = new GregorianCalendar();
        int year = 1991;
        int month = 6;
        int day = 30;
        DatePickerDialog dialog;
        dialog = new DatePickerDialog(getActivity(), dateSetting, year, month, day);


        return dialog;
    }
}

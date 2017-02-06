package lifetime.apper.klc.lifetime;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import lifetime.apper.klc.lifetime.Auxiliary.pickerdialogs;
import lifetime.apper.klc.lifetime.Auxiliary.staticParam;
import lifetime.apper.klc.lifetime.Auxiliary.userPerferences;
import lifetime.apper.klc.lifetime.Service.MyService;

/**
 * Created by c1103304 on 2017/2/3.
 */

public class EditUserInfo extends Activity{
    EditText name,age;
    public static Button born;
    String _name,_age;
    int _id;
    long _bornday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_layout);

        name = (EditText)findViewById(R.id.editText);
        age = (EditText)findViewById(R.id.editText2);
        born = (Button)findViewById(R.id.button3);

        Intent getnum = this.getIntent();
        int i = getnum.getIntExtra("block",0);

        _bornday = MyService.tmp.get(i).getBorn()*1000;
        _name = MyService.tmp.get(i).getName();
        _id = MyService.tmp.get(i).getId();

        Date date = new Date(_bornday);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String time=sdf.format(date);

        born.setText("生日: "+ time);
        name.setText(_name);





    }

    public void ok(View v){
        Calendar cn = Calendar.getInstance();
        cn.set(staticParam.year,staticParam.month,staticParam.day,12,0);
        long tmp1 = _bornday/43200;
        long tmp2 = cn.getTimeInMillis()/43200000;
        long[] tmplong;
        if(name.length()>0&&age.length()>0) {
            MyService.realm.beginTransaction();
            userPerferences d1 = new userPerferences();
            d1.setId(_id);
            d1.setName(name.getText().toString());
            if(tmp1==tmp2) {
                tmplong = signUpUser.getLifeMax(Integer.parseInt(age.getText().toString()), _bornday);
            }else {
                tmplong = signUpUser.getLifeMax(Integer.parseInt(age.getText().toString()));
            }
            d1.setMaxSec(tmplong[2] / 1000);   //最大值 / 1000 縮小範圍
            d1.setBornSec(tmplong[3] / 1000);  //出生年齡 / 1000 縮小範圍
            Log.d("MYLOS","bornday: "+tmplong[2] / 1000+"bbd"+tmplong[3] / 1000);
            MyService.realm.copyToRealmOrUpdate(d1);
            MyService.realm.commitTransaction();
            MyService.renew();
            finish();
        }else{
            Toast.makeText(this, "請正確輸入姓名與生命長度。", Toast.LENGTH_SHORT).show();
        }
    }




    public void bornclick(View view){
        pickerdialogs mpickdialogs = new pickerdialogs();
        mpickdialogs.show(getFragmentManager(),"date_picker");
    }

    public void close(View v){
        finish();
    }

}

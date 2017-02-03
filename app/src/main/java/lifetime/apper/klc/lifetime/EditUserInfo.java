package lifetime.apper.klc.lifetime;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import lifetime.apper.klc.lifetime.Auxiliary.pickerdialogs;
import lifetime.apper.klc.lifetime.Auxiliary.staticParam;
import lifetime.apper.klc.lifetime.Service.MyService;

/**
 * Created by c1103304 on 2017/2/3.
 */

public class EditUserInfo extends Activity{
    EditText name,age;
    TextView born;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_layout);
        Date date = new Date(MyService.tmp.get(0).getBorn()*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String time=sdf.format(date);
        name = (EditText)findViewById(R.id.editText);
        age = (EditText)findViewById(R.id.editText2);
        born = (TextView)findViewById(R.id.textView5);
        born.setText("生日: "+ time);



    }


    public void bornclick(View view){
        pickerdialogs mpickdialogs = new pickerdialogs();
        mpickdialogs.show(getFragmentManager(),"date_picker");
    }
}

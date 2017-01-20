package lifetime.apper.klc.lifetime.Service;

import java.util.List;

import lifetime.apper.klc.lifetime.NowMainActivity;

/**
 * Created by c1103304 on 2017/1/20.
 */


public class ListOnListenner {
    List tmp = MyService.item;
    public List ListOnListenner(List list){
        if(list != tmp){
            list = tmp;
        }
        return list;
    }
}

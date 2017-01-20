package lifetime.apper.klc.lifetime.Auxiliary;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by c1103304 on 2017/1/20.
 */

public class userPerferences extends RealmObject {
    @PrimaryKey
    private int id;
    private String Name;
    private long maxSec;
    private long bornSec;
    private String color;
    private String tmp2;
    private String tmp3;
    private String tmp4;
    private String tmp5;
    private String tmp6;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getMaxSec() {
        return maxSec;
    }

    public void setMaxSec(long maxSec) {
        this.maxSec = maxSec;
    }

    public long getBornSec() {
        return bornSec;
    }

    public void setBornSec(long bornSec) {
        this.bornSec = bornSec;
    }

    public String getcolor() {
        return color;
    }

    public void setcolor(String color) {
        this.color = color;
    }

    public String getTmp2() {
        return tmp2;
    }

    public void setTmp2(String tmp2) {
        this.tmp2 = tmp2;
    }

    public String getTmp3() {
        return tmp3;
    }

    public void setTmp3(String tmp3) {
        this.tmp3 = tmp3;
    }

    public String getTmp4() {
        return tmp4;
    }

    public void setTmp4(String tmp4) {
        this.tmp4 = tmp4;
    }

    public String getTmp5() {
        return tmp5;
    }

    public void setTmp5(String tmp5) {
        this.tmp5 = tmp5;
    }

    public String getTmp6() {
        return tmp6;
    }

    public void setTmp6(String tmp6) {
        this.tmp6 = tmp6;
    }

    public String getTmp7() {
        return tmp7;
    }

    public void setTmp7(String tmp7) {
        this.tmp7 = tmp7;
    }

    public String getTmp8() {
        return tmp8;
    }

    public void setTmp8(String tmp8) {
        this.tmp8 = tmp8;
    }

    private String tmp7;
    private String tmp8;

}

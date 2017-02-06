package lifetime.apper.klc.lifetime.Auxiliary;

/**
 * Created by c1103304 on 2017/1/20.
 */

public class staticParam {
    public static int year,month,day;
    private long max;
    private long now;
    private long born;
    private int percent;
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBorn() {
        return born;
    }

    public void setBorn(long born) {
        this.born = born;
    }



    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

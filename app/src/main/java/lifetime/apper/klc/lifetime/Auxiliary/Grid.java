package lifetime.apper.klc.lifetime.Auxiliary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lifetime.apper.klc.lifetime.R;

/**
 * Created by c1103304 on 2017/1/24.
 */

public class Grid extends BaseAdapter {

    private Context mContext;
    private final String[] menu;
    private final String[] gridcolor;

    public Grid(Context context, String[] gridcolor)
    {
        mContext=context;
        this.gridcolor=gridcolor;
        this.menu = gridcolor;
    }

    @Override
    public int getCount() {
        return gridcolor.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.dynamic_layout, null);

            grid.setBackgroundColor(Color.parseColor(gridcolor[i]));

        } else
        {
            grid =  view;
        }

        return grid;
    }
}

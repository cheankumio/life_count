package lifetime.apper.klc.lifetime.Auxiliary;

import android.content.Context;

/**
 * Created by WeiHao on 2017/1/18.
 * 輔助函式
 */

public class Auxiliary_Tool {

    /**
     * 根據手機分辨率從 dp 單位 轉成為 px(像素)
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}

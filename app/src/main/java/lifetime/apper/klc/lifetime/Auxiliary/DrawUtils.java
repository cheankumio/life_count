package lifetime.apper.klc.lifetime.Auxiliary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by WeiHao on 2017/1/18.
 * 圓形倒數條 繪製
 */

public enum DrawUtils {
    TYPE_CIRCULAR, TYPE_POINT, TYPE_NODE;
    private float mProcess;//繪制的進度
    private String mInfo, unit;//繪制的文本信息
    private static int width, height;//圓環的寬高
    private RectF mRectF;
    private Bitmap bm, bitmap;
    private Canvas canvas;
    private Paint mPaint;
    private Context context;

    //傳入一些必要的信息
    public Bitmap draw(Context context, float process, String info, String unit) {
        mProcess = process;
        this.context = context;
        this.unit = unit;
        mInfo = info;
        init();
        //判斷是那種類型的需求，然後調用對應的方法繪制
        switch (this) {
            case TYPE_CIRCULAR:
                bm = drawTypeFlow();
                break;
            case TYPE_POINT:
                bm = drawTypeMemory();
                break;
            case TYPE_NODE:
                bm = drawTypeStorage();
                break;
        }
        return bm;
    }

    //初始化操作
    private void init() {
        int circleWidth = Auxiliary_Tool.dip2px(context, 2);
        width = height = Auxiliary_Tool.dip2px(context, 80);
        mRectF = new RectF(circleWidth, circleWidth, width - circleWidth, height - circleWidth);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(circleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        int c =  Color.argb(100, 255, 255, 255);
        mPaint.setColor(c);
        mPaint.setFilterBitmap(false);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    //繪制剩余流量
    private Bitmap drawTypeFlow() {
        // 繪制圓圈，進度條背景
        canvas.drawArc(mRectF, 0, 360, false, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(mRectF, 270, mProcess * 360, false, mPaint);
        drawText(canvas);
        return bitmap;
    }


    //繪制內存情況
    private Bitmap drawTypeMemory() {
        mPaint.setStrokeWidth(Auxiliary_Tool.dip2px(context, 4));
        float dashWidth = Auxiliary_Tool.dip2px(context, 3) - 0.5f;//因為DensityUtil工具在轉換的時候多加了0.5像素導致出現刻度
        int totalCount = (int) Math.ceil(310 / dashWidth);//算出需要繪制的個數
        DashPathEffect dash = new DashPathEffect(new float[]{Auxiliary_Tool.dip2px(context, 1) - 0.5f, Auxiliary_Tool.dip2px(context, 2) - 0.5f}, 0);
        mPaint.setPathEffect(dash);
        float drawLength = (float) (Math.ceil(mProcess * totalCount) * dashWidth);//剩余部分
        canvas.drawArc(mRectF, 115, totalCount * dashWidth - drawLength, false, mPaint);
        mPaint.setColor(Color.parseColor("#00fe8f"));
        canvas.drawArc(mRectF, 115 + totalCount * dashWidth - drawLength, drawLength, false, mPaint);
        drawText(canvas);
        return bitmap;
    }

    //繪制存儲情況
    private Bitmap drawTypeStorage() {
        //每一份的寬度，總共分了8份
        double v = mRectF.width() * Math.PI / 8;
        DashPathEffect dash = new DashPathEffect(new float[]{(float) (v - Auxiliary_Tool.dip2px(context, 1)), Auxiliary_Tool.dip2px(context, 1)}, 0);
        mPaint.setPathEffect(dash);
        canvas.drawArc(mRectF, 270, 360, false, mPaint);
        mPaint.setColor(Color.parseColor("#acfa15"));
        canvas.drawArc(mRectF, 270, 360 * mProcess, false, mPaint);
        drawText(canvas);
        return bitmap;
    }

    /**
     * 因為要繪制兩遍，而兩遍的文字不一樣大，所以需要測量兩遍字體的高度
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        int c = Color.BLACK ;
        mPaint.setColor(c);
        mPaint.setStyle(Paint.Style.FILL);
        overRun();//判斷是否超限
        //上面的字體高度
        mPaint.setTextSize(Auxiliary_Tool.dip2px(context, 20));
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        int textWidth = (int) mPaint.measureText(mInfo, 0, mInfo.length());
        //下面的字體高度
        mPaint.setTextSize(Auxiliary_Tool.dip2px(context, 12));
        Paint.FontMetrics fontMetrics1 = mPaint.getFontMetrics();
        int textWidth1 = (int) mPaint.measureText(unit, 0, unit.length());
        //繪制數字
        float theY = mRectF.centerY() - fontMetrics.descent + (fontMetrics.bottom - fontMetrics.top) / 2 - (fontMetrics1.descent - fontMetrics1.ascent) / 2;
        mPaint.setTextSize(Auxiliary_Tool.dip2px(context, 20));
        canvas.drawText(mInfo, width / 2 - textWidth / 2, theY, mPaint);
        //繪制單位
        mPaint.setTextSize(Auxiliary_Tool.dip2px(context, 12));
        float newY = theY + Auxiliary_Tool.dip2px(context, 4) + fontMetrics.bottom - fontMetrics1.descent + (fontMetrics1.descent - fontMetrics1.ascent) / 2;
        canvas.drawText(unit, width / 2 - textWidth1 / 2, newY, mPaint);
    }

    //超限的情況
    private void overRun() {
        switch (this) {
            case TYPE_NODE:
                if ((unit.equalsIgnoreCase("M") && Float.parseFloat(mInfo) < 200) || unit.equalsIgnoreCase("K") || unit.equalsIgnoreCase("B"))
                    mPaint.setColor(Color.parseColor("#ff840b"));
                break;
            case TYPE_POINT:
                if ((unit.equalsIgnoreCase("M") && Float.parseFloat(mInfo) < 100) || unit.equalsIgnoreCase("K") || unit.equalsIgnoreCase("B"))
                    mPaint.setColor(Color.parseColor("#ff840b"));
                break;
            case TYPE_CIRCULAR://流量超限
                break;
        }
    }
}
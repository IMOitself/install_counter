package imo.install_counter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BarGraphView
{
    static final int BAR_SPACING = 25;
    static ViewGroup create (final Context mContext) {
        
        final LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout () {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    
                    int padding = (int) layout.getWidth() / 60;
                    layout.setPadding(padding, padding, padding, padding);
                    layout.setBackground(drawCanvas(layout, layout.getWidth(), layout.getHeight(), padding));
                }
            });
        
        layout.addView(createBar(mContext, "hello"));
        layout.addView(createBar(mContext, "world"));
        return layout;
    }
    
    static BitmapDrawable drawCanvas(View view, int width, int height, int padding){
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        float[] line1 = {padding, padding, padding, canvasHeight - padding};
        canvas.drawLine(line1[0], line1[1], line1[2], line1[3], paint);
        float[] line2 = {padding, canvasHeight - padding, canvasWidth - padding, canvasHeight - padding};
        canvas.drawLine(line2[0], line2[1], line2[2], line2[3], paint);
        return new BitmapDrawable(view.getResources(), bitmap);
    }
    
    static TextView createBar(Context mContext, String text){
        TextView textview = new TextView(mContext);
        textview.setText(text);
        textview.setBackgroundColor(Color.BLACK);
        textview.setTextColor(Color.WHITE);
        return textview;
    }
}

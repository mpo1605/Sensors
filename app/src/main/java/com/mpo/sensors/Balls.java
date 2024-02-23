package com.mpo.sensors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Balls extends View {

    public static int Vx;
    public static int Vy;
    int Coord_x, Coord_y;
    boolean vhod = true;
    public Balls(Context context) {
        super(context);


    }

    @Override
    public void draw (Canvas canvas) {
        super.draw(canvas);
        if (vhod) {
            Coord_x = (int) canvas.getWidth()/2;
            Coord_y = (int) canvas.getHeight()/2;
            vhod = false;
        }
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        float w = (int) canvas.getWidth();
        float h = (int) canvas.getHeight();

        if (!((Coord_y+Vy <= 105) || ((Coord_y+Vy >= h-105)))) {
            Coord_y+=Vy;
        }
        if (!((Coord_x+Vx <= 105) || ((Coord_x+Vx >= w-105)))) {
            Coord_x+=Vx;
        }
        canvas.drawCircle(Coord_x, Coord_y, 100, paint);
        invalidate();
    }
}

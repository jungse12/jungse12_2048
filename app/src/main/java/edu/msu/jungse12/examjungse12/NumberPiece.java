package edu.msu.jungse12.examjungse12;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class NumberPiece {
    //private Bitmap piece;

    /**
     * x location.
     * We use relative x locations in the range 0-1 for the center
     * of the number piece.
     */
    private float x = 0;

    /**
     * y location
     */
    private float y = 0;


    private int number;

    private Paint box_color;

    private Paint text_color;

    public NumberPiece(Context context, int number, float x, float y, Paint box_color) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.box_color = box_color;

        text_color = new Paint();
        text_color.setColor(0xFF808080);
        text_color.setTextSize(100);
        text_color.setTextAlign(Paint.Align.CENTER);

        float textHeight = text_color.descent() - text_color.ascent();
        float textOffset = (textHeight / 2) - text_color.descent();
        //piece = BitmapFactory.decodeResource(context.getResources(), id);
    }

    /**
     * Draw the puzzle piece
     * @param canvas Canvas we are drawing on
     * @param marginX Margin x value in pixels
     * @param marginY Margin y value in pixels
     */
    public void draw(Canvas canvas, float marginX, float marginY, float finalX, float finalY, int gameSize, float scaleFactor) {
        canvas.save();

        canvas.translate(x * gameSize, y * gameSize);
        text_color.setTextSize(100);
        text_color.setTextAlign(Paint.Align.CENTER);

        float textHeight = text_color.descent() - text_color.ascent();
        float textOffset = (textHeight / 2) - text_color.descent();

        canvas.drawRect(marginX, marginY, finalX, finalY, box_color);
        if (this.number != 0) {
            text_color.setColor(0xFF808080);
        } else {
            text_color.setColor(0x00000000);
        }
        canvas.drawText(String.valueOf(number), (float) (marginX + finalX) / 2, (float) (marginY + finalY) / 2 + textOffset, text_color);
        canvas.restore();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setFirst_color(Paint first_color) {
        this.box_color = first_color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

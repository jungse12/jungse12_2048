package edu.msu.jungse12.examjungse12;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.service.quicksettings.Tile;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.NavUtils;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    /**
     * Percentage of the display width or height that
     * is occupied by the game.
     */
    final static float SCALE_IN_VIEW = 0.9f;

    /**
     * Paint for filling the area the game is in
     */
    private Paint fillPaint;

    private Paint initial_box_color;

    private Paint box_color_2;

    private Paint box_color_4;

    private Paint box_color_8;

    private Paint box_color_16;

    private Paint box_color_32;

    private Paint box_color_64;

    private Paint box_color_128;

    private Paint box_color_256;

    private Paint box_color_512;

    private Paint box_color_1024;

    private Paint box_color_2048;
    /**
     * Paint for outlining the area the game is in
     */
    private Paint outlinePaint;

    private boolean first_time = true;

    public ArrayList<NumberPiece> pieces =  new ArrayList<NumberPiece>();

    public int score = 0;


    public Game(Context context) {
        // Create paint for filling the area the game will
        // be solved in.
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(0xffcccccc);

        initial_box_color = new Paint(Paint.ANTI_ALIAS_FLAG);
        initial_box_color.setColor(0x00000000);

        box_color_2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_2.setColor(0xFFF0FCEF);

        box_color_4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_4.setColor(0xFFF8FEE4);

        box_color_8 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_8.setColor(0xFFD8E6CF);

        box_color_16 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_16.setColor(0xFFF8FFD9);

        box_color_32 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_32.setColor(0xFFE8E6C5);

        box_color_64 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_64.setColor(0xFFFEFAE4);

        box_color_128 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_128.setColor(0xFFE8DFC5);

        box_color_256 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_256.setColor(0xFFFFF2D9);

        box_color_512 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_512.setColor(0xFFFED9C8);

        box_color_1024 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_1024.setColor(0xFFE8B1A9);

        box_color_2048 = new Paint(Paint.ANTI_ALIAS_FLAG);
        box_color_2048.setColor(0xFFFFBDD0);

        float initialX = 0;
        float initialY = 0;

        for (int i = 0; i < 16; i++) {
            if (initialX == 1.00) {
                initialX = 0;
                initialY += 0.25;
            }
            pieces.add(new NumberPiece(context, 0, initialX, initialY, initial_box_color));
            initialX +=  0.25;
        }
    }

    public void draw(Canvas canvas) {
        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        // Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        int gameSize = (int)(minDim * SCALE_IN_VIEW);

        // Compute the margins so we center the game
        int marginX = (wid - gameSize) / 2;
        int marginY = (hit - gameSize) / 2;

        int rectWidth = gameSize / 4;

        float scaleFactor = (float)rectWidth / (float)gameSize;

        canvas.drawRect(marginX, marginY,
                marginX + gameSize, marginY + gameSize, fillPaint);

        canvas.save();
        canvas.translate(marginX, marginY);
        canvas.scale(scaleFactor, scaleFactor);
        canvas.restore();

        if (first_time == true) {
            createRandomPiece();
            createRandomPiece();
        }

        for (NumberPiece piece : pieces) {
            if (piece.getNumber() == 2) {
                piece.setFirst_color(box_color_2);
            } else if (piece.getNumber() == 4) {
                piece.setFirst_color(box_color_4);
            } else if (piece.getNumber() == 8) {
                piece.setFirst_color(box_color_8);
            } else if (piece.getNumber() == 16) {
                piece.setFirst_color(box_color_16);
            } else if (piece.getNumber() == 32) {
                piece.setFirst_color(box_color_32);
            } else if (piece.getNumber() == 64) {
                piece.setFirst_color(box_color_64);
            } else if (piece.getNumber() == 128) {
                piece.setFirst_color(box_color_128);
            } else if (piece.getNumber() == 256) {
                piece.setFirst_color(box_color_256);
            } else if (piece.getNumber() == 512) {
                piece.setFirst_color(box_color_512);
            } else if (piece.getNumber() == 1024) {
                piece.setFirst_color(box_color_1024);
            } else if (piece.getNumber() == 2048) {
                piece.setFirst_color(box_color_2048);
            } else {
                piece.setFirst_color(initial_box_color);
            }
            piece.draw(canvas, marginX + piece.getX(), marginY + piece.getY(), marginX + piece.getX() + rectWidth, marginY + piece.getY() + rectWidth, gameSize, scaleFactor);
        }

        first_time = false;
    }

    private float randomNumberX() {
        float xRandom = (float)Math.random();

        if (xRandom < (float)0.25){
            xRandom = 0;
        } else if ((float)0.25 <= xRandom && xRandom < (float)0.5){
            xRandom = (float)0.25;
        } else if ((float)0.5 <= xRandom && xRandom < (float)0.75){
            xRandom = (float)0.50;
        } else if ((float)0.75 <= xRandom && xRandom < (float)1.00){
            xRandom = (float)0.75;
        }
        return xRandom;
    }

    private float randomNumberY() {
        float yRandom = (float)Math.random();

        if (yRandom < 0.25){
            yRandom = 0;
        } else if ((float)0.25 <= yRandom && yRandom < (float)0.5){
            yRandom = (float)0.25;
        } else if ((float)0.5 <= yRandom && yRandom < (float)0.75){
            yRandom = (float)0.50;
        } else if ((float)0.75 <= yRandom && yRandom < (float)1.00){
            yRandom = (float)0.75;
        }
        return yRandom;
    }

    public void createRandomPiece() {
        float randomX = randomNumberX();
        float randomY = randomNumberY();

        while (true) {
            if (validNumber(randomX, randomY) == true) {
                for (NumberPiece piece : pieces) {
                    if (piece.getX() == randomX && piece.getY() == randomY) {
                        piece.setNumber(2);
                    }
                }
                break;
            } else {
                randomX = randomNumberX();
                randomY = randomNumberY();
            }
        }
    }

    private boolean validNumber(float x, float y) {
        for (NumberPiece piece : pieces) {
            float pieceX = piece.getX();
            float pieceY = piece.getY();
            float pieceNum = piece.getNumber();

            //System.out.println("X:" + pieceX);
            //System.out.println("random x:" + x);
            if (x == pieceX && y == pieceY && pieceNum != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Handle a touch event from the view.
     * @param view The view that is the source of the touch
     * @param event The motion event describing the touch
     * @return true if the touch is handled.
     */
    public boolean onTouchEvent(View view, MotionEvent event) {
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;

            case MotionEvent.ACTION_MOVE:
                break;
        }

        return false;
    }

    public boolean turnMove(String fling) {
        boolean joinResult = false;
        boolean moveResult = false;

        int rangeX = 0;
        if (fling == "BOTTOM") {
            for (int i = 15; i >= 0; i--) {
                if (pieces.get(i).getNumber() != 0) {
                    for (int j = i - 4; j >= 0; j -= 4) {
                        if (pieces.get(j).getNumber() > pieces.get(i).getNumber()) {
                            break;
                        }
                        if (pieces.get(j).getNumber() == pieces.get(i).getNumber()) {
                            pieces.get(j).setNumber(0);
                            pieces.get(i).setNumber(pieces.get(i).getNumber() * 2);
                            currentScore(pieces.get(i).getNumber());
                            joinResult = true;
                            break;
                        }
                    }
                    if (sortTile(i, fling, rangeX) == true) {
                        moveResult = true;
                    }
                }
            }
        }
        if (fling == "RIGHT") {
            for (int i = 15; i >= 0; i --) {
                if (pieces.get(i).getNumber() != 0) {
                    if (i < 16 && i >= 12) {
                        rangeX = 12;
                    } else if (i < 12 && i >= 8) {
                        rangeX = 8;
                    }else if (i < 8 && i >= 4) {
                        rangeX = 4;
                    }else if (i < 4 && i >= 0) {
                        rangeX = 0;
                    }
                    for (int j = i - 1; j >= rangeX; j --) {
                        if (pieces.get(j).getNumber() > pieces.get(i).getNumber()) {
                            break;
                        }
                        if (pieces.get(j).getNumber() == pieces.get(i).getNumber()) {
                            pieces.get(j).setNumber(0);
                            pieces.get(i).setNumber(pieces.get(i).getNumber() * 2);
                            currentScore(pieces.get(i).getNumber());
                            joinResult = true;
                            break;
                        }
                    }
                    if (sortTile(i, fling, rangeX) == true) {
                        moveResult = true;
                    }
                }
            }
        }
        if (fling == "TOP") {
            for (int i = 0; i < 16; i++) {
                if (pieces.get(i).getNumber() != 0) {
                    for (int j = i + 4; j < 16; j += 4) {
                        if (pieces.get(j).getNumber() > pieces.get(i).getNumber()) {
                            break;
                        }
                        if (pieces.get(j).getNumber() == pieces.get(i).getNumber()) {
                            pieces.get(j).setNumber(0);
                            pieces.get(i).setNumber(pieces.get(i).getNumber() * 2);
                            currentScore(pieces.get(i).getNumber());
                            joinResult = true;
                            break;
                        }
                    }
                    if (sortTile(i, fling, rangeX) == true) {
                        moveResult = true;
                    }
                }
            }
        }
        if (fling == "LEFT") {
            for (int i = 0; i < 16; i++) {
                if (pieces.get(i).getNumber() != 0) {
                    if (i < 16 && i >= 12) {
                        rangeX = 12;
                    } else if (i < 12 && i >= 8) {
                        rangeX = 8;
                    }else if (i < 8 && i >= 4) {
                        rangeX = 4;
                    }else if (i < 4 && i >= 0) {
                        rangeX = 0;
                    }
                    for (int j = i + 1; j < rangeX + 4; j ++) {
                        if (pieces.get(j).getNumber() > pieces.get(i).getNumber()) {
                            break;
                        }
                        if (pieces.get(j).getNumber() == pieces.get(i).getNumber()) {
                            pieces.get(j).setNumber(0);
                            pieces.get(i).setNumber(pieces.get(i).getNumber() * 2);
                            currentScore(pieces.get(i).getNumber());
                            joinResult = true;
                            break;
                        }
                    }
                    if (sortTile(i, fling, rangeX) == true) {
                        moveResult = true;
                    }
                }
            }
        }

        if (moveResult == false && joinResult == false) {
            return false;
        } else {
            return true;
        }
    }

    public boolean sortTile(int index, String fling, int rangeX) {
        boolean moveResult = false;

        if (fling == "BOTTOM") {
            for (int i = index + 4; i < 16; i += 4) {
                if (pieces.get(i).getNumber() == 0) {
                    pieces.get(i).setNumber(pieces.get(index).getNumber());
                    pieces.get(index).setNumber(0);
                    index = i;
                    moveResult = true;
                }
            }
        }
        if (fling == "RIGHT") {
            for (int i = index + 1; i < rangeX + 4; i ++) {
                if (pieces.get(i).getNumber() == 0) {
                    pieces.get(i).setNumber(pieces.get(index).getNumber());
                    pieces.get(index).setNumber(0);
                    index = i;
                    System.out.println(moveResult);
                    moveResult = true;
                }
            }
        }
        if (fling == "TOP") {
            for (int i = index - 4; i >= 0; i -= 4) {
                if (pieces.get(i).getNumber() == 0) {
                    pieces.get(i).setNumber(pieces.get(index).getNumber());
                    pieces.get(index).setNumber(0);
                    index = i;
                    moveResult = true;
                }
            }
        }
        if (fling == "LEFT") {
            for (int i = index - 1; i >= rangeX; i --) {
                if (pieces.get(i).getNumber() == 0) {
                    pieces.get(i).setNumber(pieces.get(index).getNumber());
                    pieces.get(index).setNumber(0);
                    index = i;
                    moveResult = true;
                }
            }
        }
        return moveResult;
    }

    public int currentScore(int number) {
        score += number;
        return score;
    }
}

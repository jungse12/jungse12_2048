package edu.msu.jungse12.examjungse12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    TextView scoreText;
    private GestureDetectorCompat gestureDector;

    private GameView getGameView() {
        return (GameView) this.findViewById(R.id.gameView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDector = new GestureDetectorCompat(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();

        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > 100 && Math.abs(velocityX) > 100) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
        } else {
            if (Math.abs(diffY) > 100 && Math.abs(velocityY) > 100) {
                if (diffY > 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
                result = true;
            }
        }

        return result;
    }

    private void onSwipeTop() {
        //getGameView().getGame().turnMove("TOP");
        if (getGameView().getGame().turnMove("TOP") == true) {
            getGameView().getGame().createRandomPiece();
        } else {
            if (gameOver() == true) {
                Toast ("Game Over");
            }
        }
        score(getGameView().getGame().score);
        getGameView().invalidate();
        System.out.println("TOP");
    }

    private void onSwipeBottom() {
        //getGameView().invalidate();
        //getGameView().getGame().turnMove("BOTTOM");
        if (getGameView().getGame().turnMove("BOTTOM") == true) {
            getGameView().getGame().createRandomPiece();
        } else {
            if (gameOver() == true) {
                Toast ("Game Over");
            }
        }
        score(getGameView().getGame().score);
        getGameView().invalidate();
        System.out.println("BOTTOM");
    }

    private void onSwipeLeft() {
        //getGameView().getGame().turnMove("LEFT");
        if (getGameView().getGame().turnMove("LEFT") == true) {
            getGameView().getGame().createRandomPiece();
        } else {
            if (gameOver() == true) {
                Toast ("Game Over");
            }
        }
        score(getGameView().getGame().score);
        getGameView().invalidate();
        System.out.println("LEFT");
    }

    private void onSwipeRight() {
        //getGameView().getGame().turnMove("RIGHT");
        if (getGameView().getGame().turnMove("RIGHT") == true) {
            getGameView().getGame().createRandomPiece();
        } else {
            if (gameOver() == true) {
                Toast ("Game Over");
            }
        }
        score(getGameView().getGame().score);
        getGameView().invalidate();
        System.out.println("RIGHT");
    }

    public boolean gameOver() {
        int count = 0;
        for (NumberPiece piece : getGameView().getGame().pieces) {
            if (piece.getNumber() > 0) {
                count += 1;
            }
        }
        if (count == 16) {
            return true;
        } else {
            return false;
        }
    }

    public void Toast (String warning) {
        Context context = getApplicationContext();
        int timer = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, warning, timer);
        toast.show();
    }
    public void onNewGame(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void score(int number) {
        scoreText = findViewById(R.id.score_point);
        scoreText.setText(String.valueOf(number));
    }
}

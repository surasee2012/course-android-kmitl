package kmitl.lab05.surasee2012.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab05.surasee2012.simplemydot.model.Dot;
import kmitl.lab05.surasee2012.simplemydot.model.Dots;


public class DotView extends View implements View.OnLongClickListener{

    private Paint paint;
    private Dots allDot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.allDot != null){
            for (Dot dot : allDot.getAllDot()){
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(),
                        dot.getCenterY(),
                        dot.getRadius(), paint);
            }

        }

    }
    private OnDotViewTouchListener listener;
    public interface OnDotViewTouchListener {
        void onDotViewPress(int x, int y);
        void onDotViewLongPress(int x, int y);
    }

    public OnDotViewTouchListener getListener() {
        return listener;
    }

    public void setListener(OnDotViewTouchListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            listener.onDotViewPress(
                    (int)e.getX(),
                    (int)e.getY());
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            listener.onDotViewLongPress(
                    (int)e.getX(),
                    (int)e.getY());
            super.onLongPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    });
    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDots(Dots dots) {
        this.allDot = dots;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}

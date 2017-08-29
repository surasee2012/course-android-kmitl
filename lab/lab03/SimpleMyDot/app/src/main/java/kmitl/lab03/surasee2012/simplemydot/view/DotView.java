package kmitl.lab03.surasee2012.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;
import java.util.Random;

import kmitl.lab03.surasee2012.simplemydot.model.Dot;

/**
 * Created by Gun on 8/25/2017.
 */

public class DotView extends View {
    private Paint paint;
    private List<Dot> listDot;
    private List<Integer> listColor;

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.listDot != null && this.listColor != null) {
            for (int i=0;i<this.listDot.size();i++) {
                if (listColor.get(i) == 0) {
                    paint.setColor(Color.RED);
                } else if (listColor.get(i) == 1) {
                    paint.setColor(Color.BLUE);
                } else if (listColor.get(i) == 2) {
                    paint.setColor(Color.GREEN);
                } else if (listColor.get(i) == 3) {
                    paint.setColor(Color.YELLOW);
                } else if (listColor.get(i) == 4) {
                    paint.setColor(Color.MAGENTA);
                } else if (listColor.get(i) == 5) {
                    paint.setColor(Color.CYAN);
                }
                canvas.drawCircle(this.listDot.get(i).getCenterX(), this.listDot.get(i).getCenterY(), this.listDot.get(i).getRadius(), paint);
            }
        }
    }

    public void setDot(List<Dot> listDot, List<Integer> listColor) {
        this.listDot = listDot;
        this.listColor = listColor;
    }

}

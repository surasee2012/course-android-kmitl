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
import kmitl.lab03.surasee2012.simplemydot.model.DotGroup;

/**
 * Created by Gun on 8/25/2017.
 */

public class DotView extends View {
    private Paint paint;
    private DotGroup dotGroup;

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
        if (this.dotGroup != null) {
            for (int i=0;i<this.dotGroup.getDotList().size();i++) {
                if (this.dotGroup.getColorList().get(i) == 0) {
                    paint.setColor(Color.RED);
                } else if (this.dotGroup.getColorList().get(i) == 1) {
                    paint.setColor(Color.BLUE);
                } else if (this.dotGroup.getColorList().get(i) == 2) {
                    paint.setColor(Color.GREEN);
                } else if (this.dotGroup.getColorList().get(i) == 3) {
                    paint.setColor(Color.YELLOW);
                } else if (this.dotGroup.getColorList().get(i) == 4) {
                    paint.setColor(Color.MAGENTA);
                } else if (this.dotGroup.getColorList().get(i) == 5) {
                    paint.setColor(Color.CYAN);
                }
                canvas.drawCircle(this.dotGroup.getDotList().get(i).getCenterX(),
                        this.dotGroup.getDotList().get(i).getCenterY(),
                        this.dotGroup.getDotList().get(i).getRadius(), paint);
            }
        }
    }

    public void setDot(DotGroup dotGroup) {
        this.dotGroup = dotGroup;
    }

}

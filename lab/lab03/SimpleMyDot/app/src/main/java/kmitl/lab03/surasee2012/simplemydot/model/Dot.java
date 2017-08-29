package kmitl.lab03.surasee2012.simplemydot.model;

import java.util.List;

/**
 * Created by Gun on 8/25/2017.
 */

public class Dot {

    private OnDotChangedListener listener;

    private int centerX;
    private int centerY;
    private int radius;

    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener.onDotChanged(this);
    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public OnDotChangedListener getListener() {
        return listener;
    }

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }

    public interface OnDotChangedListener {
        void onDotChanged(Dot dot);
    }
}

package kmitl.lab03.surasee2012.simplemydot.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gun on 8/29/2017.
 */

public class DotGroup {


    private OnDotGroupChangedListener listener;

    private List<Dot> dotList;
    private List<Integer> colorList;

    public DotGroup(OnDotGroupChangedListener listener, List<Dot> dotList, List<Integer> colorList) {
        this.dotList = dotList;
        this.colorList = colorList;
        this.listener = listener;
    }

    public DotGroup(OnDotGroupChangedListener listener) {
        this.dotList = new ArrayList<>();
        this.colorList = new ArrayList<>();
        this.listener = listener;
    }

    public List<Dot> getDotList() {
        return dotList;
    }

    public void setDotList(List<Dot> dotList) {
        this.dotList = dotList;
    }

    public List<Integer> getColorList() {
        return colorList;
    }

    public void setColorList(List<Integer> colorList) {
        this.colorList = colorList;
    }

    public void add(Dot dot, Integer colorNum) {
        dotList.add(dot);
        colorList.add(colorNum);
        this.listener.onDotGroupChanged(this);
    }

    public void clear() {
        dotList.clear();
        colorList.clear();
        this.listener.onDotGroupChanged(this);
    }

    public OnDotGroupChangedListener getListener() {
        return listener;
    }

    public void setListener(OnDotGroupChangedListener listener) {
        this.listener = listener;
    }

    public interface OnDotGroupChangedListener {
        void onDotGroupChanged(DotGroup dotGroup);
    }
}

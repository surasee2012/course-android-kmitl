package kmitl.lab03.surasee2012.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kmitl.lab03.surasee2012.simplemydot.model.Dot;
import kmitl.lab03.surasee2012.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private DotView dotView;
    private Dot dot;
    private List<Dot> listDot;
    private List<Integer> listColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dot = new Dot(this,0,0,30);
        listDot = new ArrayList<>();
        listColor =  new ArrayList<>();
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(listDot, listColor);
        dotView.invalidate();
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int color = random.nextInt(6);
        listColor.add(color);
        int centerX = random.nextInt(1400);
        int centerY = random.nextInt(1800);
        int radius = random.nextInt(100);
        dot = new Dot(this,centerX,centerY,radius+30);
        listDot.add(dot);
    }

    public void onClearDot(View view) {
        listDot.clear();
        dotView.setDot(listDot, listColor);
        dotView.invalidate();
    }

}

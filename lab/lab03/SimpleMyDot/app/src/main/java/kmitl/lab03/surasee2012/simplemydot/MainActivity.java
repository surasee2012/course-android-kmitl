package kmitl.lab03.surasee2012.simplemydot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kmitl.lab03.surasee2012.simplemydot.model.Dot;
import kmitl.lab03.surasee2012.simplemydot.model.DotGroup;
import kmitl.lab03.surasee2012.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements DotGroup.OnDotGroupChangedListener {

    private DotView dotView;
    private DotGroup dotGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dotGroup = new DotGroup(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int colorNum = random.nextInt(6);
        int centerX = random.nextInt(1400);
        int centerY = random.nextInt(2000);
        int radius = random.nextInt(100);
        Dot dot = new Dot(centerX, centerY, radius+30);
        dotGroup.add(dot, colorNum);
    }

    public void onClearDot(View view) {
        dotGroup.clear();
    }

    @Override
    public void onDotGroupChanged(DotGroup dotGroup) {
        dotView.setDot(this.dotGroup);
        dotView.invalidate();
    }
}

package kmitl.lab04.surasee2012.simplemydot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import kmitl.lab04.surasee2012.simplemydot.model.Dot;
import kmitl.lab04.surasee2012.simplemydot.model.DotGroup;
import kmitl.lab04.surasee2012.simplemydot.model.Screenshot;
import kmitl.lab04.surasee2012.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements DotGroup.OnDotGroupChangedListener,
        DotView.OnDotViewPressListener {

    private DotView dotView;
    private DotGroup dotGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dotGroup = new DotGroup(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int colorNum = random.nextInt(6);
        int centerX = random.nextInt(1400);
        int centerY = random.nextInt(2000);
        int radius = random.nextInt(100);
        Dot dot = new Dot(centerX, centerY, radius + 30);
        dotGroup.add(dot, colorNum);
    }

    public void onClearDot(View view) {
        dotGroup.clear();
    }

    public void onUndo(View view) {
        dotGroup.undo();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dotGroup.findDot(x, y);
        if(dotPosition == -1) {
            Random random = new Random();
            int colorNum = random.nextInt(6);
            int radius = random.nextInt(100);
            Dot newDot = new Dot(x, y, radius + 30);
            dotGroup.add(newDot, colorNum);
        } else {
            dotGroup.removeBy(dotPosition);
        }
    }

    @Override
    public void onDotGroupChanged(DotGroup dotGroup) {
        dotView.setDot(this.dotGroup);
        dotView.invalidate();
    }

    public void onShare(View view) {
        View main;
        main = findViewById(R.id.main);
        Bitmap b = Screenshot.takescreenshotOfRottView(main);
        saveBitmap(b);
        File imagePath = new File(this.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(this, "kmitl.lab03.surasee2012.simplemydot.fileprovider", newFile);
        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }
    }

    private void saveBitmap(Bitmap bitmap) {
        // save bitmap to cache directory
        try {
            File cachePath = new File(this.getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

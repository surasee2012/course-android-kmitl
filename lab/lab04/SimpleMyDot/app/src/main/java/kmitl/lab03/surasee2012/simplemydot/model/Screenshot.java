package kmitl.lab03.surasee2012.simplemydot.model;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by Gun on 9/13/2017.
 */

public class Screenshot {

    public static Bitmap takescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takescreenshotOfRottView(View v){
        return takescreenshot(v.getRootView());
    }

}

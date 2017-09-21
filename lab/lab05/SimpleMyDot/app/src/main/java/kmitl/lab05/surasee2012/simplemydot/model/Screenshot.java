package kmitl.lab05.surasee2012.simplemydot.model;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by Gun on 12/9/2560.
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

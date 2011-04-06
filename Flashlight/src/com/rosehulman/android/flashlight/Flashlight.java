package com.rosehulman.android.flashlight;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class Flashlight extends Activity {
    
    private boolean mFullBright = true;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setBrightness();
    }
    
    private void setBrightness() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = (float) (mFullBright ? 1.0 : -1.0); // or WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL : WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE 
                                                                   // on 2.2 (API 8) or above
        getWindow().setAttributes(lp);
    }
}
package com.rosehulman.android.flashlight;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.WindowManager;

public class Flashlight extends Activity {
    
    private SharedPreferences mPrefs;
    private boolean mFullBright;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mPrefs = getPreferences(MODE_PRIVATE);
        mFullBright = mPrefs.getBoolean(getString(R.string.pref_key_fullbright), true);
        setBrightness();
    }
    
    private void setBrightness() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = (float) (mFullBright ? 1.0 : -1.0); // or WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL : WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE 
                                                                   // on 2.2 (API 8) or above
        getWindow().setAttributes(lp);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
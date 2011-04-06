package com.rosehulman.android.flashlight;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class Flashlight extends Activity {
    
    private SharedPreferences mPrefs;
    private boolean mFullBright;
    private String mColorKey;
    
    private View mLightView;
    
    private Map<String, Integer> mColors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mLightView = findViewById(R.id.flashlight_light);
        
        mColors = new HashMap<String, Integer>();
        mColors.put(getString(R.string.pref_key_color_white), R.color.white);
        mColors.put(getString(R.string.pref_key_color_red), R.color.red);
        mColors.put(getString(R.string.pref_key_color_green), R.color.green);
        mColors.put(getString(R.string.pref_key_color_blue), R.color.blue);
        
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateBrightness();
        updateColor();
    }
    
    private void updateBrightness() {
        mFullBright = mPrefs.getBoolean(getString(R.string.pref_key_fullbright), true);
        
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = (float) (mFullBright ? 1.0 : -1.0); // or WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL : WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE 
                                                                   // on 2.2 (API 8) or above
        getWindow().setAttributes(lp);
    }
    
    private void updateColor() {
        mColorKey = mPrefs.getString(getString(R.string.pref_key_color), getString(R.string.pref_key_color_white));
        mLightView.setBackgroundResource(mColors.get(mColorKey));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.flashlight_menu_settings) {
            startActivity(new Intent(this, FlashlightPreferences.class));
            return true;
        }
        return false;
    }
}
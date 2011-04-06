package com.rosehulman.android.flashlight;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class FlashlightPreferences extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}

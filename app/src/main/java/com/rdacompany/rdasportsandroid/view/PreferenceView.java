package com.rdacompany.rdasportsandroid.view;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.rdacompany.rdasportsandroid.R;


public class PreferenceView extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}

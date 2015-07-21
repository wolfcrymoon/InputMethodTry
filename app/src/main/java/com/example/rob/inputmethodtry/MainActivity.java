package com.example.rob.inputmethodtry;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private InputMethodManager inputMethodManager;

    private Spinner spinner;
    private ArrayAdapter<String> languageArray;
    private ListView list;
    private List<String> resultList;

    private boolean is_zh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        setContentView(R.layout.activity_main);
//        context = this.getApplication();
//        spinner = (Spinner) findViewById(R.id.spinner);
//        languageArray = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item)
//        spinner.setAdapter();
//        list = (ListView) findViewById(R.id.listView);
//        languageArray

//        List<InputMethodInfo> imiList = inputMethodManager.getEnabledInputMethodList();
        List<InputMethodInfo> imiList = inputMethodManager.getInputMethodList();

        for (InputMethodInfo imi : imiList) {
            if (imi == null) { continue; }
            Log.v(TAG, "imi " + imi.getPackageName() + " " + imi.getServiceName());
            List<InputMethodSubtype> imsList = inputMethodManager.getEnabledInputMethodSubtypeList(imi, true);
            for (InputMethodSubtype ims : imsList) {
                if (ims == null) { continue; }
                Log.v(TAG, "--> ims.getLocale() " + ims.getLocale());
            }
        }

//        for (InputMethodInfo imi : allImiList) {
//            Log.v(TAG, "all imi " + (imi != null ? imi.getPackageName() + " " + imi.getServiceName() : null));
//        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodSubtype lastIms = inputMethodManager.getLastInputMethodSubtype();
                InputMethodSubtype currentIms = inputMethodManager.getCurrentInputMethodSubtype();

                Log.v(TAG, "lastIms.getLocale() " + (lastIms != null ? lastIms.getLocale() : null));
                Log.v(TAG, "currentIms.getLocale() " + (currentIms != null ? currentIms.getLocale() : null));

                new Handler().postDelayed(this, 3000);
            }
        }, 1000);

        InputMethodSubtype.InputMethodSubtypeBuilder builder = new InputMethodSubtype.InputMethodSubtypeBuilder();
        final InputMethodSubtype zh_TW = builder.setSubtypeLocale("zh_TW").build();
        final InputMethodSubtype en_US = builder.setSubtypeLocale("en_US").build();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (is_zh) {
                    inputMethodManager.setCurrentInputMethodSubtype(en_US);
                    is_zh = false;
                } else {
                    inputMethodManager.setCurrentInputMethodSubtype(zh_TW);
                    is_zh = true;
                }

                new Handler().postDelayed(this, 3000);
            }
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

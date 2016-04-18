package com.drteam.truongpq.fragmentmanagerexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.drteam.truongpq.fragmentmanagerexample.fragments.AFragment;
import com.drteam.truongpq.fragmentmanagerexample.fragments.BFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        manager.addOnBackStackChangedListener(this);
    }

    public void addA(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, new AFragment(), "A");
        transaction.addToBackStack("addA");
        transaction.commit();
    }

    public void removeA(View view) {
        AFragment aFragment = (AFragment) manager.findFragmentByTag("A");
        FragmentTransaction transaction = manager.beginTransaction();
        if (aFragment != null) {
            transaction.remove(aFragment);
            transaction.addToBackStack("removeA");
            transaction.commit();
        } else {
            Toast.makeText(this, "The Fragment A was not added", Toast.LENGTH_SHORT).show();
        }
    }

    public void replaceAWithB(View view) {
        BFragment bFragment = new BFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, bFragment, "B");
        transaction.addToBackStack("replaceAWithB");
        transaction.commit();
    }

    public void addB(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, new BFragment(), "B");
        transaction.addToBackStack("addB");
        transaction.commit();
    }

    public void removeB(View view) {
        BFragment bFragment = (BFragment) manager.findFragmentByTag("B");
        FragmentTransaction transaction = manager.beginTransaction();
        if (bFragment != null) {
            transaction.remove(bFragment);
            transaction.addToBackStack("removeB");
            transaction.commit();
        } else {
            Toast.makeText(this, "The Fragment B was not added", Toast.LENGTH_SHORT).show();
        }
    }

    public void replaceBwithA(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, new AFragment(), "B");
        transaction.addToBackStack("replaceBwithA");
        transaction.commit();
    }

    @Override
    public void onBackStackChanged() {
        StringBuilder state = new StringBuilder();
        int count = manager.getBackStackEntryCount();
        for (int i = count - 1; i >= 0; i--) {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(i);
            state.append(entry.getName() + " - ");
        }
        Log.d("Status", state.toString());
    }
}

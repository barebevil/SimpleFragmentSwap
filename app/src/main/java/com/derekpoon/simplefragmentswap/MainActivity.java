package com.derekpoon.simplefragmentswap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
Fragment swapping with 1 activity
Buttons are defined in the activity and main acticity layout
 */

//Fragment transactions should be handled in the activity

public class MainActivity extends AppCompatActivity {

    FirstFragment firstFragment;
    SecondFragment secondFragment;
    private final String FRAG1_TAG = "Fragment 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if the fragment exists
        if (savedInstanceState != null) {
            // look up the instance that already exists by tag
//            firstFragment = (FirstFragment)
//                    getSupportFragmentManager().findFragmentByTag(FRAG1_TAG);

            //if fragment doesn't exist, example, launching the app for the first time
        } else if (firstFragment == null) {
            // create a new fragment
            firstFragment = new FirstFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, firstFragment, FRAG1_TAG);
            ft.commit();
        }

        Button mButtonFrag1 = findViewById(R.id.button_frag1);
        mButtonFrag1.setOnClickListener(btnHandler);

        Button mButtonFrag2 = findViewById(R.id.button_frag2);
        mButtonFrag2.setOnClickListener(btnHandler);

    }

    public void swapFragment(Fragment swapfragment) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, swapfragment);

        // Complete the changes added above
        ft.commit();
    }

    View.OnClickListener btnHandler = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_frag1:
                    firstFragment = new FirstFragment();
                    swapFragment(firstFragment);
                    Toast.makeText(MainActivity.this, "Displaying Fragment 1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_frag2:
                    // create a new fragment
                    secondFragment = new SecondFragment();
                    swapFragment(secondFragment);
                    Toast.makeText(MainActivity.this, "Displaying Fragment 2", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}

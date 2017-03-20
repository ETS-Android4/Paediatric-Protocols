package com.mwongera.paediatric_protocols.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mwongera.paediatric_protocols.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 3/5/17.
 */

public class TriageOfSickChildren extends Activity {

    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triage_of_sick_children);
        // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);

    }

}



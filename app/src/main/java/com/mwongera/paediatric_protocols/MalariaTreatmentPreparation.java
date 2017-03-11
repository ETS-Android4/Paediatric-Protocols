package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 2/21/17.
 */

public class MalariaTreatmentPreparation extends Activity {

    ImageView imgv;

    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maralia_treatment_preparation);
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);
        // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
    }

}

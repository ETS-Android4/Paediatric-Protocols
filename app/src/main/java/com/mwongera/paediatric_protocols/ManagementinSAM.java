package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 3/5/17.
 */

public class ManagementinSAM extends Activity {

    ImageView imgv;

    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managementin_sam);
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
    }


}

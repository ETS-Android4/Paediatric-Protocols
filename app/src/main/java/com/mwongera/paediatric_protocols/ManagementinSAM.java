package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.content.Intent;
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
        setContentView(R.layout.activity_anthrometry_weight_images);
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        Intent i= getIntent();
        String imaged=i.getStringExtra("image");
        int pos= Integer.parseInt(imaged);
        imgv = (ImageView) findViewById(R.id.imageView1);
        switch(pos){
            case 0:
                //img.setImageResource(R.drawable.my_image);
                imgv.setImageResource(R.drawable.samsteps);
                break;
            case 1:
                imgv.setImageResource(R.drawable.wfh1);
                break;

        }
        mAttacher = new PhotoViewAttacher(imgv);

    }


}

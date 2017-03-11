package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 2/22/17.
 */
public class FeedingActivity extends Activity {

    ImageView imgv;

    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);
       // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);

        //onlong press
        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(FeedingActivity.this, MalnutritionFeeding.class);

                startActivity(i);

                return false;
            }


        });


    }
}

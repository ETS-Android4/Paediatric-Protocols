package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 2/21/17.
 */

public class Tuberculosis extends Activity {

    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuberculosis);
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);
        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub

                Intent i= new Intent (Tuberculosis.this,DrugDescriptionActivity.class);
                int ps=45;
                String drugtype="Tuberculosis";
                i.putExtra("drugtype", drugtype);
                i.putExtra("position", ps+"");
                //Toast.makeText(getApplicationContext(), drugtype+" "+ps , Toast.LENGTH_LONG).show();
                startActivity(i);

                return false;
            }
        });

    }


}

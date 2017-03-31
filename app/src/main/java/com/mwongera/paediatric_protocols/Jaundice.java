package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 2/22/17.
 */
public class Jaundice extends Activity{

    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jaundiceimages);
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        Intent i= getIntent();
        String imaged=i.getStringExtra("image");
        int pos= Integer.parseInt(imaged);
        imgv = (ImageView) findViewById(R.id.imageView1);
        switch(pos){
            case 0:
                //img.setImageResource(R.drawable.my_image);
                imgv.setImageResource(R.drawable.neonatalJaundice2);
                break;
            case 1:
                imgv.setImageResource(R.drawable.neonatalJaundice3);
                break;
            case 2:
                imgv.setImageResource(R.drawable.neonatalSepsis2);
                break;
            case 3:
                Intent ii= new Intent (Jaundice.this,DrugActivity1.class);
                startActivity(ii);

                finish();
                break;
        }
        mAttacher = new PhotoViewAttacher(imgv);

    }

}

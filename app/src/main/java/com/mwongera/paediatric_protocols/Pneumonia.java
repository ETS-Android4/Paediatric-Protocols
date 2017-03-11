package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 3/5/17.
 */

public class Pneumonia extends Activity {

    ImageView imgv;
    AlertDialog dialog;
    int selectioned;
    PhotoViewAttacher mAttacher;
    String [] optioned ={"Drugs","Treatment Failure"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pneumonia);
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);
        createDialog();




        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub

                dialog.show();

                return false;
            }
        });
    }

    private void createDialog() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, optioned);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select  Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (which==0){
                    Intent i= new Intent (Pneumonia.this,DrugActivity1.class);
                    startActivity(i);

                }else if(which==1){
                    Intent i= new Intent (Pneumonia.this,TreatmentFailure.class);
                    startActivity(i);

                }

            }
        });
        dialog = builder.create();



    }



}

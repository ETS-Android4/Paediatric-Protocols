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

public class NeonatalSepsis extends Activity {

    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;
    final String[] option = new String[] { "Sepsis Treatment", "Drugs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neonatal_sepsis);
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);
        createAlertbox();

        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub

                dialog.show();

                return false;
            }
        });

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
    }
    public void createAlertbox(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Options");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                openWorkplace(which);

            }
        });
        dialog = builder.create();


    }
    private void openWorkplace(int ps) {
        Intent i = new Intent(NeonatalSepsis.this, Jaundice.class);

        i.putExtra("image", (ps+2)+"");
        //Toast.makeText(getApplicationContext(), " "+(ps+2) , Toast.LENGTH_LONG).show();
        startActivity(i);
    }



}

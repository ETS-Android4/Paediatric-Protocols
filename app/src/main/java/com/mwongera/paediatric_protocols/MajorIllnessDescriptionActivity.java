package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 3/4/17.
 */

public class MajorIllnessDescriptionActivity extends Activity {

    ImageView imgv;
    AlertDialog dialog;
    PhotoViewAttacher mAttacher;
    final String[] option = new String[] { "Shock", "Plan C", "Plan B","Plan A"};
    //  final TextView textv=(TextView)findViewById(R.id.textView1);
//  textv.setText("<<Swipe to view "+title +" >>");
//  textv.postDelayed(new Runnable(){
//  	  @Override
//  	  public void run()
//  	  {
//  	    textv.setVisibility(View.GONE);
//  	  }
//  	}, 5000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_illnes_description);
        final TextView textv=(TextView)findViewById(R.id.textView1);
        // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Treatment Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (which==0){
                    Intent i= new Intent (MajorIllnessDescriptionActivity.this,ShockActivity.class);
                    startActivity(i);

                }else if(which==1){
                    Intent i= new Intent (MajorIllnessDescriptionActivity.this,PlanCActivity.class);
                    startActivity(i);


                }else if(which==2){
                    Intent i= new Intent (MajorIllnessDescriptionActivity.this,PlanBActivity.class);
                    startActivity(i);


                }else if(which==3){
                    Intent i= new Intent (MajorIllnessDescriptionActivity.this,PlanAActivity.class);
                    startActivity(i);


                }

            }
        });
        dialog = builder.create();

        /**imgv.setOnLongClickListener(new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
        // TODO Auto-generated method stub

        dialog.show();

        return false;
        }
        });**/
        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub

                dialog.show();

                return false;
            }
        });
        textv.postDelayed(new Runnable(){
            @Override
            public void run()
            {
                textv.setVisibility(View.GONE);
            }
        }, 5000);

    }


}

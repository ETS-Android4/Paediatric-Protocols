package bpp.kelvian.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by kelvian on 3/5/17.
 */

public class NeonatalJaundice extends Activity{

    ImageView imgv;
    AlertDialog dialog;
    final String[] option = new String[] { "If 37 weeks or more gestational age", "If < 37 weeks gestational age"};
    PhotoViewAttacher mAttacher;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neonatal_jaundice);
        imgv = (ImageView) findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);
        createAlertbox();
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));


        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub

                dialog.show();

                return false;
            }
        });

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    public void createAlertbox(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Jaundice Treatment Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                openWorkplace(which);

            }
        });
        dialog = builder.create();


    }
    private void openWorkplace(int ps) {
        Intent i = new Intent(NeonatalJaundice.this, Jaundice.class);

        i.putExtra("image", ps+"");
        //Toast.makeText(getApplicationContext(), " "+ps , Toast.LENGTH_LONG).show();
        startActivity(i);
    }



}

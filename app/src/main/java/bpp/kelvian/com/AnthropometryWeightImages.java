package bpp.kelvian.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by kelvian on 3/4/17.
 */

public class AnthropometryWeightImages extends Activity {

    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;
    private FirebaseAnalytics mFirebaseAnalytics;

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
                imgv.setImageResource(R.drawable.wtestimation);
                break;
            case 1:
                imgv.setImageResource(R.drawable.wfh2);
                break;
            case 2:
                imgv.setImageResource(R.drawable.wfh3);
                break;
            case 3:
                imgv.setImageResource(R.drawable.wfh4);
                break;

        }
        mAttacher = new PhotoViewAttacher(imgv);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }

}

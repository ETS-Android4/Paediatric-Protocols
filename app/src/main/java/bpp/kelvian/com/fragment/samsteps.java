package bpp.kelvian.com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bpp.kelvian.com.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by kelvian on 4/13/17.
 */

public class samsteps extends Fragment{


    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_managementin_sam, container, false);
        imgv = (ImageView)rootView.findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);

        // Set the title view to show the page number.
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.title_template_step4));

        return rootView;

    }
    public static samsteps newInstance() {
        return new samsteps();
    }

}

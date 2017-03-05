package com.mwongera.paediatric_protocols.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mwongera.paediatric_protocols.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 3/5/17.
 */

public class Procedures extends Fragment {

    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_procedures, container, false);
        imgv = (ImageView)rootView.findViewById(R.id.imageView1);
        mAttacher = new PhotoViewAttacher(imgv);

        return rootView;

    }

}

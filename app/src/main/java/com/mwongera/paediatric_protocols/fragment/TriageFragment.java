package com.mwongera.paediatric_protocols.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.LinearLayout;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.mwongera.paediatric_protocols.ClickListener;
import com.mwongera.paediatric_protocols.Main2Activity;
import com.mwongera.paediatric_protocols.MyAdapter;
import com.mwongera.paediatric_protocols.R;
import com.mwongera.paediatric_protocols.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwongera on 2/10/17.
 */

public class TriageFragment extends Fragment{
    private ObservableWebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = (ObservableWebView) view.findViewById(R.id.webView);

        //must be called before loadURL
        MaterialViewPagerHelper.preLoadInjectHeader(mWebView);

        //have to inject header when WebView page loaded
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                MaterialViewPagerHelper.injectHeader(mWebView, true);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.loadUrl("http://mobile.francetvinfo.fr/");

        MaterialViewPagerHelper.registerWebView(getActivity(), mWebView, null);
    }

    public static TriageFragment newInstance() {
        return new TriageFragment();
    }

}

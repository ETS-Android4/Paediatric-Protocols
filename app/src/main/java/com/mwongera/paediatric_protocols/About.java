package com.mwongera.paediatric_protocols;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.thefinestartist.finestwebview.FinestWebView;

/**
 * Created by mwongera on 4/27/17.
 */

public class About extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.defaultTheme) {
            new FinestWebView.Builder(this).titleDefault("The Finest Artist")
                    .show("https://healthedsolutionsblog.wordpress.com/2016/04/30/terms-and-conditions/");
            ////                    .toolbarScrollFlags(0)
            //                    .webViewJavaScriptEnabled(true)
            //                    .webViewUseWideViewPort(false)
            ////                    .show("http://andrewliu.in/2016/01/30/聊聊Redis的订阅发布/");
            //                    .show("http://www.youtube.com");
        } else if (view.getId() == R.id.redTheme) {
            //            Intent intent = new Intent(this, WebViewActivity.class);
            //            startActivity(intent);

        }
    }

}

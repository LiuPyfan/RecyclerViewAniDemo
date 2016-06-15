package com.zdd.recyclerviewanidemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zdd.recyclerviewanidemo.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
    }
}

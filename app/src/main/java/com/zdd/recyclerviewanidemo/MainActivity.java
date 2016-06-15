package com.zdd.recyclerviewanidemo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.zdd.recyclerviewanidemo.adapter.MainContentAdapter;
import com.zdd.recyclerviewanidemo.presenter.IMainDataPresenter;
import com.zdd.recyclerviewanidemo.presenter.impl.MainDataPresenterImpl;
import com.zdd.recyclerviewanidemo.ui.imain.MainLoadData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MainLoadData {


    private IMainDataPresenter iMainDataPresenter;
    private RecyclerView rvMainContet;
    private SwipeRefreshLayout srlMainWidget;
    private LinearLayoutManager linearLayout;

    ArrayList<String> datas = new ArrayList<>();

    private int pastVisibleItems;
    private int totalItemCount;
    private int visibleItemCount;
    private boolean loading = false;
    private MainContentAdapter adapter;
    private DateFormat dateFormat;
    private ProgressBar progressBar;

    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMainContet = ((RecyclerView) findViewById(R.id.rv_main_content));
        srlMainWidget = ((SwipeRefreshLayout) findViewById(R.id.srl_main_widget));
        progressBar = ((ProgressBar) findViewById(R.id.progress));

        linearLayout = new LinearLayoutManager(MainActivity.this);
        rvMainContet.setLayoutManager(linearLayout);

        iMainDataPresenter = new MainDataPresenterImpl(this);

        srlMainWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                datas.clear();
                adapter.notifyDataSetChanged();
                iMainDataPresenter.getMainData(currentPage);
            }
        });
        rvMainContet.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//向下滚动
                    visibleItemCount = linearLayout.getChildCount();
                    totalItemCount = linearLayout.getItemCount();
                    pastVisibleItems = linearLayout.findFirstVisibleItemPosition();
                    if (!loading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = true;
                        progressBar.setVisibility(View.VISIBLE);
                        onLoadMore();
                    }
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            datas.add("----" + i);
        }
        adapter = new MainContentAdapter(MainActivity.this, datas);
        rvMainContet.setAdapter(adapter);
        dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.DEFAULT);
    }

    private void onLoadMore() {
        iMainDataPresenter.getMainData(currentPage);
    }

    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        if (null != srlMainWidget) {
            srlMainWidget.setRefreshing(false);
            loading = false;
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEerror() {

    }

    @Override
    public void undateList(ArrayList<String> updatedDatas) {
        currentPage++;
        datas.addAll(updatedDatas);
        adapter.notifyDataSetChanged();
    }
}

package com.zdd.recyclerviewanidemo.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.zdd.recyclerviewanidemo.presenter.IMainDataPresenter;
import com.zdd.recyclerviewanidemo.ui.imain.MainLoadData;

import java.util.ArrayList;

/**
 * @ClassName: RecyclerViewAniDemo
 * @CreateDate: 16/6/15 上午9:59
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 */
public class MainDataPresenterImpl implements IMainDataPresenter {

    private MainLoadData mainLoadData;

    public MainDataPresenterImpl(MainLoadData mainLoadData) {
        if (null == mainLoadData) {
            throw new IllegalArgumentException("mainloaddata is null");
        }
        this.mainLoadData = mainLoadData;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mainLoadData.hideProgressDialog();
            ArrayList<String> datas = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                datas.add("第" + msg.obj + "页----" + i);
            }
            mainLoadData.undateList(datas);
        }
    };

    @Override
    public void getMainData(final int page) {
        mainLoadData.showProgressDialog();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = Message.obtain();
                msg.obj = page;
                handler.sendMessage(msg);
            }
        }.start();
    }
}

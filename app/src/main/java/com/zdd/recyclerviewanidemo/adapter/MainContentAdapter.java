package com.zdd.recyclerviewanidemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.zdd.recyclerviewanidemo.R;
import com.zdd.recyclerviewanidemo.ui.DetailActivity;
import com.zdd.recyclerviewanidemo.util.ScreenUtil;

import java.util.ArrayList;

/**
 * @ClassName: RecyclerViewAniDemo
 * @CreateDate: 16/6/14 下午1:11
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 */
public class MainContentAdapter extends RecyclerView.Adapter<MainContentAdapter.MainContentViewHolder> {

    private Context mContext;
    private ArrayList<String> datas;

    public MainContentAdapter(Context mContext, ArrayList<String> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public MainContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_content, parent, false));
    }

    @Override
    public void onBindViewHolder(MainContentViewHolder holder, int position) {
        holder.tvItemDesc.setText(datas.get(position));
        runEnterAnimation(holder.itemView);
        holder.tvItemDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, DetailActivity.class));
            }
        });
    }

    private void runEnterAnimation(View view, int position) {
        view.setTranslationY(ScreenUtil.getScreenHeight(mContext));
        view.animate()
                .translationY(0)
                .setStartDelay(100 * (position % 5))
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }

    private void runEnterAnimation(View view) {
        view.setTranslationX(ScreenUtil.getScreenWidth(mContext));
        view.animate()
                .translationX(0)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MainContentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemDesc;

        public MainContentViewHolder(View itemView) {
            super(itemView);
            tvItemDesc = (TextView) itemView.findViewById(R.id.tv_item_desc);
        }
    }
}

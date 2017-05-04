package com.example.administrator.morningstar.view.adapter;

import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.bean.DataServer;
import com.example.administrator.morningstar.view.bean.Status;

/**
 * 文 件 名: PullToRefreshAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 19:55
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public PullToRefreshAdapter() {
        super( R.layout.layout_animation, DataServer.getSampleData(10));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.addOnClickListener(R.id.img).addOnClickListener(R.id.tweetText).addOnClickListener(R.id.tweetName);

        helper.setText(R.id.tweetName,"Hoteis in Rio de Janeiro");
        String msg="\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        ( (TextView)helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());
    }




}

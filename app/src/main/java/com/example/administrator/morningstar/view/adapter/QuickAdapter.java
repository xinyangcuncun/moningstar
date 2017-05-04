package com.example.administrator.morningstar.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.bean.DataServer;
import com.example.administrator.morningstar.view.bean.Status;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class QuickAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {

    public QuickAdapter(int dataSize) {
        super(R.layout.layout_animation, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetName, "Hoteis in Rio de Janeiro");
        helper.setText(R.id.tweetText, "O ever youthful,O ever weeping");

    }


}

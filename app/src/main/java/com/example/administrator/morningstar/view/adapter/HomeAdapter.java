package com.example.administrator.morningstar.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.bean.HomeItem;

import java.util.List;

/**
 * Created by anson on 2017/4/20.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeItem,BaseViewHolder> {
    public HomeAdapter(int p0, List<HomeItem> title) {
        super(p0,title);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.text, item.getTitle());
    }
}

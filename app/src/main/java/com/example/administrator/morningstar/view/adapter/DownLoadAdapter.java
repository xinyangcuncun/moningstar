package com.example.administrator.morningstar.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.bean.Status;
import com.example.administrator.morningstar.view.constant.AppConst;
import com.example.administrator.morningstar.view.db.MyBean;

import java.util.List;

/**
 * Created by anson on 2017/6/12.
 */

public class DownLoadAdapter extends BaseQuickAdapter<MyBean, BaseViewHolder> {

    public DownLoadAdapter( List<MyBean> data) {
        super(R.layout.item_down_load_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBean item) {
        switch (item.getState()) {
            case AppConst.PENDING:
                helper.setText(R.id.tv_item_state, "队列中");
                break;
            case AppConst.BEGGIN:
                helper.setText(R.id.tv_item_state, "下载开始");
                break;
            case AppConst.LOAING:
                helper.setText(R.id.tv_item_state, "下载开始");
                break;
            case AppConst.ERROR:
                helper.setText(R.id.tv_item_state, "错误");
                break;
            case AppConst.ENDING:
                helper.setText(R.id.tv_item_state, "完成");
                break;
            case AppConst.DOWNING:
                helper.setText(R.id.tv_item_state, "下载中");
                break;
            case AppConst.CANCLE:
                helper.setText(R.id.tv_item_state, "暂停");
                break;
        }
        helper.setText(R.id.tv_item_name, item.getUsername());
        if (item.getState() == AppConst.ENDING) {
            helper.setProgress(R.id.pb_item_bar, 100);
        } else {
            helper.setProgress(R.id.pb_item_bar, (int) item.getProgress());
        }
        helper.addOnClickListener(R.id.btn_item_start).addOnClickListener(R.id.btn_item_pause).addOnClickListener(R.id.btn_item_delete);
    }
}

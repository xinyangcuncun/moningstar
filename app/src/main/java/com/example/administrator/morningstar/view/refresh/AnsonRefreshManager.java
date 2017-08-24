package com.example.administrator.morningstar.view.refresh;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.morningstar.R;

/**
 * Created by anson on 2017/8/24.
 */

public class AnsonRefreshManager {
    private final Context context;
    private View inflate;

    public AnsonRefreshManager(Context context) {
        this.context = context;
    }

    public View getSeflHeadView() {
        if (inflate == null) {
            inflate = View.inflate(context, R.layout.view_refresh_header_normal, null);
            inflate.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        return inflate;
    }

    public int getHeght() {
        inflate.measure(0,0);
        int measuredHeight = inflate.getMeasuredHeight();
        return measuredHeight;
    }

}

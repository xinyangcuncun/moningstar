package com.example.administrator.morningstar.view.activity;

import android.support.design.widget.TextInputLayout;
import android.widget.Button;

/**
 * Created by anson on 2017/5/11.
 */

public interface IRegistActivity {
    Button getBtnLogin();

    Button getBtnRegist();

    TextInputLayout getTieName();

    TextInputLayout getTiePassword();

    TextInputLayout getTieToken();
}

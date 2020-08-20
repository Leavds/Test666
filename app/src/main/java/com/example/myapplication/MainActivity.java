package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private View viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KeyBordStateUtil keyBordStateUtil = new KeyBordStateUtil(this);
        keyBordStateUtil.addOnKeyBordStateListener(new KeyBordStateUtil.onKeyBordStateListener() {
            @Override
            public void onSoftKeyBoardShow(int keyboardHeight) {
                LinearLayout contentView = findViewById(R.id.contentView);
                LinearLayout messageLayout = findViewById(R.id.messageLayout);
                messageLayout.setClipChildren(false);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) messageLayout.getLayoutParams();
                layoutParams.width=contentView.getWidth();
                messageLayout.setLayoutParams(layoutParams);
                contentView.requestLayout();
            }

            @Override
            public void onSoftKeyBoardHide() {
                LinearLayout contentView = findViewById(R.id.contentView);
                LinearLayout messageLayout = findViewById(R.id.messageLayout);
                messageLayout.setClipChildren(true);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) messageLayout.getLayoutParams();
                layoutParams.width=0;
                layoutParams.weight=1;
                messageLayout.setLayoutParams(layoutParams);
                contentView.requestLayout();
            }
        });
    }
}

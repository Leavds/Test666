package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private  boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View inflate = LayoutInflater.from(this).inflate(R.layout.input_layout, null);
        final EditText editText = inflate.findViewById(R.id.ed1);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    View emjoLayout = inflate.findViewById(R.id.emjoLayout);
                    if(emjoLayout.getVisibility()==View.VISIBLE){
                        flag=true;
                    }else{
                        flag=false;
                    }
                }
                return flag;
            }
        });
        inflate.findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View emjoLayout = inflate.findViewById(R.id.emjoLayout);
                if (emjoLayout.getVisibility() == View.VISIBLE) {
                    emjoLayout.setVisibility(View.GONE);
                    editText.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText,0);

                } else {
                    emjoLayout.setVisibility(View.VISIBLE);
                    EditText viewById = inflate.findViewById(R.id.ed1);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(viewById.getWindowToken(),0);

                }
            }
        });
        KeyBordStateUtil keyBordStateUtil = new KeyBordStateUtil(this);
        keyBordStateUtil.addOnKeyBordStateListener(new KeyBordStateUtil.onKeyBordStateListener() {
            @Override
            public void onSoftKeyBoardShow(int keyboardHeight) {
                    FrameLayout contentView = findViewById(R.id.contentView);
                    contentView.removeView(inflate);
                    contentView.addView(inflate);
                    inflate.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSoftKeyBoardHide() {
                View emjoLayout = inflate.findViewById(R.id.emjoLayout);
                if (emjoLayout.getVisibility() == View.VISIBLE) {

                } else {
                    FrameLayout contentView = findViewById(R.id.contentView);
                    contentView.removeView(inflate);
                    inflate.setVisibility(View.GONE);
                    emjoLayout.setVisibility(View.GONE);

                }
            }
        });
    }
}

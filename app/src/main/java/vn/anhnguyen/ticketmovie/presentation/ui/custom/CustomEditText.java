package vn.anhnguyen.ticketmovie.presentation.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import vn.anhnguyen.ticketmovie.config.AppConfig;


public class CustomEditText extends TextInputEditText {
    private Context context;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        setupUI();
//        addFocusListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupUI() {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();
                return false;
            }
        });
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
//        addFocusListener();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        this.context = context;
//        addFocusListener();
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        Typeface normalTypeface = Typeface.createFromAsset(getContext().getAssets(), AppConfig.NORMAL_FONT);
        Typeface boldTypeface = Typeface.createFromAsset(getContext().getAssets(), AppConfig.BOLD_FONT);

        if (style == Typeface.BOLD) {
            super.setTypeface(boldTypeface/*, -1*/);
        } else {
            super.setTypeface(normalTypeface/*, -1*/);
        }
        super.setTypeface(tf, style);
    }

    private void addFocusListener() {
        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Log.e("editText", String.format("OnFocusChangeListener %s", hasFocus ? "focus" : "non-focus"));
                if (!hasFocus) {
                    hideKeyboard();
                }
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
    }
}

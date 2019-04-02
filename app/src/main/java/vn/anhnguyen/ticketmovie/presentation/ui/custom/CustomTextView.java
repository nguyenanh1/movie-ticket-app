package vn.anhnguyen.ticketmovie.presentation.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;


import vn.anhnguyen.ticketmovie.config.AppConfig;


@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {
    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text == null)
            return;
        super.setText(text, type);
    }

}

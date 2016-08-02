package com.shonejin.smartmarket.Adpater;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by oshinrawal on 7/28/16.
 */
public class ImageAdapter extends ImageView {
    public ImageAdapter(Context context) {
        super(context);
    }

    public ImageAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageAdapter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}

package com.spd.abkey.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author :Reginer in  2018/6/7 10:21.
 * 联系方式:QQ:282921012
 * 功能描述:禁止滑动ViewPager
 */
public class SlideViewPager extends ViewPager {
    /**
     * 是否可以进行滑动
     */
    private boolean isSlide = false;

    public void setSlide(boolean slide) {
        isSlide = slide;
    }

    public SlideViewPager(Context context) {
        super(context);
    }

    public SlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }
}

package com.example.fooddelivery;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

public class ScalingAnimation extends LinearLayoutManager {
    private Interpolator mInterpolator;
    private float mMinScale;
    private Context context;

    public ScalingAnimation(Context context, Interpolator mInterpolator, float mMinScale) {
        super(context);
        this.context = context;
        this.mInterpolator = mInterpolator;
        this.mMinScale = mMinScale;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int orientation = getOrientation();
        if (orientation == VERTICAL) {
            int scrolled = super.scrollVerticallyBy(dy, recycler, state);
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                float top = getDecoratedTop(child);
                float bottom = getDecoratedBottom(child);
                float scale = 1f;
                if (top < 0) {
                    scale = bottom / (bottom - top);
                }
                if (bottom > getHeight()) {
                    scale = (getHeight() - top) / (bottom - top);
                }

                if (scale < 1.0f) {
                    scale = Math.max(mMinScale, scale);
                }
                scale = mInterpolator.getInterpolation(scale);
                child.setScaleX(scale);
                child.setScaleY(scale);

            }
            return scrolled;

        } else {
            return 0;
        }

    }

}

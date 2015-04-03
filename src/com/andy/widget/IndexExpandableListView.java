package com.andy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class IndexExpandableListView extends ExpandableListView {

	private boolean mIsFastScrollEnabled = false;
	private IndexScrollBar mScrollBar = null;
	private GestureDetector mGestureDetector = null;

	public IndexExpandableListView(Context context) {
		super(context);
	}

	public IndexExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public IndexExpandableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFastScrollEnabled() {
		return mIsFastScrollEnabled;
	}

	@Override
	public void setFastScrollEnabled(boolean enabled) {
		mIsFastScrollEnabled = enabled;
		if (mIsFastScrollEnabled) {
			if (mScrollBar == null)
				mScrollBar = new IndexScrollBar(getContext(), this);
		} else {
			if (mScrollBar != null) {
				mScrollBar.hide();
				mScrollBar = null;
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);		
		// Overlay index bar
		if (mScrollBar != null)
			mScrollBar.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// Intercept ListView's touch event
		if (mScrollBar != null && mScrollBar.onTouchEvent(ev))
			return true;
		
		if (mGestureDetector == null) {
			mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

				@Override
				public boolean onFling(MotionEvent e1, MotionEvent e2,
						float velocityX, float velocityY) {
					// If fling happens, index bar shows
					mScrollBar.show();
					return super.onFling(e1, e2, velocityX, velocityY);
				}
				
			});
		}
		mGestureDetector.onTouchEvent(ev);
		
		return super.onTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}

	@Override
	public void setAdapter(ExpandableListAdapter adapter) {
		super.setAdapter(adapter);
		if (mScrollBar != null)
			mScrollBar.setAdapter(adapter);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (mScrollBar != null)
			mScrollBar.onSizeChanged(w, h, oldw, oldh);
	}
}

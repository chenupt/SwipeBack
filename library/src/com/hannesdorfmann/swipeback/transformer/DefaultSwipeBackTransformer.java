package com.hannesdorfmann.swipeback.transformer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.hannesdorfmann.swipeback.R;
import com.hannesdorfmann.swipeback.SwipeBack;

public class DefaultSwipeBackTransformer implements SwipeBackTransformer {


	@Override
	public void onSwipeBackViewCreated(SwipeBack swipeBack, Activity activity,
			final View swipeBackView) {


		onSwipeBackReseted(swipeBack, activity);

	}

	@Override
	public void onSwipeBackCompleted(SwipeBack swipeBack, Activity activity) {
		activity.finish();
		activity.overridePendingTransition(R.anim.swipeback_stack_to_front,
				R.anim.swipeback_stack_right_out);
	}

	@SuppressLint("NewApi")
	@Override
	public void onSwipeBackReseted(SwipeBack swipeBack, Activity activity) {

	}

	@SuppressLint("NewApi")
	@Override
	public void onSwiping(SwipeBack swipeBack, float openRatio, int pixelOffset) {

	}

}

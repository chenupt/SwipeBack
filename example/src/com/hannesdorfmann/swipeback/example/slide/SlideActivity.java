package com.hannesdorfmann.swipeback.example.slide;

import android.os.Bundle;

import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;
import com.hannesdorfmann.swipeback.example.R;
import com.hannesdorfmann.swipeback.example.SwipeBackActivity;

public class SlideActivity extends SwipeBackActivity {

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);

        // Init the swipe back mechanism
        SwipeBack.attach(this, Position.LEFT)
                .setDrawOverlay(true)
                .setDividerEnabled(true)
                .setContentView(R.layout.activity_simple)
                .setUpDefaultSwipeBackView();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.swipeback_slide_left_in,
                R.anim.swipeback_slide_right_out);

    }

}

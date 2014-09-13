package com.hannesdorfmann.swipeback.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

import com.hannesdorfmann.swipeback.SwipeBack;

/**
 * Created by Hannes Dorfmann on 03.01.14.
 */
public class SwipeBackActivity extends ActionBarActivity {

    protected SwipeBack swipeBack;

    @Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);

		Log.d("SwipeBack", "onCreate");

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

	}

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Log.d("dsdf", "setContentView");
//        setContentView(layoutResID);
//        swipeBack = SwipeBack.attach(this, Position.LEFT)
////                .setContentView(com.hannesdorfmann.swipeback.example.R.layout.activity_simple)
//                .setSwipeBackView(com.hannesdorfmann.swipeback.example.R.layout.swipeback_default);
    }

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.d("SwipeBack", "onDestroy");

	}

    @Override
    public void finish() {
        super.finish();
        Log.d("eeee", "parent finish");
    }

    @Override
	public void onBackPressed(){
		super.onBackPressed();
		overridePendingTransition(com.hannesdorfmann.swipeback.R.anim.swipeback_stack_to_front,
				com.hannesdorfmann.swipeback.R.anim.swipeback_stack_right_out);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if (item.getItemId() == android.R.id.home){
			onBackPressed();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}

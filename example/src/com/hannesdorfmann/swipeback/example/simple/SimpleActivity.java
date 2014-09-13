package com.hannesdorfmann.swipeback.example.simple;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;
import com.hannesdorfmann.swipeback.example.R;
import com.hannesdorfmann.swipeback.example.SwipeBackActivity;

/**
 * Created by hannes on 03.01.14.
 */
public class SimpleActivity extends SwipeBackActivity {

	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);
        setContentView(R.layout.activity_simple);

        swipeBack = SwipeBack.attach(this, Position.LEFT)
                .setUpDefalutSwipeBackView();

        swipeBack.setOnOpenListener(new SwipeBack.OnOpenListener() {
            @Override
            public boolean onOpen() {
                AlertDialog dialog = new AlertDialog.Builder(SimpleActivity.this).setMessage("Exit?")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SimpleActivity.super.finish();
                            }
                        }).create();
                dialog.show();
                return false;
            }
        });
	}

}

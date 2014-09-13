SwipeBack
=========

SwipeBack is for Android Activities to do pretty the same as the android "back-button" will do, but in a really intuitive way by using a swipe gesture

Demo
====
[demo](https://github.com/chenupt/SwipeBack/raw/dev/extra/swipeback-example.apk "demo")

![device-2014-09-13-223347.png](./extra/device-2014-09-13-223347.png "")



How to use it (EDITED)
=============
It's not supported yet to build it from xml.
You simply have to set it up in you Activities onCreate() method.
Instead of `Activity.setContentView()` call `SwipeBack.setContentView()`. Or just call Activity.setContentView() before setUpDefaultSwipeBackView().


styles.xml
```xml
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="android:windowBackground">@android:color/transparent</item>
    　　<item name="android:windowIsTranslucent">true</item>
        <item name="android:windowAnimationStyle">@android:style/Animation.Translucent</item>
    </style>
```

You can also call attach method in your BaseActivity for all activities. The Activity.setContentView() can be called in child activity instead of SwipeBack.setContentView().
```java
public class SwipeBackActivity extends FragmentActivity{

	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);

		// Init the swipe back
		SwipeBack.attach(this, Position.LEFT)
		    .setContentView(R.layout.activity_simple)
		    .setUpDefaultSwipeBackView();

	}


	@Override
	public void onBackPressed(){
		super.onBackPressed();
		overridePendingTransition(R.anim.swipeback_stack_to_front,
				R.anim.swipeback_stack_right_out);
	}
}
```



Customization
=============

The most important thing is the `SwipeBackTransformer`. This interface provides an API that will be called from the `SwipeBack` class. Here is where you implement frame by frame animation while the swipe back view will become open (by users swipe gesture). Additionally you can customize the SwipeBack position, the drag mode (drag content or drag window) and if it should be drawn as overlay or not (Type.BEHIND or Type.OVERLAY).

```java
    /**
	 * Attaches the SwipeBack to the Activity.
	 *
	 * @param activity
	 *            The activity the swipe back will be attached to.
	 * @param type
	 *            The {@link SwipeBack.Type} of the drawer.
	 * @param position
	 *            Where to position the swipe back.
	 * @param dragMode
	 *            The drag mode of the drawer. Can be either
	 *            {@link SwipeBack#DRAG_CONTENT} or
	 *            {@link SwipeBack#DRAG_WINDOW}.
	 * @return The created SwipeBack instance.
	 */
	public static SwipeBack attach(Activity activity, Type type, Position position, int dragMode, SwipeBackTransformer transformer)

```

You can also draw a divider between the normal content view and the swipe back view and a overlay that will fade out while opening the swipe back view.

```java
SwipeBack.attach(this, Position.LEFT)
		.setDrawOverlay(true)
		.setDivider(drawable)
		.setDividerEnabled(true) // Must be called to enable, setDivider() is not enough
		.setSwipeBackTransformer(new SlideSwipeBackTransformer())
		.setContentView(R.layout.activity_simple)
		.setSwipeBackView(R.layout.swipeback_default);

```

ViewPager
=========
To distinguish a ViewPager swipe gesture from a SwipeBack swipe gesture you have to setup a `OnInterceptMoveEventListener`:

```java
public class ViewPagerActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private int mPagerPosition;
	private int mPagerOffsetPixels;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SwipeBack.attach(this, Position.LEFT)
		.setContentView(R.layout.activity_view_pager)
		.setSwipeBackView(R.layout.swipeback_default)
		.setDividerAsSolidColor(Color.WHITE)
		.setDividerSize(2)
		.setOnInterceptMoveEventListener(
				new OnInterceptMoveEventListener() {
					@Override
					public boolean isViewDraggable(View v, int dx,
							int x, int y) {
						if (v == mViewPager) {
							return !(mPagerPosition == 0 && mPagerOffsetPixels == 0)
									|| dx < 0;
						}

						return false;
					}
				});


		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));

		mViewPager.setOnPageChangeListener(new SimpleOnPageChangeListener(){
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				mPagerPosition = position;
				mPagerOffsetPixels = positionOffsetPixels;
			}

		});
	}


}
```


Why?
====
The Samsung Galaxy Nexus was one of the first device without hardware buttons for "back", "home" and "app switching (multitasking)" but used the androids navigation bar on screen (introduced in Android 4.0). The navigation bar was at least in my opinion a big step forward, especially on screen rotation from landscape to portrait and vice versa. But I asked myself, do we really need a navigation bar? I mean the navigation bar takes ca. 10 % of the whole screen. Even at the home screen the "back" and "home" button is useless (because they do nothing). So I thought to myself: Why do we not use swipe gestures instead of a navigation bar? But maybe this idea is to futuristic and not suitable for all kind of android user. A few years later apple introduced the swipe back gesture in iOS 7. Why doesn't Android Apps use swipe gesture as alternative to the back button. Pinterest and Tumblr do so, but at least they use a single Activity and a ViewPager. The problem with this approach is:

 1. You will lost a little bit the ability to jump to any screen by using intents. Take Pinterest as an example: If you get a push notification from Pinterest and you click on it you will see a loading dialog on screen. Internal the navigation stack is generated by adding Fragments to the ViewPager.

 2. ActionBar: The ActionBar is as default not part of a fragment, but it's part of the activity. So you can not (by using a ViewPager) use the default ActionBar to swipe back to the previous Fragment, because the ActionBar will remain sticky. So you have to implement you own ActionBar and attach that to the fragments view.

My approach can be used for activities. It does pretty the same as the android menu drawers do. It adds an aditional layout and slides the content or the window to the side.


Thanks
======
 * Simon Vig Therkildsen: The most code of handling swipe gestures has been taken from his [android-menudrawer](https://github.com/SimonVT/android-menudrawer) library.

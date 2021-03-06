package com.neatocode.gyroimageview;

import android.app.Activity;
import android.content.Context;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import com.google.android.glass.touchpad.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.polites.android.GestureImageView;
import com.polites.android.MoveAnimation;
import com.polites.android.MoveAnimationListener;

import java.util.Arrays;
import com.neatocode.gyroimageview.HeadScrollView;

import logic.Shape;
import logic.Tetris;

/**
 * View an image, scrolling it with head movements.
 *
 */
//public class ViewActivity extends Activity implements FilteredOrientationTracker.Listener {
public class ViewActivity extends Activity{
	private static final int ANIMATION_DURATION_MS = 100;
	private static final float GYRO_TO_Y_PIXEL_DELTA_MULTIPLIER = 300;

	private GestureImageView image;
	private MoveAnimation moveAnimation;
	private FilteredOrientationTracker tracker;

	//DrawView drawView;

	private Tetris t;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.view_activity);

//		View v= (View) findViewById(R.id.r1c1);
//		v.setBackgroundColor(Color.RED);
//
//		View x= (View) findViewById(R.id.r1c2);
//		x.setBackgroundColor(Color.BLUE);

		t = new Tetris();

	}

	@Override
	public void onResume(Bundle savedInstanceState) {

		super.onResume(savedInstanceState);

		t.start();

	}

	public void paint() {

		int R = 22;
		int C = 10;

		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				Shape shape = t.shapeAt(j, R - i - 1);
				if (shape != Shape.NULL) {
					String s = "r" + (i + 1) + "c" + (j + 1);

					int resID = Context.getResources().getIdentifier(s, "id", "com.neatocode.gyroimageview.ViewActivity");

					View v = (View) findViewById(resID);
					v.setBackgroundColor(Color.RED);

//                drawSquare(g, 0 + j * squareWidth(),
//                            boardTop + i * squareHeight(), shape);
				}
			}

//        if (curPiece.getShape() != Shape.NULL) {
//            for (int i = 0; i < 4; ++i) {
//                int x = curX + curPiece.x(i);
//                int y = curY - curPiece.y(i);
//                drawSquare(g, 0 + x * squareWidth(),
//                        boardTop + (BoardHeight - y - 1) * squareHeight(),
//                        curPiece.getShape());
//            }
//        }
		}

	}





}


//    	image = (GestureImageView) findViewById(R.id.image);
//
//
//
//    	moveAnimation = new MoveAnimation();
//		moveAnimation.setAnimationTimeMS(ANIMATION_DURATION_MS);
//		moveAnimation.setMoveAnimationListener(new MoveAnimationListener() {
//			@Override
//			public void onMove(final float x, final float y) {
//				image.setPosition(x, y);
//				image.redraw();
//			}
//		});
//		tracker = new FilteredOrientationTracker(this, this);
//
//		DrawView drawview = new DrawView(this);
////		setContentView(drawview);
////		setContentView(R.layout.view_activity);
//		addContentView(drawview, new ViewGroup.LayoutParams(100, 100));

//	}
//
//	public class DrawView extends View
//	{
//		Paint paint = new Paint();
//		public DrawView(Context context)
//		{
//			super(context);
//		}
//
//		@Override
//		public void onDraw(Canvas canvas)
//		{
//			Paint paint = new Paint();
//			paint.setColor(Color.BLACK);
//			paint.setStrokeWidth(3);
//			canvas.drawRect(30, 30, 80, 80, paint);
//			paint.setColor(Color.CYAN);
//			canvas.drawRect(33,  60, 77, 77, paint);
//			paint.setColor(Color.YELLOW);
//			canvas.drawRect(33,  33, 77, 60, paint);
//		}
//	}
//
//
//
//	@Override
//	public void onResume() {
//		super.onResume();
//		tracker.onResume();
//	}
//
//	@Override
//	protected void onPause() {
//		super.onPause();
//		tracker.onPause();
//	}
//
//	// On gyro motion, start an animated scroll that direction.
//	@Override
//	public void onUpdate(float[] aGyro, float[] aGyroSum) {
////		System.out.println(aGyro[0] + " " + aGyro[1] + " " + aGyro[2]);
//		final float xGyro = aGyro[0];
//		final float deltaY = GYRO_TO_Y_PIXEL_DELTA_MULTIPLIER * xGyro;
//		animateTo(deltaY);
//	}
//
//	// Animate to a given offset, stopping at the image edges.
//	private void animateTo(final float animationOffsetY) {
//		float nextY = image.getImageY() + animationOffsetY;
//		final int maxHeight = image.getScaledHeight();
//		final int bottomBoundary = (-maxHeight / 2) + image.getDisplayHeight();
//		final int topBoundary = (maxHeight / 2);
//		if ( nextY < bottomBoundary ) {
//			nextY = bottomBoundary;
//		} else if ( nextY > topBoundary ) {
//			nextY = topBoundary;
//		}
//		moveAnimation.reset();
//		moveAnimation.setTargetX(image.getImageX());
//		moveAnimation.setTargetY(nextY);
//
////		System.out.println("Top boundary: " + topBoundary);
////		System.out.println("Bottom boundary: " + bottomBoundary);
////		System.out.println(image.getImageX() + " " + nextY);
//
////		System.out.println(image.getImageX() + " " + image.getScaledHeight() / 2);
//
//
////		moveAnimation.setTargetY(nextX);
//
//		image.animationStart(moveAnimation);
//
//
//	}
//
//	private GestureDetector mGestureDetector;
//
//
//
//	private GestureDetector createGestureDetector(Context context) {
//		GestureDetector gestureDetector = new GestureDetector(context);
//		gestureDetector.setBaseListener( new GestureDetector.BaseListener() {
//			@Override
//			public boolean onGesture(Gesture gesture) {
//				if (gesture == Gesture.LONG_PRESS || gesture == Gesture.TAP) {
//					System.out.println("long press or tap");
//					openOptionsMenu();
//					return true;
//				} else if (gesture == Gesture.TWO_TAP) {
//					System.out.println("two tap");
//
//					return true;
//				} else if (gesture == Gesture.SWIPE_RIGHT) {
//					System.out.println("swipe right");
//					return true;
//				} else if (gesture == Gesture.SWIPE_LEFT) {
//					System.out.println("swipe left");
//					return true;
//				}
//				return false;
//			}
//		});
//		return gestureDetector;
//	}
//
//	@Override
//	public boolean onGenericMotionEvent(MotionEvent event) {
//		if (mGestureDetector != null) {
//			return mGestureDetector.onMotionEvent(event);
//		}
//		return false;
//	}
//}
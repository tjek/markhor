package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class HackedViewPager extends ViewPager {

    public static final String TAG = HackedViewPager.class.getSimpleName();

    public HackedViewPager(Context context) {
        super(context);
    }

    public HackedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // Bug in MotionEvent.getX(int) and getY(int) where
            // pointerIndex is out of range - ignore any exceptions
        } catch (ArrayIndexOutOfBoundsException e) {
            // Don't know why this is being thrown
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // Bug in MotionEvent.getX(int) and getY(int) where
            // pointerIndex is out of range - ignore any exceptions
        } catch (ArrayIndexOutOfBoundsException e) {
            // Don't know why this is being thrown
        }
        return false;
    }

}

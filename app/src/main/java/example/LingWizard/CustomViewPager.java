package example.LingWizard;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by akash on 1/9/2017.
 */


public class CustomViewPager extends ViewPager {

    float mStartDragX;
    OnSwipeOutListener mListener;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public void setOnSwipeOutListener(OnSwipeOutListener listener) {
        mListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent me){
        float x = me.getX();
        if(mListener==null){
            return super.onTouchEvent(me);
        }
        switch (me.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //System.out.println("down");
                mStartDragX = x;
                return true;
            case MotionEvent.ACTION_UP:
                if (mStartDragX < x && getCurrentItem() == 0) {
                    //mListener.onSwipeOutAtStart();
                } else if (mStartDragX > x && getCurrentItem() == getAdapter().getCount() - 1) {
                        mListener.onSwipeOutEnd();
                }
                break;
        }
        return super.onTouchEvent(me);
    }

    public interface OnSwipeOutListener {
        //public void onSwipeOutAtStart();
        public void onSwipeOutEnd();
    }

}

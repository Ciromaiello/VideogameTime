package com.example.cirom.videogametime.tutorial.selezione_piattaforme;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.Toast;


public class CustomViewPager extends ViewPager {

    private PiattaformeFragment mFragment;
    private final ViewPager myself = this;
    private int oldPage;
    final int item = 0;

    public void setFragment(PiattaformeFragment mFragment) {
        this.mFragment = mFragment;
    }

    private boolean enabled;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        enabled = true;

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(!mFragment.canSwipe()){
                    myself.setCurrentItem(item);
                    final Toast toast = Toast.makeText(getContext(), "Seleziona almeno 1 console", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(ev.getAction()!=MotionEvent.ACTION_MOVE){
            return false;
        }
        if(mFragment!=null){
            return !mFragment.canSwipe();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()!=MotionEvent.ACTION_MOVE){
            return false;
        }
        if(mFragment!=null){
            return !mFragment.canSwipe();
        }
        return true;
    }*/

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

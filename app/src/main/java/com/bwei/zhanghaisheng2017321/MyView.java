package com.bwei.zhanghaisheng2017321;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 1.类的用途
 * 2.@author:zhanghaisheng
 * 3.@2017/3/21
 */


public class MyView extends View{
    Paint paint;
    private float mCurrx;
    private float mCurry;
    private float radius=100;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setAntiAlias(true);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getPointerCount()>1)
                    return false;
                float x=event.getX();
                float y=event.getY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        if(x>=radius&&y>=radius&&x<=getWidth()-radius&&y<=getHeight()-radius){
                        }
                        mCurrx=x;
                        mCurry=y;
                        invalidate();
                        break;
                }
                return true;
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float dis=getDisByXY(event);
        if (dis!=0){
            radius=dis;
            invalidate();
        }

        return super.onTouchEvent(event);

    }
    private float getDisByXY(MotionEvent event){
        if (event.getPointerCount()>=2){
            //第一个点
            float x=event.getX();
            float y=event.getY();
            //第二个点
            float x2=event.getX(1);
            float y2=event.getY(1);
            return (float) Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
        }
            return 0;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mCurrx=getWidth()/2;
        mCurry=getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCurrx,mCurry,radius,paint);
    }
}

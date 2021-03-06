package com.ttjjttjj.mypointtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.txt)
    TextView txt;
    @Bind(R.id.txt2)
    TextView txt2;
    @Bind(R.id.txt3)
    TextView txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        setFloatBtn();

        ButterKnife.bind(this);

        getPointXY();
        getWindowDesity();
    }

    /**
     * 直接在oncreate中获取出来的多是零
     * 需要在等ui控件加载完成获取，最好在方法onWindowFocusChanged
     */
    private void getPointXY(){

        txt.setText("当前TextView的坐标" + "left=" + txt.getLeft() + ",top=" + txt.getTop()
                + ",right=" + txt.getRight() + ",bottom=" + txt.getBottom());
    }

    /**
     * 该方法中获取view的坐标才不会为0
     *
     * 注意：View的坐标系统是相对于父控件而言的.
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        txt2.setText("当前TextView的坐标" + "left=" + txt2.getLeft() + ",top=" + txt2.getTop()
                + ",right=" + txt2.getRight() + ",bottom=" + txt2.getBottom());
    }

    private void getWindowDesity(){

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int W = metric.widthPixels;
        int H = metric.heightPixels;
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        txt3.setText("说明:\n运行设备Width = " + W + ",Height = " + H + ",density=" + density + ",\n该图中第二个txt显示的left=48px" +
                "它是由16*3=48来的，其中16是布局中到左边的距离16dp，其中3是获取到的desity=3，" +
                "其他的想计算dp的值，只需要那获取的px值除以3就行了");

    }

    private void testGetXYRawXY(){

    }

    /**
     * 点击获取当前位置点的坐标
     * 其中getX，getY，和getRawX，getRawY是不同的
     *
     *   event.getX();   event.getY();    //触摸点相对于其所在组件坐标系的坐标
     *   event.getRawX(); event.getRawY();   //触摸点相对于屏幕默认坐标系的坐标
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        Log.d("MainActivity", "event.getX()=" + event.getX() + ",event.getY()=" + event.getY()
                + ",event.getRawX()=" + event.getRawX() + ",event.getRawY()=" + event.getRawY());

        //点击右下角浮动的，可以显示当前的位置
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "event.getX()=" + event.getX() + ",event.getY()=" + event.getY()
                        + ",event.getRawX()=" + event.getRawX() + ",event.getRawY()=" + event.getRawY(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return super.onTouchEvent(event);
    }

    private void setFloatBtn(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




}

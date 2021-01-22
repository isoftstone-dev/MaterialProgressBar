package com.example.myapplication4.slice;



import com.example.materialprogressbarohoslibrary.CircleProgress;
import com.example.materialprogressbarohoslibrary.CircleProgressDrawTask;
import com.example.materialprogressbarohoslibrary.LineProgress;
import com.example.materialprogressbarohoslibrary.LineProgressDrawTask;
import com.example.myapplication4.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ProgressBar;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;


public class MainAbilitySlice extends AbilitySlice {

    public EventHandler handler = new EventHandler(EventRunner.create(true));

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        Component button1 = findComponentById(ResourceTable.Id_button1);
        Component button2 = findComponentById(ResourceTable.Id_button2);
        Component button3 = findComponentById(ResourceTable.Id_button3);
        Component button4 = findComponentById(ResourceTable.Id_button4);
        Component button5 = findComponentById(ResourceTable.Id_button5);
        Component button6 = findComponentById(ResourceTable.Id_button6);
        ProgressBar progressBar = (ProgressBar) findComponentById(ResourceTable.Id_progressBar);

        LineProgressDrawTask lineProgressDrawTask = new LineProgressDrawTask(button1);
        LineProgressDrawTask lineProgressDrawTask2 = new LineProgressDrawTask(button2);
        LineProgressDrawTask lineProgressDrawTask3 = new LineProgressDrawTask(button3);
        lineProgressDrawTask.setCurrentValue(50);

        LineProgress lineProgress = new LineProgress(this);
        lineProgress.setStartNum(2);
        lineProgress.SetMoreAnimator(lineProgressDrawTask2,100,200);
        lineProgress.SetMoreAnimator(lineProgressDrawTask3,100,200);
        lineProgress.SetMoreAnimator2(lineProgressDrawTask3,100,200);

        CircleProgressDrawTask mDrawTask = new CircleProgressDrawTask(button4);
        mDrawTask.setMaxValue(100);
        mDrawTask.setCurrentValue(75);
        CircleProgressDrawTask mDrawTask2 = new CircleProgressDrawTask(button5);
        mDrawTask.setMaxValue(100);
        CircleProgressDrawTask mDrawTask3 = new CircleProgressDrawTask(button6);
        mDrawTask.setMaxValue(100);

        CircleProgress circleProgress = new CircleProgress(this);
        circleProgress.SetMoreAnimator(mDrawTask2,100,200);
        circleProgress.SetMoreAnimator(mDrawTask3,100,200);
        circleProgress.SetMoreAnimator2(mDrawTask3,100,200);

        handler.postTask(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                i = i + 1;
                getUITaskDispatcher().asyncDispatch(new Runnable(){
                    @Override
                    public void run() {
                        if( i <= mDrawTask.getMaxValue()) {
                            progressBar.setProgressValue(i);

                        } else {
                            progressBar.setProgressValue(0);
                            i = 0;
                        }
                    }
                });
                handler.postTask(this, 100);
            }
        }, 200);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}

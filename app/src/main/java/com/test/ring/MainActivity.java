package com.test.ring;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.github.ring.CircleProgress;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    CircleProgress circleprogress;
    CheckBox cb_quekou, cb_wenzi, cb_secondcolor, cb_shunshizhong;
    TextView tv_progress;
    AppCompatSeekBar sb_startangle, sb_progress, sb_disangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_progress = (TextView) findViewById(R.id.tv_progress);
        circleprogress = (CircleProgress) findViewById(R.id.circleprogress);
        sb_startangle = (AppCompatSeekBar) findViewById(R.id.sb_startangle);
        cb_wenzi = (CheckBox) findViewById(R.id.cb_wenzi);
        cb_quekou = (CheckBox) findViewById(R.id.cb_quekou);
        cb_secondcolor = (CheckBox) findViewById(R.id.cb_secondcolor);
        cb_shunshizhong = (CheckBox) findViewById(R.id.cb_shunshizhong);
        sb_progress = (AppCompatSeekBar) findViewById(R.id.sb_progress);
        sb_disangle = (AppCompatSeekBar) findViewById(R.id.sb_disangle);
        cb_quekou.setOnCheckedChangeListener(this);
        cb_wenzi.setOnCheckedChangeListener(this);
        cb_secondcolor.setOnCheckedChangeListener(this);
        cb_shunshizhong.setOnCheckedChangeListener(this);
        tv_progress.setText("总进度" + circleprogress.getMaxProgress()+", 当前进度:" + circleprogress.getProgress());
        sb_startangle.setProgress(360+circleprogress.getStartAngle());
        sb_progress.setMax(circleprogress.getMaxProgress());
        sb_progress.setProgress(circleprogress.getProgress());
        sb_disangle.setProgress(circleprogress.getDisableAngle());
        circleprogress.setOnCircleProgressInter(new CircleProgress.OnCircleProgressInter() {
            @Override
            public void progress(int progress, int max) {
                tv_progress.setText("总进度"+circleprogress.getMaxProgress()+",当前进度:"+progress);
            }
        });
        sb_startangle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleprogress.setStartAngle(progress-360);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sb_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleprogress.setProgress(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sb_disangle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(cb_quekou.isChecked()){
                    circleprogress.setDisableAngle(progress);
                }else{
                    circleprogress.setDisableAngle(0);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        switch (v.getId()) {
            case R.id.cb_quekou:
                if (isChecked) {
                    circleprogress.setDisableAngle(sb_disangle.getProgress());
                } else {
                    circleprogress.setDisableAngle(0);
                }
                break;
            case R.id.cb_wenzi:
                circleprogress.setShowPercentText(isChecked);
                break;
            case R.id.cb_secondcolor:
                if (isChecked) {
                    circleprogress.setRingProgressSecondColor(ContextCompat.getColor(this, R.color.green));
                } else {
                    circleprogress.setRingProgressSecondColor(circleprogress.getRingProgressColor());
                }
                break;
            case R.id.cb_shunshizhong:
                circleprogress.setClockwise(isChecked);
                break;
        }
    }

}

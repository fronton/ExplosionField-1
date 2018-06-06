package com.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.ef.ExplosionField;

/**
 * Created by songwenchao
 * on 2017/11/2.
 * <p>
 * 主界面容器类
 * 需要 --
 * 可以设置底部栏属性等
 */
public class MainActivity extends Activity {

    private ExplosionField mField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mField = new ExplosionField(this);
        mField.setOnAnimFinishListener(new ExplosionField.OnFinishListener() {
            @Override
            public void onExplosionFinish() {
                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
            }
        });
        final TextView text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mField.explode(text);
            }
        });
    }
}

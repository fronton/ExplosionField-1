package com.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ExplosionField field = new ExplosionField(this);
        final TextView text = findViewById(R.id.text);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                field.explode(text);
            }
        });

        field.setOnAnimFinishListener(new ExplosionField.OnFinishListener() {
            @Override
            public void onExplosionFinish() {

            }
        });
    }
}

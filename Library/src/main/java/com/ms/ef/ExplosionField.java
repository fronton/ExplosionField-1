package com.ms.ef;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by songwenchao
 * on 2018/6/6 0006.
 * <p>
 * 类名
 * 需要 --
 * 可以 --
 */
public class ExplosionField extends View {

    private List<ExplosionAnimator> mExplosions = new ArrayList<>();
    private static int[] mExpandInset = new int[2];

    private ExplosionField(Context context) {
        this(context, null);
    }

    private ExplosionField(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private ExplosionField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public static ExplosionField getInstance(Activity activity) {
        Arrays.fill(mExpandInset, Utils.dp2Px(32));

        ViewGroup rootView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        ExplosionField field = new ExplosionField(activity);
        rootView.addView(field, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return field;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ExplosionAnimator explosion : mExplosions) {
            explosion.draw(canvas);
        }
    }

    public void expandExplosionBound(int dx, int dy) {
        mExpandInset[0] = dx;
        mExpandInset[1] = dy;
    }

    public void explode(final View view) {
        Rect r = new Rect();
        view.getGlobalVisibleRect(r);
        int[] location = new int[2];
        getLocationOnScreen(location);
        r.offset(-location[0], -location[1]);
        r.inset(-mExpandInset[0], -mExpandInset[1]);
        int startDelay = 100;
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(150);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            Random random = new Random();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((random.nextFloat() - 0.5f) * view.getWidth() * 0.05f);
                view.setTranslationY((random.nextFloat() - 0.5f) * view.getHeight() * 0.05f);

            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (listener != null) {
                    listener.onExplosionFinish();
                }
            }
        });
        animator.start();
        view.animate().setDuration(150).setStartDelay(startDelay).scaleX(0f).scaleY(0f).alpha(0f).start();
        explode(Utils.createBitmapFromView(view), r, startDelay, ExplosionAnimator.DEFAULT_DURATION);
    }

    public void explode(Bitmap bitmap, Rect bound, long startDelay, long duration) {
        final ExplosionAnimator explosion = new ExplosionAnimator(this, bitmap, bound);
        explosion.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mExplosions.remove(animation);
            }
        });
        explosion.setStartDelay(startDelay);
        explosion.setDuration(duration);
        mExplosions.add(explosion);
        explosion.start();
    }

    public interface OnFinishListener {

        void onExplosionFinish();
    }

    private OnFinishListener listener;

    public ExplosionField setOnAnimFinishListener(OnFinishListener listener) {
        this.listener = listener;
        return this;
    }

    public void clear() {
        mExplosions.clear();
        invalidate();
    }
}

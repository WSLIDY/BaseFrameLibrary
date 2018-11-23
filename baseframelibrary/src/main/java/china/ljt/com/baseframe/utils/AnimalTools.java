package china.ljt.com.baseframe.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import china.ljt.com.baseframe.R;


/**
 * Created by fdc-001 on 2016/5/27.
 */
public class AnimalTools {
    /**
     *
     * @return
     */
    public static TranslateAnimation mHidden() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    /**
     *
     * @return
     */
    public static TranslateAnimation showAnimation() {
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        return mShowAction;
    }
    public static Animation startAnimation(Context context){
        Animation showAnimation= AnimationUtils.loadAnimation(context, R.anim.alpha_view_start);
        return showAnimation;
    }
    public static void alphaShowAnimation(Context context, View view){
        if (view.getVisibility() != View.VISIBLE) {
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_view_start));
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void viewBottomAnimation(Context context, View view) {
        if (view.getVisibility() != View.VISIBLE) {
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_in));
            view.setVisibility(View.VISIBLE);
        }else{
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_out));
            view.setVisibility(View.GONE);
        }
    }
}

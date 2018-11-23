package china.ljt.com.baseframe.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;

import china.ljt.com.baseframe.R;


/**
 * Created by 李江涛 on 2017/10/17.
 * 说明:
 */

public class ActivityTools {
    //设置带元素的动画
    public static void startActivityWithSharedElements(Activity context, Intent intent, View shareView, String viewKey) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, shareView, viewKey).toBundle());
        } else {
            goNextActivityAlpha(context,intent);
        }
    }
    public static void startActivityWithSharedElements(Activity context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
        }else {
            goNextActivityAlpha(context,intent);
        }
    }
    //启动动画
    public static void activityExplodeAnim(Activity context) {
        try {
            context.getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.getWindow().setEnterTransition(new Explode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //启动动画
    public static void activitySlideAnim(Activity context) {
        try {
            context.getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.getWindow().setEnterTransition(new Slide());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //启动动画
    public static void activityFadeAnim(Activity context) {
        try {
            context.getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.getWindow().setEnterTransition(new Fade());
                context.getWindow().setExitTransition(new Fade());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*左进右出动画*/
    public static void goNextActivity(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_left,
                R.anim.out_from_left);
    }
    public static void goNextActivityNoAnim(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
    public static void goNextActivityWithFlag(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_left,
                R.anim.out_from_left);
    }

    public static void goNextActivityInNewTask(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_left,
                R.anim.out_from_left);
    }
    public static void goNextActivityNoAnima(Context context, Class<?> clazz,
                                             Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void goNextActivity(Context context, Class<?> clazz,
                                      Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_left,
                R.anim.out_from_left);
    }

    public static void goNextActivityInNewTask(Context context, Class<?> clazz,
                                               Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ((Activity) context).overridePendingTransition(R.anim.in_from_left,
                R.anim.out_from_left);
    }
    public static void goNextActivityForResult(Context context, Class<?> clazz,
                                               Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(intent, requestCode);
        ((Activity) context).overridePendingTransition(R.anim.in_from_left,
                R.anim.out_from_left);
    }
    public static void goNextActivityNoAnim(Context context, Class<?> clazz,
                                            Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void goNextActivityAlpha(Context context, Class<?> clazz,
                                           Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.alpha_start,
                R.anim.alpha_end);
    }
    public static void goNextActivityAlpha(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.alpha_start,
                R.anim.alpha_end);
    }
    public static void goNextActivityAlpha(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.alpha_start,
                R.anim.alpha_end);
    }
}

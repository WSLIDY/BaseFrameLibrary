package china.ljt.com.baseframe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * Created by 李江涛 on 2018/11/28.
 * 说明:
 */

public class LeftSlideView extends HorizontalScrollView {

    private View signVIew;//设置按钮


    private int mScrollWidth;//记录滚动条可以滚动的距离

    private Boolean once = false;//在onMeasure中只执行一次的判断

    private Boolean isOpen = false;//记录按钮菜单是否打开，默认关闭false

   private IonSlidingButtonListener mIonSlidingButtonListener;//自定义的接口，用于传达滑动事件等
    private LeftSlideView mMenu = null;
    private LeftSlideView openedMenu = null;

    /**
     * 1.构造方法
     */
    public LeftSlideView(Context context) {
        super(context, null);
    }

    public LeftSlideView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public LeftSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOverScrollMode(OVER_SCROLL_NEVER);
    }


    //2.在onMeasure中先取得作为“设置”、“删除”按钮的TextView
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            signVIew = getChildAt(0);
            if (signVIew instanceof ViewGroup) {
                signVIew = ((ViewGroup) signVIew).getChildAt(0);
            }
            once = true;
        }
    }


    //3.在onLayout中使Item在每次变更布局大小时回到初始位置，并且获取滚动条的可移动距离
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);

            //获取水平滚动条可以滑动的范围，即右侧“设置”、“删除”按钮的总宽度

            mScrollWidth = signVIew.getWidth();
        }
    }


    //4.滑动监听，按滑动的距离大小控制菜单开关
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN://按下
            case MotionEvent.ACTION_MOVE://移动
                mIonSlidingButtonListener.onDownOrMove(this);
                //onDownOrMove1(this);
                break;
            case MotionEvent.ACTION_UP://松开
            case MotionEvent.ACTION_CANCEL:
                changeScrollx();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 5.
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        signVIew.setTranslationX(1);

    }


    /**
     * 6.按滚动条被拖动距离判断关闭或打开菜单
     * getScrollX()                view的左上角相对于母视图的左上角的X轴偏移量
     * smoothScrollTo(x, y);        参数：相对于ScrollView左上角的位置来说，你要移动的位置
     */
    public void changeScrollx() {
        if (getScrollX() >= (mScrollWidth / 2)) {
            this.smoothScrollTo(mScrollWidth, 0);
            isOpen = true;
            mIonSlidingButtonListener.onMenuIsOpen(this);
           //onMenuIsOpen1(this);

        } else {
            this.smoothScrollTo(0, 0);
            isOpen = false;
        }
    }

    /**
     * 7.打开菜单
     */
//    public void openMenu() {
//        if (isOpen) {
//            return;
//        }
//        this.smoothScrollTo(mScrollWidth, 0);//相对于原来没有滑动的位置x轴方向偏移了mScrollWidth，y轴方向没有变化。
//        isOpen = true;
//       // mIonSlidingButtonListener.onMenuIsOpen(this);
//        onMenuIsOpen1(this);
//    }

    /**
     * 8.关闭菜单
     */
    public void closeMenu() {
        if (!isOpen) {
            return;
        }
        this.smoothScrollTo(0, 0);//相对于原来没有滑动的位置,x轴方向、y轴方向都没有变化，即回到原来的位置了。
        isOpen = false;
    }


    /**
     * 9.接口定义及注册方法
     */
    public void setSlidingButtonListener(IonSlidingButtonListener listener) {
        mIonSlidingButtonListener = listener;
    }

    public interface IonSlidingButtonListener {

        //该方法在Adapter中实现
        void onMenuIsOpen(View view);//判断菜单是否打开

        void onDownOrMove(LeftSlideView leftSlideView);//滑动或者点击了Item监听
    }
    /******************************************************************/
    /**
     * 删除菜单打开信息接收
     */
    public void onMenuIsOpen1(View view) {
        mMenu = (LeftSlideView) view;
       // openedMenu = (LeftSlideView) view;
    }
    /**
     * 滑动或者点击了Item监听
     *
     * @param leftSlideView
     */
    public void onDownOrMove1(LeftSlideView leftSlideView) {
        if (menuIsOpen1()) {
            if (mMenu != leftSlideView) {
                closeMenu();
            }
        }
    }
    /**
     * 关闭菜单
     */
    public void closeMenu1() {
        mMenu.closeMenu();
        mMenu = null;

    }
    /**
     * 判断菜单是否打开
     *
     * @return
     */
    public Boolean menuIsOpen1() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }
}

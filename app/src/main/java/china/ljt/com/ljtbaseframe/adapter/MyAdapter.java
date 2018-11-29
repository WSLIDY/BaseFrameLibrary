package china.ljt.com.ljtbaseframe.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import china.ljt.com.baseframe.widget.LeftSlideView;
import china.ljt.com.ljtbaseframe.MyActivity;
import china.ljt.com.ljtbaseframe.R;
import china.ljt.com.ljtbaseframe.Utils;

/**
 * Created by 李江涛 on 2018/11/28.
 * 说明:
 */

public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements LeftSlideView.IonSlidingButtonListener {
    private Context mContext;
//
    private List<String> mDatas = new ArrayList<String>();

    private LeftSlideView mMenu = null;

    public MyAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        for (int i = 0; i < 10; i++) {
            mDatas.add(i + "");
        }
    }

    public void setSlipLinstener(Context context) {
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text, item);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        //        //设置内容布局的宽为屏幕宽度
        holder.getView(R.id.layout_content).getLayoutParams().width = Utils.getScreenWidth(mContext);
        ((LeftSlideView) holder.itemView).setSlidingButtonListener(MyAdapter.this);
        holder.setOnClickListener(R.id.image, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //左滑设置点击事件
                int n = holder.getLayoutPosition();
                onDeleteItem(view, n);
            }
        });
        holder.setOnClickListener(R.id.layout_content, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();
                } else {
                    int n = holder.getLayoutPosition();
                    onItemsClick(view, n);
                }
            }
        });
    }




    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (LeftSlideView) view;
    }


    /**
     * 滑动或者点击了Item监听
     *
     * @param leftSlideView
     */
    @Override
    public void onDownOrMove(LeftSlideView leftSlideView) {
        if (menuIsOpen()) {
            if (mMenu != leftSlideView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

    /**
     * 判断菜单是否打开
     *
     * @return
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }

    public void onItemsClick(View view, int position) {
        Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();


    }

    public void onDeleteItem(View view, int position) {
        Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();

    }
}


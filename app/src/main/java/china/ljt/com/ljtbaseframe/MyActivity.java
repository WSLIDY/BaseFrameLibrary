package china.ljt.com.ljtbaseframe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import china.ljt.com.ljtbaseframe.adapter.MyAdapter;

/**
 * Created by 李江涛 on 2018/11/28.
 * 说明:
 */

public class MyActivity extends AppCompatActivity  {

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mRecyclerView = (RecyclerView) findViewById(R.id.slidRecycleView);

        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "hahaha");
        }
        MyAdapter adapter = new MyAdapter(R.layout.layout_second_item, list);
        adapter.setSlipLinstener(this);
//        SecondAdapter adapter = new SecondAdapter(R.layout.layout_second_item, list, R.id.layout_content,this, s);
//        adapter.setIOnClickListener(this);
        mRecyclerView.setAdapter(adapter);//设置适配器
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置控制Item增删的动画

    }


}

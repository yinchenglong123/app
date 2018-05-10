package com.example.rk2;

import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PullToRefreshListView plv;
    adapter adapter;
    String path = "http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4";
    List<bean.ResultBean.DataBean> list = new ArrayList<>();
    //使用handler进行关闭刷新
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取id
        plv = findViewById(R.id.plv);
        initData();
        adapter = new adapter(MainActivity.this,list);
        plv.setAdapter(adapter);
        //下拉
        //下拉刷新
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                //刷新时清空集合
                list.clear();
                //使用新id加载解析数据
                initData();
                //使用handler进行关闭刷新
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //关闭刷新
                        plv.onRefreshComplete();
                    }
                    //关闭刷新的时间
                },500);
            }
        });
        //上拉加载
        plv.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //运行一次id加一
                //使用新id加载解析数据
                initData();
            }
        });

    }

    private void initData() {
        new MyAsy().execute(path);
    }
    private class MyAsy extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            String data = url.getData();
            return data;
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            bean bean1 = gson.fromJson(s, bean.class);
            List<bean.ResultBean.DataBean> data1 = bean1.getResult().getData();
            //把请求的数据放入大集合中
            list.addAll(data1);
            //刷新
            adapter.notifyDataSetChanged();

        }
    }
}

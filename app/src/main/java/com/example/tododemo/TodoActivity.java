package com.example.tododemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tododemo.adapter.ClassTodoAdapter;
import com.example.tododemo.adapter.TodoAdapter;
import com.example.tododemo.pojo.TodoData;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;
/**
 * @author baroknight
 * */
public class TodoActivity extends AppCompatActivity {
private SwipeRecyclerView srv_todo;
private SwipeRecyclerView rv_class_todo;
private List<TodoData> unGonging;
private List<TodoData> finished;
 private    ClassTodoAdapter ctAdapter;
    private  TodoAdapter tAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        initView();
        getData();
        initSetting();
    }

    private void initSetting() {
//     左边列表的配置
        ctAdapter = new ClassTodoAdapter(this);
        rv_class_todo.setLayoutManager(new LinearLayoutManager(TodoActivity.this));
        rv_class_todo.setAdapter(ctAdapter);
//        设置默认的选取状态
        rv_class_todo.setScrollingTouchSlop(0);

        ctAdapter.setOnItemClickListener(new ClassTodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                rv_class_todo.setScrollingTouchSlop(adapterPosition);
                tAdapter.setClassPosition(adapterPosition);
                tAdapter.notifyDataSetChanged();
            }
        });

//        右边列表的配置
         tAdapter = new TodoAdapter();
        srv_todo.setLayoutManager(new LinearLayoutManager(this));
        srv_todo.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(this,R.color.divider_color)));
        srv_todo.setSwipeMenuCreator(swipeMenuCreator);
        srv_todo.setOnItemMenuClickListener(menuClickListener);
        srv_todo.setAdapter(tAdapter);
        tAdapter.notifyDataSetChanged(unGonging);

    }

    private void getData() {
        unGonging = new ArrayList<>();
        finished = new ArrayList<>();
        unGonging.add(new TodoData("2","菜鸟工具","好吃1",true));
        unGonging.add(new TodoData("3","Google11111","好吃1",true));
        finished.add(new TodoData("4","Google11111","好吃1",false));
/*
* [{"title": "菜鸟工具", "taskId": "2", "subTitle": "好吃1", "completed": true},
*  {"title": "Google11111", "taskId": "3", "subTitle": "好吃1", "completed": true}]
* */
    }

    private void initView() {
        srv_todo = findViewById(R.id.srv_todo);
        rv_class_todo = findViewById(R.id.rv_class_todo);
    }

    private SwipeMenuCreator swipeMenuCreator= new SwipeMenuCreator(){
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
//            设置每一个右滑选项的宽高
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//            添加右滑的菜单项
            {
                SwipeMenuItem finishedItem = new SwipeMenuItem(TodoActivity.this).setBackground(R.drawable.selector_purple)
                        .setText("完成")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(finishedItem);

                SwipeMenuItem modifyItem = new SwipeMenuItem(TodoActivity.this).setBackground(R.drawable.selector_green)
                        .setText("修改")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(modifyItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(TodoActivity.this).setBackground(R.drawable.selector_red)
                        .setText("修改")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(deleteItem);
            }
        }
    };

    private OnItemMenuClickListener menuClickListener = new OnItemMenuClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
            menuBridge.closeMenu();
            int menuPosition = menuBridge.getPosition();
            switch (menuPosition){
                case 0:
                    Toast.makeText(TodoActivity.this, "已完成", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(TodoActivity.this, "修改", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(TodoActivity.this, "删除", Toast.LENGTH_SHORT).show();
            }
        }
    };
}

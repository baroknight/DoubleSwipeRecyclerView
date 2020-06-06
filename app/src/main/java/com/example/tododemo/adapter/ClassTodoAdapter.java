package com.example.tododemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododemo.R;

import java.util.ArrayList;
import java.util.List;
/**
 * @author baroknight
 * */
public class ClassTodoAdapter extends RecyclerView.Adapter<ClassTodoAdapter.ViewHolder> {

    private Context context;
    private OnItemClickListener mOnItemClickListener = null;
    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色
private  List<String> classTodo;
    public ClassTodoAdapter(Context context) {

        this.context = context;

        isClicks = new ArrayList<>();
        classTodo = new ArrayList<>();
        classTodo.add("OnGonging");
        classTodo.add("Finished");
        for (int i = 0; i < classTodo.size(); i++) {
            isClicks.add(false);
            if (i == 0) {
                isClicks.set(0, true);
            }
        }



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class_todo, parent, false);
        ClassTodoAdapter.ViewHolder holder = new ClassTodoAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.itemView.setTag(holder.tv_class_name);

//左列表被选中的一项更替背景颜色
        if (isClicks.get(position)) {
//            holder.tv_class_name.setTextColor(Color.parseColor("#ff0000"));
            holder.tv_class_name.setBackgroundColor(Color.parseColor("#808080"));

        } else {
//            holder.tv_class_name.setTextColor(Color.parseColor("#000000"));
            holder.tv_class_name.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.tv_class_name.setText(classTodo.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < isClicks.size(); i++) {
                        isClicks.set(i, false);
                    }
                    isClicks.set(position, true);
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return classTodo.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_class_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_class_name = itemView.findViewById(R.id.tv_class_name);
        }
    }
}
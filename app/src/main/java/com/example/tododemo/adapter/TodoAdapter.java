package com.example.tododemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododemo.R;
import com.example.tododemo.pojo.TodoData;

import java.util.List;

/**
 * @author baroknight
 */
public class TodoAdapter extends RecyclerView.Adapter <TodoAdapter.THolder>{
    private List<TodoData> tList;
   private int classPosition;
    public void notifyDataSetChanged(List<TodoData> tList){
        this.tList = tList;
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public THolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo,parent,false);
        return new THolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull THolder holder, int position) {
        if (classPosition == 0 ){
            holder.setData(tList.get(position));
        }else if (classPosition == 1){
            holder.setData(null);
        }

    }
    @Override
    public int getItemCount() {
        return tList ==null ? 0 :tList.size();
    }


    public void setClassPosition(int classPosition) {
        this.classPosition = classPosition;
    }
    class THolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView subTitle;

        public THolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            subTitle = itemView.findViewById(R.id.tv_subtitle);
        }




        public void setData(TodoData todoData) {
            if (todoData ==null){
                title.setText("无");
                subTitle.setText("");
            }else {
                title.setText(todoData.getTitle());
                subTitle.setText(todoData.getSubTitle());
            }

        }
    }
}

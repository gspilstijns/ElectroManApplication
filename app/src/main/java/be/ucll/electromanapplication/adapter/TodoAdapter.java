package be.ucll.electromanapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.ucll.electromanapplication.R;
import be.ucll.electromanapplication.model.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    class TodoViewHolder extends RecyclerView.ViewHolder{
        private final TextView todoItemView;

        private TodoViewHolder(View itemView) {
            super(itemView);
            todoItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Todo> mTodos; // Cached copy of todo's

    public TodoAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        if (mTodos != null) {
            Todo current = mTodos.get(position);
            holder.todoItemView.setText(current.getDescription());
        } else {
            // Covers the case of data not being ready yet.
            holder.todoItemView.setText("No Todo's");
        }
    }

    public void setTodos(List<Todo> todos){
        mTodos = todos ;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mTodos != null)
            return mTodos.size();
        return 0;
    }
}

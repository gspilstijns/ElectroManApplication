package be.ucll.electromanapplication.adapter;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import be.ucll.electromanapplication.R;
import be.ucll.electromanapplication.database.TodoOfUser;
import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.viewmodel.TodoViewModel;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private final ClickListener listener;
    class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView todoItemCity;
        private final TextView todoItemCustomer;
        private final TextView todoItemDevice;
        private final TextView todoItemProblemCode;
        private final Button todoItemActionButton;
        private final TextView todoItemProcessed;


       // private WeakReference<ClickListener> listenerRef;

        private TodoViewHolder(View itemView,ClickListener listener) {
            super(itemView);
            todoItemCity = itemView.findViewById(R.id.txtCity);
            todoItemCustomer = itemView.findViewById(R.id.txtCustomerName);
            todoItemDevice = itemView.findViewById(R.id.txtDevice);
            todoItemProblemCode = itemView.findViewById(R.id.txtProblemCode);
            todoItemActionButton = itemView.findViewById(R.id.TodoItemActionButton);
            todoItemProcessed = itemView.findViewById(R.id.txtProcessedDateTime);
            todoItemActionButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TodoViewModel mTodoViewModel =new TodoViewModel((Application) v.getContext().getApplicationContext());
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
            alertDialog.setTitle(mTodos.get(getAdapterPosition()).getDevice());
            alertDialog.setMessage("WorkOrder notes");
            final EditText txtNotes = new EditText(v.getContext());
            txtNotes.setText(mTodos.get(getAdapterPosition()).getNote());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            txtNotes.setLayoutParams(lp);
            alertDialog.setView(txtNotes);

            //Onclick listener for the Dialog to input/update notes
            alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Todo to_update = mTodos.get(getAdapterPosition());
                    to_update.setNote(txtNotes.getText().toString());
                    to_update.setProcessed(true);
                    mTodoViewModel.updateTodo(to_update);
                   // mTodos.get(getAdapterPosition()).setNote(txtNotes.getText().toString());
                }
            });
            alertDialog.show();

            if (v.getId() == todoItemActionButton.getId()) {

                Toast.makeText(v.getContext(), "ITEM PRESSED = "+ mTodos.get(getAdapterPosition()).getDevice() , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

           // listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }

    private final LayoutInflater mInflater;
    private List<Todo> mTodos; // Cached copy of todo's
    private List<TodoOfUser> mUserTodos; // Cached copy of todo's

    public TodoAdapter(Context context, ClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        return new TodoViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        if (mTodos != null) {
            //TodoOfUser current = mTodos.get(position);
            Todo current = mTodos.get(position);
            //holder.todoItemView.setText(current.todos.iterator().next().getDescription());
            holder.todoItemCity.setText(current.getCity());
            holder.todoItemCustomer.setText(current.getCustomerName());
            holder.todoItemDevice.setText(current.getDevice());
            holder.todoItemProblemCode.setText(current.getProblemCode());
            holder.todoItemProcessed.setText(current.getProcessed().toString());
            if (current.getProcessed()){
                holder.todoItemActionButton.setText("Edit Notes");
            }
            else{
                holder.todoItemActionButton.setText("Finish Work Order");
            }
        } else {
            // Covers the case of data not being ready yet.
            holder.todoItemCity.setText("No Todo's");
            holder.todoItemCustomer.setText("No Todo's");
            holder.todoItemDevice.setText("No Todo's");
            holder.todoItemProblemCode.setText("No Todo's");

        }
    }


    public void setTodos(List<Todo> todos){
        mTodos = todos ;
        notifyDataSetChanged();
    }

    public void setUserTodos(List<TodoOfUser> todos){
        mUserTodos = todos ;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTodos != null)
            return mTodos.size();
        return 0;
    }
}

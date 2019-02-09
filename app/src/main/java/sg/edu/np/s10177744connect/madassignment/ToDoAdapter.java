package sg.edu.np.s10177744connect.madassignment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;



import java.util.List;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>{

    private Context context;
    private List<ToDo> list;
    ToDoActivity todoAct;

    ToDoAdapter(Activity activity){
        todoAct = (ToDoActivity) activity;
    }
    //public RecyclerTouchListener onClickListener;

    public ToDoAdapter(Context context, List<ToDo> list) { this.list = list; this.context = context;}




    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.to_do_row_item, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        final ToDo toDo = list.get(position);

        holder.bind(toDo);

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = toDo.isExpanded();
            toDo.setExpanded(!expanded);
            notifyItemChanged(position);
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ((ToDoActivity) view.getContext()).showToDoDialog(true, toDo,position);
                } catch(Exception e){

                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do your stuff
                try{
                    ((ToDoActivity) view.getContext()).deleteToDo(position);
                } catch(Exception e) {

                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder {

        private TextView description;
        private ImageButton deleteButton;
        private ImageButton editButton;
        private View subItem;

        public ToDoViewHolder(View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.item_description);
            deleteButton = itemView.findViewById(R.id.sub_item_deleteButton);
            editButton = itemView.findViewById(R.id.sub_item_editButton);
            subItem = itemView.findViewById(R.id.sub_item);
        }

        private void bind(ToDo toDo) {
            boolean expanded = toDo.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            description.setText(toDo.getDescription());

        }
    }

}

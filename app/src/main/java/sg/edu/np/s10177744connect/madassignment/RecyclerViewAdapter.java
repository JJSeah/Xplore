package sg.edu.np.s10177744connect.madassignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Country> data;
    private Context context;
    static String cont;

    public RecyclerViewAdapter(Context c, List data ) {
        context = c;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Country current = data.get(position); //set position
        //insert data into listview

        holder.Name.setText(current.Place);

        int id = context.getResources().
                getIdentifier(
                        data.get(position).Photo,
                        "drawable",
                        context.getPackageName()
                );

        holder.image.setImageResource(id);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CountryDetails.class);
                intent.putExtra("Country", data.get(position).Place);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView Name;
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgPhoto);
            Name = itemView.findViewById(R.id.text1);
            parentLayout = itemView.findViewById(R.id.card_view);

        }
    }
}

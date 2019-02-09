package sg.edu.np.s10177744connect.madassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    List<Country> data = new ArrayList<Country>();
    private Context context;

    public CountryAdapter(Context src, int layout, List data)
    {
        super(src, layout, data);
        this.data = data;
        context = src;
    }

    public View getView (int position,
                         View convertView,
                         ViewGroup parent){


        View itemView = convertView;
        if ( itemView == null)
        {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.grid_layout,
                            parent,
                            false);
        }

        Country current = data.get(position); //set position
        //insert data into listview
        TextView title = itemView.findViewById(R.id.text1);
        title.setText(data.get(position).Attraction);





        int id = context.getResources().
                getIdentifier(
                        data.get(position).Photo,
                        "drawable",
                        context.getPackageName()
                );

        ImageView photo = (ImageView)itemView.findViewById(R.id.imgPhoto);

        photo.setImageResource(id);

        return itemView;

    }
}

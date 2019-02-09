package sg.edu.np.s10177744connect.madassignment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JournalAdapter extends ArrayAdapter<Country> {
    List<Country> data = new ArrayList<Country>();
    private Context context;

    public JournalAdapter(Context src, int layout, List data)
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
                    .inflate(R.layout.journal_layout,
                            parent,
                            false);
        }

        Country current = data.get(position); //set position
        //insert data into listview
        TextView title = itemView.findViewById(R.id.txtName);

        title.setText(data.get(position).Attraction);


        ImageView photo = (ImageView)itemView.findViewById(R.id.imgPhoto);
        byte[] Pic = data.get(position).image;
        Bitmap bitmap = BitmapFactory.decodeByteArray(Pic, 0, Pic.length);
        photo.setImageBitmap(bitmap);

        return itemView;

    }
}

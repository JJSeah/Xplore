package sg.edu.np.s10177744connect.madassignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import sg.edu.np.s10177744connect.madassignment.models.ServiceNoModel;

import java.util.ArrayList;
import java.util.List;

public class busAdapter extends RecyclerView.Adapter<busViewHolder> {
    private List<ServiceNoModel> busList = fetchData.ServiceNoList;
    private Context context;

    public busAdapter(Context c, List<ServiceNoModel> l) {
        context = c;
        busList = l;
    }
    @Override
    public busViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bus_layout, parent, false);
        return new busViewHolder(item);
    }

    @Override
    public void onBindViewHolder(busViewHolder holder, final int position) {
        ServiceNoModel s = busList.get(position);
        List<ServiceNoModel.NextBus> n = s.getBusList();
        List<ProgressBar> p = new ArrayList<>();
        List<TextView> ib = new ArrayList<>();
        p.add(holder.crowd);
        p.add(holder.crowd2);
        p.add(holder.crowd3);
        ib.add(holder.incomingBus);
        ib.add(holder.incomingBus2);
        ib.add(holder.incomingBus3);
        holder.busNo.setText(s.getServiceNo());
        for(int i=0; i<3; i++)
        {

            if (n.get(i).getLoad().equals("SEA")) {
                p.get(i).setProgress(0);
            } else if (n.get(i).getLoad().equals("SDA")) {
                p.get(i).setProgress(50);
            } else {
                p.get(i).setProgress(0);
            }

            if (n.get(i).getEstimatedArrival() < 1) {
                ib.get(i).setText("Arr");
            } else {
                ib.get(i).setText(Long.toString(n.get(i).getEstimatedArrival()));
            }
        }

    }

    @Override
    public int getItemCount() {
        if(busList == null) {
            return 0;
        } else {
            return busList.size();
        }

    }

}

package sg.edu.np.s10177744connect.madassignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sg.edu.np.s10177744connect.madassignment.models.Money;

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.MyViewHolder> {
    private Context context;
    private List<Money> moneyList;

    public FinanceAdapter(Context context, List<Money> moneyList) {
        this.context = context;
        this.moneyList = moneyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.finance_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Money money = moneyList.get(position);

        holder.description.setText(money.getDescription());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        holder.amount.setText(Double.toString(money.getAmount()));

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(money.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return moneyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public TextView dot;
        public TextView timestamp;
        public TextView amount;

        public MyViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.description);
            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
            amount = view.findViewById(R.id.amount);
        }
    }

    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }

}

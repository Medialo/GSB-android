package fr.medialo.gsba.core.tabbed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.medialo.gsba.R;
import fr.medialo.gsba.core.Fee;
import fr.medialo.gsba.core.FeeLine;

public class ExclFeeLineAdapter extends RecyclerView.Adapter<FeeLineAdapter.ViewHolder> {


    private List<Fee> mFeeLineList;
    private AdapterView.OnItemClickListener listener;
    FeeLine selectedFeeLine;

    public Fee getSelectedFee() {
        return selectedFeeLine;
    }



    // Pass in the contact array into the constructor
    public ExclFeeLineAdapter(List<Fee> feeLineList, AdapterView.OnItemClickListener listener) {
        mFeeLineList = feeLineList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public FeeLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fee_line_view_in_list, parent, false);

        return new FeeLineAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FeeLineAdapter.ViewHolder holder, int position) {
        Fee fee = this.mFeeLineList.get(position);
        holder.getDate().setText(fee.getDate().toString());
        holder.getLabel().setText(fee.getName());
        holder.getPrice().setText(fee.getCostToString());
        holder.getQuantity().setText("1");


        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(null,null,position, fee.getId());
        });
    }

    @Override
    public int getItemCount() {
        return mFeeLineList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        private final TextView label;
        private TextView quantity;
        private final TextView date;
        private final TextView price;

        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View

            label = (TextView) view.findViewById(R.id.textview_label_fee_line_view_in_list);

            quantity = (TextView) view.findViewById(R.id.textview_quantity_fee_line_view_in_list);


            date = (TextView) view.findViewById(R.id.textview_date_fee_line_view_in_list);
            price = (TextView) view.findViewById(R.id.textview_price_fee_line_view_in_list);
        }

        public TextView getLabel() {
            return label;
        }

        public TextView getQuantity() {
            return quantity;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getPrice() {
            return price;
        }
    }




}

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

public class FeeLineAdapter extends RecyclerView.Adapter<FeeLineAdapter.ViewHolder> {

    private List<FeeLine> mFeeLineList;
    private AdapterView.OnItemClickListener listener;
    FeeLine selectedFeeLine;

    public FeeLine getSelectedFee() {
        return selectedFeeLine;
    }



    // Pass in the contact array into the constructor
    public FeeLineAdapter(List<FeeLine> feeLineList, AdapterView.OnItemClickListener listener) {
        mFeeLineList = feeLineList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fee_line_view_in_list, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeeLine feeLine = this.mFeeLineList.get(position);
        holder.getDate().setText(feeLine.getDate().toString());
        holder.getLabel().setText(feeLine.getName() + " (" + feeLine.getSubCategory().getCost()+"€/u)");
        holder.getPrice().setText(feeLine.getCostToString());

        holder.getQuantity().setText(feeLine.getQuantity() +"");


        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(null,null,position, feeLine.getId());
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
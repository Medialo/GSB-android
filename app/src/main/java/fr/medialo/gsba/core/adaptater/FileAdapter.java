package fr.medialo.gsba.core.adaptater;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import fr.medialo.gsba.EditActivity;
import fr.medialo.gsba.R;
import fr.medialo.gsba.core.FeeFile;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private List<FeeFile> mFeeFiles;
    private AdapterView.OnItemClickListener listener;

    public FeeFile getSelectedFeeFile() {
        return selectedFeeFile;
    }

    FeeFile selectedFeeFile;

    // Pass in the contact array into the constructor
    public FileAdapter(List<FeeFile> feeFiles, AdapterView.OnItemClickListener listener) {
        mFeeFiles = feeFiles;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.file_view_in_list, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeeFile feeFile = this.mFeeFiles.get(position);
        holder.getTextView().setText("Dossier n°" + feeFile.getId());
        holder.getDatetxt().setText(feeFile.getDateCreatedToString());
        holder.getDate2txt().setText(feeFile.getDateModifiedToString());

        holder.itemView.setOnClickListener(v -> {
                  this.selectedFeeFile = feeFile;
                listener.onItemClick(null,null,position, feeFile.getId());
        });
    }

    @Override
    public int getItemCount() {
        return mFeeFiles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView getDatetxt() {
            return datetxt;
        }

        public TextView getDate2txt() {
            return date2txt;
        }

        private final TextView textView;
        private final TextView datetxt;
        private final TextView date2txt;

        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView);
            datetxt = (TextView) view.findViewById(R.id.textView3);
            date2txt = (TextView) view.findViewById(R.id.textView4);
        }

        public TextView getTextView() {
            return textView;
        }
    }

 /*   public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }*/




}

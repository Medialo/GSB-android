package fr.medialo.gsba.core.tabbed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

import fr.medialo.gsba.EditActivity;
import fr.medialo.gsba.EditLineActivity;
import fr.medialo.gsba.R;
import fr.medialo.gsba.core.Fee;
import fr.medialo.gsba.core.database.dao.ExclFeeLine_DAO;

public class EditFragmentExclFeeLine extends Fragment {

    private ExclFeeLineAdapter feeLineAdapter;
    private ExclFeeLine_DAO feeLine_dao;

    public ExclFeeLineAdapter getFeeLineAdapter() {
        return feeLineAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_excl_fee_line, container, false);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Bundle args = getArguments();
        // ((TextView) view.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));

        int fileId = getArguments().getInt("id");
        feeLine_dao = new ExclFeeLine_DAO(fileId);

        RecyclerView rvFeeLine = view.findViewById(R.id.recycler_view_list_excl_fee_line_fragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rvFeeLine.getContext(),LinearLayoutManager.VERTICAL,false);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(rvFeeLine.getContext(), layoutManager.getOrientation());
        rvFeeLine.addItemDecoration(dividerItemDecoration);
        List<Fee> feeLineList = feeLine_dao.getAll();
        feeLineAdapter = new ExclFeeLineAdapter(feeLineList,(parent, view2, position, id) -> {
            Intent intent = new Intent(getContext(), EditLineActivity.class);

            intent.putExtra("id", id);
            intent.putExtra("fee",true);
            intent.putExtra("file_id",fileId);
            startActivityForResult(intent,1);

        });
        rvFeeLine.setAdapter(feeLineAdapter);

        rvFeeLine.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //         Toast.makeText(view.getContext(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(view.getContext(), "Supprimé", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                Fee fee = feeLineAdapter.getmFeeLineList().get(position);
                feeLine_dao.delete(fee);
                feeLineAdapter.setmFeeLineList(feeLine_dao.getAll());
                feeLineAdapter.notifyDataSetChanged();
                EditActivity.getInstance().execute();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvFeeLine);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        feeLineAdapter.setmFeeLineList(feeLine_dao.getAll());
        feeLineAdapter.notifyDataSetChanged();
    }


}

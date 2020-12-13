package fr.medialo.gsba.core.tabbed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.medialo.gsba.EditLineActivity;
import fr.medialo.gsba.R;
import fr.medialo.gsba.core.Fee;
import fr.medialo.gsba.core.database.dao.ExclFeeLine_DAO;

public class EditFragmentExclFeeLine extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_excl_fee_line, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Bundle args = getArguments();
        // ((TextView) view.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));

        int fileId = getArguments().getInt("id");
        ExclFeeLine_DAO feeLine_dao = new ExclFeeLine_DAO(fileId);

        RecyclerView rvFeeLine = view.findViewById(R.id.recycler_view_list_excl_fee_line_fragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rvFeeLine.getContext(),LinearLayoutManager.VERTICAL,false);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(rvFeeLine.getContext(), layoutManager.getOrientation());
        rvFeeLine.addItemDecoration(dividerItemDecoration);
        List<Fee> feeLineList = feeLine_dao.getAll();
        ExclFeeLineAdapter feeLineAdapter = new ExclFeeLineAdapter(feeLineList,(parent, view2, position, id) -> {
            Intent intent = new Intent(getContext(), EditLineActivity.class);
            startActivity(intent);

        });
        rvFeeLine.setAdapter(feeLineAdapter);
        rvFeeLine.setLayoutManager(new LinearLayoutManager(view.getContext()));


    }
}

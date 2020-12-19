package fr.medialo.gsba;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.Inflater;

import fr.medialo.gsba.core.Fee;
import fr.medialo.gsba.core.FeeFile;
import fr.medialo.gsba.core.FeeLine;
import fr.medialo.gsba.core.SubCategoryManager;
import fr.medialo.gsba.core.category.SubCategory;
import fr.medialo.gsba.core.database.dao.Dao;
import fr.medialo.gsba.core.database.dao.ExclFeeLine_DAO;
import fr.medialo.gsba.core.database.dao.FeeFile_DAO;
import fr.medialo.gsba.core.database.dao.FeeLine_DAO;

public class EditLineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Dao dao;
    private Fee fee;
    private int fileID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        boolean isFee = intent.getBooleanExtra("fee",false);
        fileID = intent.getIntExtra("file_id",0);
        setContentView(R.layout.edit_fee_line);

        List<SubCategory> list = new ArrayList<>(SubCategoryManager.map.values());

        spinner = (Spinner) findViewById(R.id.spinnerSubCat);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<SubCategory> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);





        TextView spinnerTextView = findViewById(R.id.spinnerTextView);
        TextView tvQuantity = findViewById(R.id.textviewQuantity);
        EditText edQuantity = findViewById(R.id.editTextQuantity);



        TextView labelTextView = findViewById(R.id.labelTextView);
        EditText label = findViewById(R.id.ediTextLabelExclFee);

        EditText cost = findViewById(R.id.editTextCostExclFee);
        TextView tvCost = findViewById(R.id.textviewCost);
        DatePicker date = findViewById(R.id.datePickerDateExclFee);
        Switch s = findViewById(R.id.switchExclFee);
        AtomicReference<LocalDate> localDate;
        if(isFee){ //excl / Fee
            dao = new ExclFeeLine_DAO(fileID);
            fee = (Fee) dao.get((int) intent.getLongExtra("id",-1));


            spinner.setVisibility(View.GONE);
            spinnerTextView.setVisibility(View.GONE);

            cost.setText(String.valueOf(fee.getCost()));


            localDate = new AtomicReference<>(fee.getDate());
            label.setText(fee.getName());

            tvQuantity.setVisibility(View.GONE);
            edQuantity.setVisibility(View.GONE);

        } else { //compris

            dao = new FeeLine_DAO(fileID);
            fee = (FeeLine) dao.get((int) intent.getLongExtra("id",-1));

            tvCost.setVisibility(View.GONE);
            cost.setVisibility(View.GONE);
            label.setVisibility(View.GONE);
            labelTextView.setVisibility(View.GONE);

            edQuantity.setText(((FeeLine)fee).getQuantity() +"");
            spinner.setSelection(((FeeLine)fee).getSubCategory().getId()-1,true);


            localDate = new AtomicReference<>(((FeeLine)fee).getDate());
        }

        s.setChecked(fee.getStatu_idBool());
        date.updateDate(fee.getDate().getYear(),fee.getDate().getMonthValue(),fee.getDate().getDayOfMonth());







        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        //Faire des recherches atomic ref

        date.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
            localDate.set(LocalDate.of(year, monthOfYear+1, dayOfMonth));
        });

        Button button = findViewById(R.id.buttonEditFeeLineExcl);
        button.setOnClickListener(v -> {

            if(localDate.get() != null)
                fee.setDate(localDate.get());
            fee.setStatu_id(s.isChecked() ? 2 : 1);
            if(isFee){//excl
                fee.setCost(Double.parseDouble(cost.getText().toString()));
                fee.setName(label.getText().toString());


            } else {
                SubCategory subCategory = (SubCategory) spinner.getSelectedItem();
                ((FeeLine)fee).setSubCategory(subCategory);
                ((FeeLine)fee).setQuantity(Integer.parseInt(edQuantity.getText().toString()));
            }
            if(fee.getId() == -1)
                dao.create(fee);
            else
                dao.update(fee);

//
            FeeFile_DAO feeFile_dao = new FeeFile_DAO();
            feeFile_dao.updateDate(fileID);


            //    setResult(1);

            finish();
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    //    Toast.makeText(parent.getContext(), "Selected: " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}

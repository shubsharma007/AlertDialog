package com.example.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

import com.example.toast.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    boolean[] selectedSize;
    ArrayList<Integer> sizeList = new ArrayList<>();
    String[] sizeArray = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        selectedSize = new boolean[sizeArray.length];
        binding.sizeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Size");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(sizeArray, selectedSize, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            sizeList.add(which);
                            // Sort array list
                            Collections.sort(sizeList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            sizeList.remove(Integer.valueOf(which));
                        }
                    }
                });
                builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < sizeList.size(); i++) {
                            stringBuilder.append(sizeArray[sizeList.get(i)]);
                            // check condition
                            if (i != sizeList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(" , ");
                            }
                        }
                        binding.sizeTv.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

    }
}
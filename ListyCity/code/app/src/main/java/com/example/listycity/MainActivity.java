package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> datalist;

    EditText cityInput;
    Button add, delete, confirm;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView title = findViewById(R.id.title);
        title.setText("ListyCity");

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        datalist = new ArrayList<>();
        datalist.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, datalist);
        cityList.setAdapter(cityAdapter);

        cityInput = findViewById(R.id.city_input);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        confirm = findViewById(R.id.confirm);

        add.setOnClickListener(v -> {
            cityInput.setEnabled(true);
            confirm.setEnabled(true);
            cityInput.requestFocus();
        });

        confirm.setOnClickListener(v -> {
            String cityname = cityInput.getText().toString().trim();

            if (!cityname.isEmpty()) {
                datalist.add(cityname);
                cityAdapter.notifyDataSetChanged();

                cityInput.setText("");
                cityInput.setEnabled(false);
                confirm.setEnabled(false);
            }
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

        delete.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                datalist.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });
    }
}
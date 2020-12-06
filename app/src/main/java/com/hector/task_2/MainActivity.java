package com.hector.task_2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.google.android.material.button.MaterialButton;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText fullName;
    Spinner state;
    Button saveButton,addButton;
    LinearLayout mainLayout;

    String[] states;
    int COUNT;

    ArrayList<Integer> removedLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        mainLayout = findViewById(R.id.mainLayout);
        fullName = findViewById(R.id.full_name);
        state = findViewById(R.id.state);
        saveButton = findViewById(R.id.save_button);
        addButton = findViewById(R.id.add_button);

        COUNT = 0;
        removedLayouts = new ArrayList<>();

        states = new String[]{"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal","Andaman and Nicobar","Chandigarh","Dadra and Nagar Haveli","Daman and Diu","Lakshadweep","Delhi","Puducherry"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, states);
        state.setAdapter(adapter);

        createLayout();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLayout();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full_name, state_name;
                ArrayList<EducationDataModel> educationDataModelList = new ArrayList<>();
                full_name = fullName.getText().toString();
                state_name = state.getSelectedItem().toString();

                for (int i=1; i <=COUNT; i++ ){
                    if(!removedLayouts.contains(i)){
                        EditText education = findViewById(i*10+1);
                        EditText school = findViewById(i*10+2);
                        CheckBox present = findViewById(i*10+3);
                        educationDataModelList.add(new EducationDataModel(education.getText().toString(), school.getText().toString(), present.isChecked()));
                    }
                }

                Intent i = new Intent(MainActivity.this, DetailsPage.class);
                i.putExtra("fullName", full_name);
                i.putExtra("stateName", state_name);

                Bundle args = new Bundle();
                args.putSerializable("educationDetails", educationDataModelList);
                i.putExtra("bundle", args);

                startActivity(i);

            }
        });
    }
    public void createLayout(){
        COUNT++;
        final LinearLayout layout1 = new LinearLayout(this);
        layout1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setId(COUNT);

        EditText editText1 = new EditText(layout1.getContext());
        editText1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        editText1.setHint("Education");
        editText1.setId(COUNT*10 + 1);

        EditText editText2 = new EditText(layout1.getContext());
        editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        editText2.setHint("School");
        editText2.setId(COUNT*10 + 2);

        LinearLayout layout2 = new LinearLayout(layout1.getContext());
        layout2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout2.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(layout2.getContext());
        textView.setText("Present: ");

        CheckBox checkBox = new CheckBox(layout2.getContext());
        checkBox.setId(COUNT*10 + 3);

        MaterialButton removeButton = new MaterialButton(layout2.getContext());
        removeButton.setLayoutParams(new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT));
        removeButton.setText("Remove");

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removedLayouts.add(layout1.getId());
                mainLayout.removeView(layout1);
            }
        });

        layout2.addView(textView);
        layout2.addView(checkBox);
        layout2.addView(removeButton);

        layout1.addView(editText1);
        layout1.addView(editText2);
        layout1.addView(layout2);

        mainLayout.addView(layout1);
    }
}
package com.hector.task_2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsPage extends AppCompatActivity {

    TextView fullName, stateName;
    LinearLayout detailsPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fullName = findViewById(R.id.details_page_name);
        stateName = findViewById(R.id.details_page_state);
        detailsPage = findViewById(R.id.details_page_layout);

        Intent i = getIntent();

        fullName.setText("Full Name: "+i.getStringExtra("fullName"));
        stateName.setText("Sate: "+i.getStringExtra("stateName"));

        Bundle args = i.getBundleExtra("bundle");

        ArrayList<EducationDataModel> dataModelArrayList= (ArrayList<EducationDataModel>) args.getSerializable("educationDetails");

        for(int j=0; j < dataModelArrayList.size(); j++){
            createTexts(j+1,dataModelArrayList.get(j).education, dataModelArrayList.get(j).school, dataModelArrayList.get(j).present);
        }
    }

    private void createTexts(int count, String education, String school, boolean present) {


        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(0,30,0,5);

        TextView count_txt = new TextView(this);
        count_txt.setLayoutParams(params1);
        count_txt.setText("Education-"+count);
        count_txt.setTextSize(18);
        count_txt.setTypeface(ResourcesCompat.getFont(this, R.font.amaranth));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView edu = new TextView(detailsPage.getContext());
        edu.setLayoutParams(params);
        TextView scl = new TextView(detailsPage.getContext());
        scl.setLayoutParams(params);
        TextView prst = new TextView(detailsPage.getContext());
        prst.setLayoutParams(params);

        edu.setText("Education: "+education);
        edu.setTextSize(18);
        edu.setTypeface(ResourcesCompat.getFont(this, R.font.amaranth));

        scl.setText("School: "+ school);
        scl.setTextSize(18);
        scl.setTypeface(ResourcesCompat.getFont(this, R.font.amaranth));

        prst.setText("Present: "+(present?"YES":"NO"));
        prst.setTextSize(18);
        prst.setTypeface(ResourcesCompat.getFont(this, R.font.amaranth));


        detailsPage.addView(count_txt);
        detailsPage.addView(edu);
        detailsPage.addView(scl);
        detailsPage.addView(prst);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
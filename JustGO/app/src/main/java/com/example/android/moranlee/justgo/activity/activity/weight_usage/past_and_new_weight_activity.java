package com.example.android.moranlee.justgo.activity.activity.weight_usage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.activity.MainMenu;
import com.example.android.moranlee.justgo.activity.sql_interaction.WeightRepo;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;

public class past_and_new_weight_activity extends AppCompatActivity
{

    Button submit;

    EditText weightValue;

    WeightRepo weigthRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        submit = (Button) findViewById(R.id.weight_submit);
        weightValue = (EditText)findViewById(R.id.new_weight_value);
        weigthRepo = new WeightRepo(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weightValue.getText().length() <= 0
                        || Double.parseDouble(weightValue.getText().toString()) <= 0) {
                    Toast.makeText(past_and_new_weight_activity.this,
                                   "Can not add new weight with no value / negative value",
                                   Toast.LENGTH_SHORT).show();
                } else {
                    weigthRepo.insert(weigthRepo.createNewWeight(Double.parseDouble(
                                          weightValue.getText().toString())));
                    Intent goBack = new Intent(getItSelf(), MainMenu.class);
                    startActivity(goBack);
                }
            }
        });
        ArrayList<HashMap<String, String>> weightDatas = weigthRepo.get_all_weight(
                    GlobalVariables.getG_CurrentUserId());
        GraphView graph = (GraphView) findViewById(R.id.weight_graph);
        DataPoint [] some = new DataPoint[weightDatas.size()];
        for (int i = 0; i < weightDatas.size(); i++) {
            some[i] = new DataPoint(i,
                                    Double.parseDouble(weightDatas.get(i).get("weight")));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(some);
        graph.addSeries(series);

// legend
        series.setTitle("user's weight change");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

    }

    private Activity getItSelf()
    {
        return this;
    }


}

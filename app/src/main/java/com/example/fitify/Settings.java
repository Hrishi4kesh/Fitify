package com.example.fitify;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class Settings extends AppCompatActivity {

    private TextView stepsTextView;
    private Button changeObjButton;
    private FitnessViewModel fitnessViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        fitnessViewModel = new ViewModelProvider(this).get(FitnessViewModel.class);
    }

   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_settings, container, false);

        stepsTextView = rootView.findViewById(R.id.steps);
        changeObjButton = rootView.findViewById(R.id.changeObj);

        showObjectiveSteps(getContext());
        changeObjButton.setOnClickListener(v -> showObjectiveDialog(requireContext()));

        return rootView;
    }*/

    private void showObjectiveSteps(Context context) {
        stepsTextView.setText(String.valueOf(fitnessViewModel.loadObjectiveSteps(context)));
    }

    private void showObjectiveDialog(Context context) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_settings, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle("Select Objective Steps");

        NumberPicker objectiveSeekBar = dialogView.findViewById(R.id.steps);
        objectiveSeekBar.setMinValue(8);
        objectiveSeekBar.setMaxValue(40);
        objectiveSeekBar.setValue(fitnessViewModel.loadObjectiveSteps(context) / 1000);


        objectiveSeekBar.setFormatter(value -> String.valueOf(value * 1000));


        dialogBuilder.setPositiveButton("Save", (dialog, which) -> {
            int newObjectiveSteps = objectiveSeekBar.getValue() * 1000;
            fitnessViewModel.saveObjectiveSteps(context, newObjectiveSteps);
            showObjectiveSteps(context);
            Toast.makeText(context, "Objective steps saved", Toast.LENGTH_SHORT).show();
        });


        dialogBuilder.create().show();
    }
   /* public settings(){
        Function0 ownerProducer$iv = (Function0)(new settings());
        Function0 extraProducer$iv = null;
        Function0 factoryProducer$iv = null;
        // int viewModels = false;

    }*/
}



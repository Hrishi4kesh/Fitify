package com.example.fitify;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;


public class Settings extends Fragment {

    private TextView stepsTextView;
    private Button changeObjButton;
    private FitnessViewModel fitnessViewModel;

  /*  private final FitnessViewModel getFitnessViewModel(){
        Lazy<FitnessViewModel> var1 = this.fitnessViewModelDelegate;
        return (FitnessViewModel) var1.getValue();
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fitnessViewModel = new ViewModelProvider(this).get(FitnessViewModel.class);
    }
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View rootView=inflater.inflate(R.layout.fragment_settings,container,false);
        View stepsTextView=rootView.findViewById(R.id.steps);
        stepsTextView=rootView.findViewById(R.id.steps);


            return rootView;
    }

    private final void showObjectiveSteps(Context context) throws IllegalAccessException {
        TextView stepsTextView = this.stepsTextView;
        if (stepsTextView == null) {
            throw new IllegalAccessException("stepsTextView must not be null");
        }
        // stepsTextView.setText(String.valueOf(this.getFitnessViewModel().leadObjectiveSteps(context)));
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
            try {
                showObjectiveSteps(context);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Toast.makeText(context, "Objective steps saved", Toast.LENGTH_SHORT).show();
        });

        dialogBuilder.create().show();


    }


    /* public Setting() {
    Function0<SettingViewModel> ownerProducer$iv = new Function0<SettingViewModel>() {
        @Override
        public SettingViewModel invoke() {
            return new SettingViewModel(Setting.this);
        }
    };
    Function0<?> extrasProducer$iv = null;
    Function0<?> factoryProducer$iv = null;
    int $i$f$viewModels = false;
    Lazy<SettingViewModel> owner$delegate$iv = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0<SettingViewModel>() {
        @Override
        public SettingViewModel invoke() {
            return new SettingViewModel(Setting.this);
        }
    });
    this.fitnessViewModelDelegate = FragmentViewModelLazyKt.createViewModelLazy(
            this,
            Reflection.getOrCreateKotlinClass(FitnessViewModel.class),
            new Function0<SettingViewModel>() {
                @Override
                public SettingViewModel invoke() {
                    return (SettingViewModel) owner$delegate$iv.getValue();
                }
            },
            new Function0<SettingViewModel>() {
                @Override
                public SettingViewModel invoke() {
                    return (SettingViewModel) extrasProducer$iv.invoke();
                }
            },
            new Function0<SettingViewModel>() {
                @Override
                public SettingViewModel invoke() {
                    return new SettingViewModel(Setting.this);
                }
            }
    );
}*/




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
}



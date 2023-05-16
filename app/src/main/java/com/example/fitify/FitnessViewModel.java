package com.example.fitify;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.ViewModel;

public class FitnessViewModel extends ViewModel {
    private static final String PREFERENCES_FILE_NAME = "com.fitnesstracker.settings";
    private static final String OBJECTIVE_STEPS_KEY = "objective_steps";

    public int loadObjectiveSteps(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(OBJECTIVE_STEPS_KEY, 8000);
    }

    public void saveObjectiveSteps(Context context, int objectiveSteps) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(OBJECTIVE_STEPS_KEY, objectiveSteps);
        editor.apply();
    }
}

package com.example.fitify;

public class DailyFitnessModel {
    private final int stepCount;
    private final int caloriesBurned;
    private final int distance;

    public DailyFitnessModel(int stepCount, int caloriesBurned, int distance) {
        this.stepCount = stepCount;
        this.caloriesBurned = caloriesBurned;
        this.distance = distance;
    }
    public int getStepCount(){  return this.stepCount;
    }

    public int getCaloriesBurned() {
        return this.caloriesBurned;
    }

    public int getDistance() {
        return this.distance;
    }
}

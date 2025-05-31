package com.example.latihan_database_sqlite.presentation.ui.fragment;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latihan_database_sqlite.R;

public class SettingsFragment extends Fragment {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightListener;
    private TextView textLightStatus;
    private TextView textLightValue;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textLightStatus = view.findViewById(R.id.text_light_status);
        textLightValue = view.findViewById(R.id.text_light_value);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor != null) {
                lightListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        float lightValue = event.values[0];
                        textLightValue.setText("Lux: " + lightValue);

                        if (lightValue < 10) {
                            textLightStatus.setText("Cahaya terlalu redup");
                        } else {
                            textLightStatus.setText("Cahaya cukup terang");
                        }
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {
                        // Tidak digunakan
                    }
                };
            } else {
                textLightStatus.setText("Sensor cahaya tidak tersedia");
            }
        } else {
            Toast.makeText(getContext(), "SensorManager tidak tersedia", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lightSensor != null && lightListener != null) {
            sensorManager.registerListener(lightListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (lightSensor != null && lightListener != null) {
            sensorManager.unregisterListener(lightListener);
        }
    }
}

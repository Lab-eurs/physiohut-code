package com.example.physiohut;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R9Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class R9Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public R9Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment R9Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static R9Fragment newInstance(String param1, String param2) {
        R9Fragment fragment = new R9Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_r9, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_r9Fragment_to_patientFragment);
                        break;
                }
                return false;
            }
        });
        //koumpi calendar
        Button submit = view.findViewById(R.id.calendarbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });

    }
    private String selectedDate;
    //dhmiourgia custom calendar pop-up
    private void showPopup(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.calendarforr9);
        dialog.show();


        CalendarView calendarView = dialog.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            }
        });

        //koumpi wres
        Button select = dialog.findViewById(R.id.buttoncal);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showHourPopup(selectedDate);
            }
        });
    }

    private String time;
    //dhmiourgia custom hour pop-up
    private String showHourPopup(String date) {

        Dialog hourDialog = new Dialog(getContext());
        hourDialog.setContentView(R.layout.hoursforr9);
        hourDialog.show();

        TextView textView = hourDialog.findViewById(R.id.day2);
        textView.setText(date);

        Button firstButton = hourDialog.findViewById(R.id.button22);
        Button secondButton = hourDialog.findViewById(R.id.button23);
        Button thirdButton = hourDialog.findViewById(R.id.button24);
        Button fourthButton = hourDialog.findViewById(R.id.button25);
        Button fifthButton = hourDialog.findViewById(R.id.button26);
        Button sixthButton = hourDialog.findViewById((R.id.button27));
        Button submitButton = hourDialog.findViewById(R.id.submitbutton);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstButton.setBackgroundColor(getResources().getColor(R.color.button));
                secondButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                thirdButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fourthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fifthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                sixthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                time = "9:00 - 11:00";
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                secondButton.setBackgroundColor(getResources().getColor(R.color.button));
                thirdButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fourthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fifthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                sixthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                time = "11:00 - 13:00";
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                secondButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                thirdButton.setBackgroundColor(getResources().getColor(R.color.button));
                fourthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fifthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                sixthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                time = "13:00 - 15:00";
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                secondButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                thirdButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fourthButton.setBackgroundColor(getResources().getColor(R.color.button));
                fifthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                sixthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                time = "15:00 - 17:00";
            }
        });

        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                secondButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                thirdButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fourthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fifthButton.setBackgroundColor(getResources().getColor(R.color.button));
                sixthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                time = "17:00 - 19:00";
            }
        });

        sixthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                secondButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                thirdButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fourthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                fifthButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral60));
                sixthButton.setBackgroundColor(getResources().getColor(R.color.button));
                time = "19:00 - 21:00";
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Ραντεβού έκλεισε για " + time + date, Toast.LENGTH_LONG).show();
                hourDialog.dismiss();
            }
        });
        return  time;
    }

    //NA VALW SWSTO RETURN, NA MOU DWSEI TO ALERT H KWNNA


    //Confirmation alert gia to rantevou



}
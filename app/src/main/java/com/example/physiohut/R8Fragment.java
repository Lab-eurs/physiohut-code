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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R8Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class R8Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public R8Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment R8Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static R8Fragment newInstance(String param1, String param2) {
        R8Fragment fragment = new R8Fragment();
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
        return inflater.inflate(R.layout.fragment_r8, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //koumpi gia paroxes
        Button provisionBtn = view.findViewById(R.id.ProvitionsBtn);
        provisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_r8Fragment_to_provisionFragment);
            }
        });

        //navigation bar leitourgikotita
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_r8Fragment_to_doctorFragment);
                        break;
                }
                return false;
            }
        });

        //koumph upobolhs
        Button submit = view.findViewById(R.id.Submitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void updateTextView(String newText) {
        TextView textView = getView().findViewById(R.id.Provisions);
        textView.setText(newText);
    }
    private String selectedDate;
    //dhmiourgia custom popup gia to calendar
    private void showDialog(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.calendar);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);
        dialog.show();


        CalendarView calendarView = dialog.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            }
        });

        //koumpi gia tis wres
        Button select = dialog.findViewById(R.id.calendarSelection);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showHourDialog(selectedDate);
            }
        });
    }

    //dhmiourgia custom pop up gia tis wres
    private void showHourDialog(String date){
        Dialog hourDialog = new Dialog(getContext());
        hourDialog.setContentView(R.layout.hours);
        hourDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);
        hourDialog.show();

        TextView textView = hourDialog.findViewById(R.id.day);
        textView.setText(date);

        Button firstButton = hourDialog.findViewById(R.id.button11);
        Button secondButton = hourDialog.findViewById(R.id.button13);
        Button thirdButton = hourDialog.findViewById(R.id.button14);
        Button forthButton = hourDialog.findViewById(R.id.button15);
        Button fifthButton = hourDialog.findViewById(R.id.button16);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Ραντεβού έκλεισε για 9:00- 11:00 "+ date, Toast.LENGTH_LONG).show();
                hourDialog.dismiss();
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Ραντεβού έκλεισε για 11:00- 13:00 "+ date, Toast.LENGTH_LONG).show();
                hourDialog.dismiss();
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Ραντεβού έκλεισε για 13:00- 15:00 "+ date, Toast.LENGTH_LONG).show();
                hourDialog.dismiss();
            }
        });

        forthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Ραντεβού έκλεισε για 17:00- 19:00 "+ date, Toast.LENGTH_LONG).show();
                hourDialog.dismiss();
            }
        });

        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Ραντεβού έκλεισε για 19:00- 21:00 "+ date, Toast.LENGTH_LONG).show();
                hourDialog.dismiss();
            }
        });
    }
}
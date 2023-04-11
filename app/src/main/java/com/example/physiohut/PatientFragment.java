package com.example.physiohut;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientFragment newInstance(String param1, String param2) {
        PatientFragment fragment = new PatientFragment();
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
        return inflater.inflate(R.layout.fragment_patient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button createAppointment = (Button) view.findViewById(R.id.appointmentbtn);
        createAppointment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_patientFragment_to_r9Fragment);
            }
        });

        Button economicHistory = (Button) view.findViewById(R.id.economicbtn);
        economicHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_patientFragment_to_r10Fragment);
            }
        });

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                        Navigation.findNavController(view).navigate(R.id.action_patientFragment_to_mainFragment);
                        break;
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_patientFragment_self);
                        break;
                }
                return false;
            }
        });

    }
}
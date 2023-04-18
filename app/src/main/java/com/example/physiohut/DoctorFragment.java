package com.example.physiohut;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorFragment newInstance(String param1, String param2) {
        DoctorFragment fragment = new DoctorFragment();
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
        return inflater.inflate(R.layout.fragment_doctor, container, false);
    }

    ListView listView;
    List<String> friends = new ArrayList<String>();
    String [] startingList = {"Ραντεβού #1", "Ραντεβού #2", "Ραντεβού #3", "Ραντεβού #4", "Ραντεβού #5", "Ραντεβού #6"};
    ArrayAdapter<String> ad;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = (ListView) view.findViewById(R.id.listview);
        friends = new ArrayList<String>(Arrays.asList(startingList));
        ad = new ArrayAdapter<String>(getParentFragment().getContext(), android.R.layout.simple_list_item_1,friends);
        listView.setAdapter(ad);

        TabHost th = (TabHost) view.findViewById(R.id.patientandoc);
        th.setup();

        TabHost.TabSpec ts = th.newTabSpec("Ασθενής");
        ts.setContent(R.id.patient);
        ts.setIndicator("Ασθενής");
        th.addTab(ts);

        TabHost.TabSpec ts2 = th.newTabSpec("ραντεβού");
        ts2.setContent(R.id.appointment);
        ts2.setIndicator("Ραντεβού");
        th.addTab(ts2);


        Button patientHistory = (Button) view.findViewById(R.id.btnR4);
        patientHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_r4Fragment);
            }
        });

        FloatingActionButton addPatient = (FloatingActionButton) view.findViewById(R.id.btnR3);
        addPatient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_r3Fragment);
            }
        });

        FloatingActionButton reqappointment = (FloatingActionButton) view.findViewById(R.id.btnR7);
        reqappointment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_r7Fragment);
            }
        });

        FloatingActionButton createAppointment = (FloatingActionButton) view.findViewById(R.id.btnR8);
        createAppointment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_r8Fragment);
            }
        });

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                        Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_mainFragment);
                        break;
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_doctorFragment_self);
                        break;
                }
                return false;
            }
        });

    }
}
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorFragment extends Fragment implements SearchView.OnQueryTextListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;
    private ArrayAdapter adapter;
    private SearchView searchView;
    private ArrayList<Doctor> doctorArrayList = new ArrayList<>();

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

    String app_list[] = {"Ραντεβού #1","Ραντεβού #2","Ραντεβού #3","Ραντεβού #4","Ραντεβού #5","Ραντεβού #6","Ραντεβού #7","Ραντεβού #8","Ραντεβού #9","Ραντεβού #10"};
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView =  view.findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getParentFragment().getContext(), R.layout.activity_listview, R.id.textView, app_list);
        listView.setAdapter(arrayAdapter);





        TabHost th = (TabHost) view.findViewById(R.id.patientandoc);
        th.setup();

        TabHost.TabSpec ts = th.newTabSpec("Ασθενής");
        ts.setContent(R.id.appointment);
        ts.setIndicator("Ασθενής");
        th.addTab(ts);

        TabHost.TabSpec ts2 = th.newTabSpec("ραντεβού");
        ts2.setContent(R.id.date);
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


        // Find the list view and set its adapter
        listView = view.findViewById(R.id.list_view);
        ArrayList<String> itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        // Find the search view and set its listener
        searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        System.out.println("submit");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        System.out.println("change");
        return false;
    }
}
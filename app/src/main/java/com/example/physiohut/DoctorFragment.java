package com.example.physiohut;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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
    private final String ip = "192.168.1.66";

    private final String myIP = "192.168.1.4";

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

    ArrayList<String> app_list = new ArrayList<>();


    private AppointmentsList cbl;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cbl = new AppointmentsList(myIP);
        super.onViewCreated(view, savedInstanceState);

        listView =  view.findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getParentFragment().getContext(), R.layout.activity_listview, R.id.textView, cbl.getNames());
        listView.setAdapter(arrayAdapter);
        app_list.add("Ραντεβού #1");
        app_list.add("Ραντεβού #2");
        app_list.add("Ραντεβού #3");
        app_list.add("Ραντεβού #4");
        app_list.add("Ραντεβού #5");
        listView =(ListView) view.findViewById(R.id.listView);
        CustomDoctorAdapter customDoctorAdapter = new CustomDoctorAdapter(getContext(), app_list);
        listView.setAdapter(customDoctorAdapter);


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

        // Find the list view, create dataArray, ArrayList and set url
        listView = view.findViewById(R.id.list_view);
        ArrayList<String> dataArray = new ArrayList<>();
        String url= "http://"+ip+"/GetPatientsNames.php?";

        //take the data with OkHttpHandler class and put them in dataArray
       try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            dataArray = okHttpHandler.getPatientNames(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Put the data in the listView
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, dataArray);
        // Find the list view and set its adapter
        listView = view.findViewById(R.id.list_view);
        ArrayList<String> itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        // Find the search view and set its listener and set an extra arrayList for the search
        searchView = view.findViewById(R.id.search_view);
        ArrayList<String> finalDataArray = dataArray;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }

            private void filterData(String query) {
                List<String> filteredDataList = new ArrayList<>();

                // Filter the original data list based on the query
                for (String item : finalDataArray) {
                    if (item.toLowerCase().contains(query.toLowerCase())) {
                        filteredDataList.add(item);
                    }
                }

                // Update the adapter with the filtered data
                adapter.clear();
                adapter.addAll(filteredDataList);
                adapter.notifyDataSetChanged();
            }
        });



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

    public class CustomDoctorAdapter extends ArrayAdapter<String> {

        public CustomDoctorAdapter(Context context, List<String> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Inflate the list item layout
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview, parent, false);
            }

            // Get the current item from the data source
            String currentItem = getItem(position);

            // Set the text or perform any other operations on other views in the item layout
            TextView nameTextView = convertView.findViewById(R.id.textView);
            nameTextView.setText(currentItem);
            // Get the button reference and set the click listener
            Button buttonItem = convertView.findViewById(R.id.item_btn);
            buttonItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProvisionPatients provisionPatients = new ProvisionPatients();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.doctorhome, provisionPatients);
                    transaction.addToBackStack("tag");

                    transaction.commit();
                }
            });

            return convertView;
        }
    }


}
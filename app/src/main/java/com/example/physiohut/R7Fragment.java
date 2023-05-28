package com.example.physiohut;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class R7Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //Declarations
    private ArrayList<Appointments> recArrayList;
    private static final R7DataFetcher dbFetcher = new R7DataFetcher();
    private Adapter myAdapter;
    private Spinner spinner;
    private SearchView searchViewR7;

    public R7Fragment() {
        // Required empty public constructor
    }

    public static R7Fragment newInstance(String param1, String param2) {
        R7Fragment fragment = new R7Fragment();
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
            getArguments().getString(ARG_PARAM1);
            getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_r7, container, false);

        //Sort-by spinner init.
        spinner = view.findViewById(R.id.sortBySpinner);
        //Layout setup.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sortBySpinner,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); //Spinner set index=0.

        searchViewR7 = view.findViewById(R.id.searchR7);
        searchViewR7.clearFocus(); //removes cursor from search - may otherwise cause issues on lower APIs.
        searchViewR7.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty()){
                    searchViewR7.clearFocus();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        return view;
    }

    //Search logic.
    @SuppressLint("NotifyDataSetChanged")
    private void filterList(String text) {

        ArrayList<Appointments> filteredList = new ArrayList<>();

        for(Appointments item : recArrayList) {
            if (item.getPatientName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(),"no data found",Toast.LENGTH_SHORT).show();
        }
        else {
            sortBy(spinner.getSelectedItemPosition(),filteredList);
            myAdapter.setFilteredList(filteredList);
            myAdapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Navigation menu code.
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.back:
                case R.id.home:
                    Navigation.findNavController(view).navigate(R.id.action_r7Fragment_to_doctorFragment);
                    break;
            }
            return false;
        });

        //Main RecyclerView ArrayList.
        recArrayList = new ArrayList<>();


        //RecyclerView setup and init.
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewR7);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Sort-by routine.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortBy(position,recArrayList); //Sends the index of the selected spinner item & returns a sorted list.
                filterList(searchViewR7.getQuery().toString());
                myAdapter.notifyDataSetChanged(); //update recyclerView.
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        recArrayList = dbFetcher.fetchAppointmentsFromDB(); //Fetch data from DB
        myAdapter = new Adapter(getContext(),recArrayList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    //Sorts the recyclerView's list by the spinners index value.
    public static void sortBy(int position, ArrayList<Appointments> arrayList){
        arrayList.sort((o1, o2) -> {
            if (position == 0) {
                //A-Z sort
                return o1.getPatientName().compareToIgnoreCase(o2.getPatientName());
            }
            else {
                //Z-A sort
                return o2.getPatientName().compareToIgnoreCase(o1.getPatientName());
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        String index = Integer.toString(position);
        Toast.makeText(parent.getContext(),text + "at: index" + index,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
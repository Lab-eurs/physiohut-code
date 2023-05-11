package com.example.physiohut;

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
    private ArrayList<Appointments> recArrayList;
    Adapter myAdapter;
    Spinner spinner;

    SearchView searchViewR7;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_r7, container, false);

        spinner = view.findViewById(R.id.sortBySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sortBySpinner,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Spinner init.
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); //Spinner set index=0 ?.

        searchViewR7 = view.findViewById(R.id.searchR7);
        searchViewR7.clearFocus(); //removes cursor from search.
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

    //Function used to search recyclerview
    private void filterList(String text) {

        ArrayList<Appointments> filteredList = new ArrayList<>();

        for(Appointments item : recArrayList){
            if(item.getPatientName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(getContext(),"no data found",Toast.LENGTH_SHORT).show();
        } else {

            int itemPos = spinner.getSelectedItemPosition();
            sortBy(itemPos,filteredList);
            myAdapter.setFilteredList(filteredList);
            myAdapter.notifyDataSetChanged();

        }

    }

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

        //RecyclerView contents in ArrayList. 
        recArrayList = new ArrayList<>();
        getData(recArrayList); //Insert List with dummy data till i connect the DB.

        //RecyclerView setup and init.
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewR7);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Sort-by routine.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //EDW EINAI TO BUG ME TO SEARCH
                //String idkpos = Integer.toString(position);
                //Toast.makeText(getContext(),"the id is:" + idkpos,Toast.LENGTH_SHORT).show();
                sortBy(position,recArrayList); //Sends the index of the selected spinner item & returns a sorted list.
                // myAdapter.setFilteredList(recArrayList);//idk about that
                myAdapter.notifyDataSetChanged(); //update recyclerView.
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // myAdapter.notifyDataSetChanged();//idk if it's doing anything to be honest.
            }
        });

        myAdapter = new Adapter(getContext(),recArrayList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }
    //Inserts data to the recyclerView's main list
    public static void getData(ArrayList<Appointments> arrayList){
        arrayList.add(new Appointments("Giwrgos giorgou", "11/4/2023", "thessaloniki", "13:00PM"));
        arrayList.add(new Appointments("Antreas nikou", "14/2/2023", "thessaloniki", "13:00PM"));
        arrayList.add(new Appointments("Mixalis Papadopoulos", "11/4/2023", "thessaloniki", "13:00PM"));
        arrayList.add(new Appointments("Giwrgos giannou", "11/8/2023", "thessaloniki", "13:00PM"));
        arrayList.add(new Appointments("Giwrgos giorgou", "12/1/2023", "thessaloniki", "13:00PM"));
        arrayList.add(new Appointments("Vaggelis evaggelou", "11/4/2023", "thessaloniki", "13:00PM"));
    }
    //rts the recyclerView's list in the context of the spinners index.
    public static void sortBy(int position, ArrayList<Appointments> arrayList){
        arrayList.sort((o1, o2) -> {

            if (position == 0) {
                //A-Z sort
                return o1.patientName.compareToIgnoreCase(o2.patientName);

            } else if (position == 1) {
                //Z-A sort
                return o2.patientName.compareToIgnoreCase(o1.patientName);

            } else if (position == 2) {
                //Date Acceding !TODO NEEDS FIXING
                return o1.appointmentDate.compareToIgnoreCase(o2.appointmentDate);

            } else {
                //Date Descending !TODO NEEDS FIXING
                return o2.appointmentDate.compareToIgnoreCase(o1.appointmentDate);
            }

        });

    }
    //not really needed but ok. show's the spinner's selection
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
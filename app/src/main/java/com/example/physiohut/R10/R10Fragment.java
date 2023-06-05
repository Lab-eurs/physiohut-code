package com.example.physiohut.R10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.physiohut.AuthenticateUser;
import com.example.physiohut.model.Provision;
import com.example.physiohut.R;
import com.example.physiohut.model.Session;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R10Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class R10Fragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final R10DataFetcher dbFetcher = new R10DataFetcher();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private HashMap<String, Comparator<Provision>> sortingFNsMapping = new HashMap<>();


    private RecyclerView.LayoutManager layoutManagerForRV;



    private Comparator<Provision> compAscendingPrice = new Comparator<Provision>() {
        @Override
        public int compare(Provision provision, Provision t1) {
            //might be the other way around
            double distance = provision.getPrice() - t1.getPrice();
            if(distance > 0) return 1;
            if(distance < 0) return -1;
            return 0;

        }
    };
    //the rest the same etc.
    public  ArrayList<Provision> provisionData = new ArrayList<>(Arrays.asList(new Provision(1,"Malaksh","Trivw gera",15.75),
            new Provision(2,"HlektroXalarwsh","se diapernaei revma",15.75),
            new Provision(3,"Athlitiko Masaz","Ksepianesai",15.75),
            new Provision(4,"Sauna","Idrwneis, kanei kalo",15.75)));


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public R10Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment R10Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static R10Fragment newInstance(String param1, String param2) {
        R10Fragment fragment = new R10Fragment();
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


    //TODO: PUT BORDER IN RECYCLER VIEW-> DONE
    //TODO: CHANGE TEXT SIZES AND BOLD IN RECYCLER VIEW-> DONE
    //TODO: CHANGE SCROLLBAR -> DONE
    //TODO: PUT BORDER IN RECYCLER VIEW ELEMENTS
    //TODO: FIX THE BORDER THING THAT HIDES THE LITTLE ARROW IN THE SPINNER
    //TODO: CHANGE TEXT COLORS TO MATCH THOSE OF MOCK-UP
    //TODO: FIX SCROLLBAR JUST LIKE LINES WITH STROKES AND STYLE ETC -> DONE
    //TODO: SPACE ELEMENTS AROUND BETTER
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_r10, container, false);
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
                        Navigation.findNavController(view).navigate(R.id.action_r10Fragment_to_patientFragment);
                        break;
                }
                return false;
            }
        });

        this.sortingFNsMapping.put("Αύξον Κόστος",this.compAscendingPrice);
        this.sortingFNsMapping.put("Φθίνον Κόστος",this.compAscendingPrice);
        this.sortingFNsMapping.put("Πιο πρόσφατες",this.compAscendingPrice);
        this.sortingFNsMapping.put("Πιο παλιές",this.compAscendingPrice);

        Spinner sortSpinner = (Spinner) getView().findViewById(R.id.sort_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.sort_choices_array,R.layout.sort_spinner_item);
        adapter.setDropDownViewResource(R.layout.sort_spinner_item);
        sortSpinner.setAdapter(adapter);

        TextView totalAmount = (TextView) getView().findViewById(R.id.payment_amount);
        //TODO: ME AUTO NA DOUME TI THA KANOUME
        int currentPatient = AuthenticateUser.patientID;
        ArrayList<Session> sessions = dbFetcher.fetchCompletedSessionsOfPatient(currentPatient);
        Double paidAmount = sessions.stream().reduce(0.0,(acc,curr)-> acc + curr.getProvision().getPrice(),Double::sum);
        totalAmount.setText( paidAmount + "$");


        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.provision_recycler_view);
        recyclerView.setAdapter(new SessionRecyclerAdapter(sessions));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String selectedItem = (String) adapterView.getItemAtPosition(pos);
                Toast.makeText(R10Fragment.super.getContext(),"The item is: " + selectedItem,Toast.LENGTH_LONG);
                //System.out.println("Hello there: " + selectedItem);
                String[] validOptions = getResources().getStringArray(R.array.sort_choices_array);

                if(!Arrays.stream(validOptions).anyMatch(str -> selectedItem.equals(str))){
                    System.out.println("SOMETHING WENT TERRIBLY WRONG");
                }
                Comparator<Provision> sortingFN = sortingFNsMapping.getOrDefault(selectedItem,(Provision p1,Provision p2)-> 0);
                Collections.sort((List) provisionData,sortingFN);

                System.out.println("The list is finally " + provisionData);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
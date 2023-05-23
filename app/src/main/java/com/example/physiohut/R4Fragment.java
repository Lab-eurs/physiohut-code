package com.example.physiohut;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class R4Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String imerominia = "";
    String paroxi = "";

    public R4Fragment(String imerominia, String paroxi) {
        this.imerominia = imerominia;
        this.paroxi = paroxi;
    }

    public String getImerominia() {
        return imerominia;
    }

    public void setImerominia(String imerominia) {
        this.imerominia = imerominia;
    }

    public String getParoxi() {
        return paroxi;
    }

    public void setParoxi(String paroxi) {
        this.paroxi = paroxi;
    }

    public R4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment R4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static R4Fragment newInstance(String param1, String param2) {
        R4Fragment fragment = new R4Fragment();
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
        return inflater.inflate(R.layout.fragment_r4, container, false);
    }

    private final String myIP = "172.20.10.2";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Provision> provisions = new ArrayList<>();

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_r4Fragment_to_doctorFragment);
                        break;
                }
                return false;
            }
        });

//        // ArrayList<String> provisions = new ArrayList<>();
//        provisions.add("Παροχη#1");
//        provisions.add("Παροχη#2");
//        provisions.add("Παροχη#3");
//        provisions.add("Παροχη#4");
//        provisions.add("Παροχη#5");
//        provisions.add("Παροχη#6");
//        provisions.add("Παροχη#7");
//        provisions.add("Παροχη#8");
//        provisions.add("Παροχη#9");
//        provisions.add("Παροχη#10");
//        provisions.add("Παροχη#11");
//        provisions.add("Παροχη#12");
//        provisions.add("Παροχη#13");
//        provisions.add("Παροχη#14");
//        provisions.add("Παροχη#15");
//        provisions.add("Παροχη#16");
//        ArrayList<String> dates = new ArrayList<>();
//        dates.add("22-01-2021    ");
//        dates.add("02-02-2021    ");
//        dates.add("08-02-2021    ");
//        dates.add("30-06-2021    ");
//        dates.add("14-07-2021    ");
//        dates.add("20-09-2021    ");
//        dates.add("27-12-2021    ");
//        dates.add("10-03-2022    ");
//        dates.add("20-04-2022    ");
//        dates.add("06-05-2022    ");
//        dates.add("17-05-2022    ");
//        dates.add("04-08-2022    ");
//        //dates.add("11-11-2022    ");
//        //dates.add("29-03-2023    ");
//        //dates.add("22-04-2023    ");
//        // dates.add("19-05-2023    ");
        String url = "http://"+myIP+"/physiohut/populatePatientHistory.php";
        try{
            R4DB dbFETCHER = new R4DB();
            provisions = dbFETCHER.PopulateRecycleView(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_main);
        recyclerView.setAdapter(new MyAdapter(provisions));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    // MyAdapter class
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private ArrayList<Provision> dataList;

        public MyAdapter(ArrayList<Provision> provisions) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_history_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            // Get the data for the current position
            //Patient data = dataList.get(position);
            Provision p = dataList.get(position);
            // Update the view holder with the new data
            holder.textHmeromhnia.setText(p.getDate());
            holder.textParoxi.setText(p.getName());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    // MyViewHolder class
    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textHmeromhnia, textParoxi;

        public MyViewHolder(View itemView) {
            super(itemView);
            textHmeromhnia = itemView.findViewById(R.id.textImerominia);
            textParoxi = itemView.findViewById(R.id.textParoxi);
        }
    }
}
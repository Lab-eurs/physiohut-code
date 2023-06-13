package com.example.physiohut.R4;

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

import com.example.physiohut.DoctorFragment;
import com.example.physiohut.R1.R1DataFetcher;
import com.example.physiohut.R10.R10DataFetcher;
import com.example.physiohut.R10.R10Fragment;
import com.example.physiohut.model.Provision;
import com.example.physiohut.R;
import com.example.physiohut.model.Session;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class R4Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PATIENT_ID = "patient_id";
    private int patient_id;
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM1 = "param1";


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
        System.out.println(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            patient_id = getArguments().getInt(ARG_PATIENT_ID);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_r4, container, false);
    }

    private final String myIP = "192.168.205.51";
    private final int doc_id = 1;
    private static final R4DataFetcher dbfetcher = new R4DataFetcher();
    private ArrayList<Provision> provisions;
    private ArrayList<Session> sessions;
    ArrayList<Provision> adapterProv = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("ID is: "+ patient_id);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_doctorFragment_self);
                        break;
                }
                return false;
            }
        });
        TextView patientTitleTextV = (TextView) view.findViewById(R.id.textHistory);
        patientTitleTextV.setText("Ιστορικό Ασθενή " + patient_id);

        try{
            R10DataFetcher dbFETCHER = new R10DataFetcher();
            sessions = dbFETCHER.fetchCompletedSessionsOfPatient(patient_id);
        }catch (Exception e){
            e.printStackTrace();
        }

//        System.out.println("THE PROVISIONS ARE " + provisions);
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_main);
        recyclerView.setAdapter(new RecyclerAdapter(sessions));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }




    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PatientSessionsViewHolder>{


        public RecyclerAdapter(ArrayList<Session> data){
            this.localData = data;
        }

        private ArrayList<Session> localData;
        @NonNull
        @Override
        public PatientSessionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_history_layout, parent, false);

            return new PatientSessionsViewHolder(view);

        }


        @Override
        public void onBindViewHolder(@NonNull PatientSessionsViewHolder holder, int position) {
            Session p = localData.get(position);
            holder.getDateTextView().setText(p.getCompletedAt());
            //TODO: put logic here that trims the text to 12 chars max or some amount
            holder.getProvisionTextView().setText(p.getProvision().getName());
//            holder.getPriceTextView().setText(String.valueOf(p.getPrice()) + "$");


        }

        @Override
        public int getItemCount() {
            return localData.size();
        }

        public class PatientSessionsViewHolder extends RecyclerView.ViewHolder {

            private final TextView provisionText;
            private final TextView dateText;

            public PatientSessionsViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View
                provisionText = (TextView) view.findViewById(R.id.textParoxi);
                dateText = (TextView) view.findViewById(R.id.textImerominia);

            }



           public TextView getProvisionTextView() {return provisionText;}

            public TextView getDateTextView(){
                return dateText;
            }

        }
    }

    // MyAdapter class
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private ArrayList<Provision> dataList;

        public MyAdapter(ArrayList<Provision> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_provision_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            // Get the data for the current position
            //Patient data = dataList.get(position);
            Provision p = dataList.get(position);
            // Update the view holder with the new data
            holder.getDateTextView().setText(p.getDate());
            //TODO: put logic here that trims the text to 12 chars max or some amount
            holder.getDescriptionTextView().setText(p.getDescription());
            holder.getPriceTextView().setText(String.valueOf(p.getPrice()) + "$");
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    // MyViewHolder class
    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView priceText;
        private final TextView descriptionText;
        private final TextView dateText;
        private TextView textHmeromhnia, textParoxi;

        public MyViewHolder(View itemView) {
            super(itemView);
            priceText = (TextView) itemView.findViewById(R.id.priceTextV);
            descriptionText = (TextView) itemView.findViewById(R.id.descriptionTextV);
            dateText = (TextView) itemView.findViewById(R.id.dateTextV);
            textHmeromhnia = itemView.findViewById(R.id.textImerominia);
            textParoxi = itemView.findViewById(R.id.textParoxi);
        }

        public TextView getPriceTextView(){
            return priceText;
        }

        public TextView getDescriptionTextView(){
            return descriptionText;
        }

        public TextView getDateTextView(){
            return dateText;
        }
    }
}
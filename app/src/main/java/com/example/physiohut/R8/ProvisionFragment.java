package com.example.physiohut.R8;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.physiohut.R;
import com.example.physiohut.model.Provision;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProvisionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProvisionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProvisionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProvisionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProvisionFragment newInstance(String param1, String param2) {
        ProvisionFragment fragment = new ProvisionFragment();
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
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_provision, container, false);
    }

    List<String> provitions = new ArrayList<String>();
    ArrayList<Provision> provisions;
    ArrayList<Provision> adapterProv = new ArrayList<>();
    private static final R8DataFetcher dbFetcher = new R8DataFetcher();
    private final String myIP = "192.168.179.235";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_r8Fragment_self);
                        break;
                }
                return false;
            }
        });
        try{
            provisions = dbFetcher.fetchProvisions();
        }catch (Exception e){
            provisions = new ArrayList<>();
        }
//        provisions = dbFetcher.fetchProvisions();
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerProv);
        recyclerView.setAdapter(new ProvisionFragment.MyProvAdapter(getAdapterProv()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Button okbtn = view.findViewById(R.id.provisionSubmit);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> provisionsName = new ArrayList<>();
                for(int i=0;i<adapterProv.size();i++){
                    if(adapterProv.get(i).isSelected()){
                        provisionsName.add(adapterProv.get(i).getName());
                    }
                    System.out.println(adapterProv.get(i).isSelected());
                }
                System.out.println(provisionsName);

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("provisionList", provisionsName);

                R8Fragment r8Fragment = new R8Fragment();
                r8Fragment.setArguments(bundle);

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, r8Fragment);
                transaction.addToBackStack("tag");

                transaction.commit();
            }
        });
    }
    private ArrayList<Provision> getAdapterProv(){
        for(int i=0; i<provisions.size();i++){
            adapterProv.add(new Provision(provisions.get(i).getName(), false));
        }
        return adapterProv;
    }

    // MyAdapter class
    public class MyProvAdapter extends RecyclerView.Adapter<MyProvAdapter.MyViewHolder> {

        private ArrayList<Provision> dataList;

        public MyProvAdapter(ArrayList<Provision> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_for_provision, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            // Get the data for the current position
            //Patient data = dataList.get(position);
            Provision provision = dataList.get(position);
            // Update the view holder with the new data
            holder.textParoxi.setText(dataList.get(position).getName());
            holder.textParoxi.setChecked(provision.isSelected());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private final CheckBox textParoxi;

            public MyViewHolder(View itemView) {
                super(itemView);
                textParoxi = (CheckBox) itemView.findViewById(R.id.checkBox);

                textParoxi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isChecked = ((CheckBox) view).isChecked();

                        if (isChecked) {
                            dataList.get(getAdapterPosition()).setSelected(true);
                        } else {
                            dataList.get(getAdapterPosition()).setSelected(false);
                        }
                        notifyDataSetChanged();
                        for (int i = 0; i < dataList.size(); i++) {
                            Log.d("TAG", dataList.toString());
                        }
                    }
                });
            }

        }
    }


    // MyViewHolder class

}
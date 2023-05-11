package com.example.physiohut;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

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
        return inflater.inflate(R.layout.fragment_provision, container, false);
    }

    List<String> provitions = new ArrayList<String>();
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
                        Navigation.findNavController(view).navigate(R.id.action_provisionFragment_to_r8Fragment);
                        break;
                }
                return false;
            }
        });

        CheckBox checkBox = view.findViewById(R.id.checkBox);
        CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        CheckBox checkBox4 = view.findViewById(R.id.checkBox4);
        CheckBox checkBox5 = view.findViewById(R.id.checkBox5);


        Bundle bundle = new Bundle();
        R8Fragment r8Fragment = new R8Fragment();
        r8Fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // Remove the previous R8Fragment instance before adding the new one
        fragmentManager.popBackStackImmediate(R8Fragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        // Add the new R8Fragment instance
        transaction.replace(R.id.fragmentContainerView, r8Fragment);

        Button provisionBtn = view.findViewById(R.id.provisionSubmit);
        provisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    provitions.add(checkBox.getText().toString());
                }else{
                    provitions.remove(checkBox.getText().toString());
                }
                if(checkBox2.isChecked()){
                    provitions.add(checkBox2.getText().toString());
                }else{
                    provitions.remove(checkBox2.getText().toString());
                }
                if(checkBox3.isChecked()){
                    provitions.add(checkBox3.getText().toString());
                }else {
                    provitions.remove(checkBox3.getText().toString());
                }
                if(checkBox4.isChecked()){
                    provitions.add(checkBox4.getText().toString());
                }else{
                    provitions.remove(checkBox4.getText().toString());
                }
                if(checkBox5.isChecked()){
                    provitions.add(checkBox5.getText().toString());
                }else{
                    provitions.remove(checkBox4.getText().toString());
                }
                String provisionsList = TextUtils.join(", ", provitions);
                bundle.putString("List",provisionsList);
                // Add the transaction to the back stack
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();
                Navigation.findNavController(view).navigate(R.id.action_provisionFragment_to_r8Fragment);
            }
        });
    }
}
package com.example.physiohut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class R1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public R1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment R1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static R1Fragment newInstance(String param1, String param2) {
        R1Fragment fragment = new R1Fragment();
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
        return inflater.inflate(R.layout.fragment_r1, container, false);
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
                        Navigation.findNavController(view).navigate(R.id.action_r1Fragment_to_psfFragment);
                        break;
                }
                return false;
            }
        });

        Button btnSubmit = (Button) view.findViewById(R.id.clinicsubmitbutton);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //name
                //TextInputLayout textInputLayoutName= view.findViewById(R.id.nameinputlayout);
                //EditText editTextName= textInputLayoutName.getEditText();
                //address
                //TextInputLayout textInputLayoutAddress = view.findViewById(R.id.addressinputlayout);
                //EditText editTextAddress = textInputLayoutAddress.getEditText();
                //afm
                //TextInputLayout textInputLayoutAfm = view.findViewById(R.id.afminputlayout);
                //EditText editTextAfm = textInputLayoutAfm.getEditText();
                //System.out.println(textInputLayoutName);
                //if ((editTextName != null && editTextName.getText().toString().isEmpty())||(editTextAddress != null && editTextAddress.getText().toString().isEmpty())||(editTextAfm != null && editTextAfm.getText().toString().isEmpty())) {
                  //  Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                //} else {
                    //popUp
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Υποβολή Φυσιοθεραπευτηρίου");
                    builder.setMessage("ονομα κλπ κλπ");
                    builder.setPositiveButton("Υποβολή", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Navigation.findNavController(view).navigate(R.id.action_r1Fragment_to_psfFragment);
                            //bash dedomenwn kwdikas
                        }
                    });
                    builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getContext(), "Something went kwnna", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                //}


            }
        });


    }
}
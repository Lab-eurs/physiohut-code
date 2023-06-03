package com.example.physiohut;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class R3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public R3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment R3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static R3Fragment newInstance(String param1, String param2) {
        R3Fragment fragment = new R3Fragment();
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
        return inflater.inflate(R.layout.fragment_r3, container, false);
    }
    private int doc_id=1;
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
                        Navigation.findNavController(view).navigate(R.id.action_r3Fragment_to_doctorFragment);
                        break;
                }
                return false;
            }
        });
        //code_Eleni
        Button buttonSubmission = getActivity().findViewById(R.id.button_submition);
        buttonSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validationFailed =false;
                EditText editTextName = getActivity().findViewById(R.id.editText_name);
                String name = String.valueOf(editTextName.getText());
                if (name.isEmpty()) {
                    editTextName.requestFocus();
                    editTextName.setError("Το πεδίο Όνομαεπωνύμο είναι κενό!");
                    validationFailed = true;
                }
               else  if (name.length() <= 8) {
                    editTextName.requestFocus();
                    editTextName.setError("Το πεδίο Όνομαεπωνύμο  έχει λάθος δεδομένα!");
                    validationFailed = true;
                }

                EditText editTextAddress = getActivity().findViewById(R.id.editText_address);
                String address = String.valueOf(editTextAddress.getText());
                if (address.isEmpty()) {
                    editTextAddress.requestFocus();
                    editTextAddress.setError("Το πεδίο Διεύθυνση είναι κενό!");
                     validationFailed= true;
                }
               else  if (address.length() <=4 ) {
                    editTextAddress.requestFocus();
                    editTextAddress.setError("Το πεδίο Διεύθυνση έχει λάθος δεδομένα!");
                    validationFailed = true;
                }

                EditText editTextAmka = getActivity().findViewById(R.id.editText_amka);
                String amka = String.valueOf(editTextAmka.getText());
                if (amka.isEmpty()) {
                    editTextAmka.requestFocus();
                    editTextAmka.setError("Το πεδίο AMKA  είναι κενό!");
                    validationFailed = true;
                }

               else if(amka.length()!=11)
                {
                    editTextAmka.requestFocus();
                    editTextAmka.setError("Το πεδίο AMKA  έχει λάθος δεδομένα!");
                    validationFailed=true;
                }
                editTextAmka.setText("   ");
                editTextAddress.setText("   ");
                editTextName.setText("   ");
                if (!validationFailed)
                //pop up message
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    builder.setTitle("Επιβεβαίωση ασθενή");
                    builder.setMessage("Όνομα:{" + name + "}\n" + "Δίευθυνση:{" + address + "}\n" + "ΑΜΚΑ:{" + amka + "}");
                    builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast myToast = Toast.makeText(getActivity(), "H υποβολή ασθενή ακυρώθηκε!", Toast.LENGTH_SHORT);
                            myToast.show();
                            Navigation.findNavController(view).navigate(R.id.action_r3Fragment_to_doctorFragment);
                            dialogInterface.cancel();
                        }
                    });
                    builder.setPositiveButton("Υποβολή", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast myToast = Toast.makeText(getActivity(), "H υποβολή ασθενή έγινε!", Toast.LENGTH_SHORT);
                            myToast.show();
                            //Navigation.findNavController(view).navigate(R.id.action_r3Fragment_to_doctorFragment);
                            //εδω θα γίνει η αποστολή των δεδομένων στην ΒΔ
                            String url = NetworkConstants.getUrlOfFile("r3.php") + "?doc_id="+doc_id+"&NAME="+name+"&address="+address +"&amka="+amka;
                            try{
                                R3DataLog r1DataLog = new R3DataLog();
                                System.out.println(url);
                                r1DataLog.physioLog(url);
                                Toast.makeText(getContext(),"NAME: "+name +" Address: "+address+" Amka: "+amka,Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                    builder.show();
                } else if (validationFailed) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    builder.setTitle("Επιβεβαίωση ασθενή");
                    builder.setMessage("Απαιτείται διόρθωση δεδομένων παρακαλώ επαναλάβεται την διαδικάσια από την αρχή!");
                    builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast myToast = Toast.makeText(getActivity(), "H υποβολή ασθενή ακυρώθηκε!", Toast.LENGTH_SHORT);
                            myToast.show();
                            Navigation.findNavController(view).navigate(R.id.action_r3Fragment_to_doctorFragment);
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();


                }

            }
        });
    }

}

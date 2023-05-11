package com.example.physiohut;

import android.content.DialogInterface;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class R2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public R2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment R2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static R2Fragment newInstance(String param1, String param2) {
        R2Fragment fragment = new R2Fragment();
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
        return inflater.inflate(R.layout.fragment_r2, container, false);
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
                        Navigation.findNavController(view).navigate(R.id.action_r2Fragment_to_psfFragment);
                        break;
                }
                return false;
            }
        });

        //code_Flora
       Button buttonSubmission =getActivity().findViewById(R.id.buttonCreation);

       buttonSubmission.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               EditText EditTextCode = (EditText)  getActivity().findViewById(R.id.editText_code);
               String sCode  = String.valueOf(EditTextCode.getText());

               EditText EditTextName = (EditText)  getActivity().findViewById(R.id.editText_name);
               String sName  = String.valueOf(EditTextName.getText());

               EditText EditTextDescription = (EditText)  getActivity().findViewById(R.id.editText_description);
               String sDescription  = String.valueOf(EditTextDescription.getText());

               EditText EditTextPrice = (EditText)  getActivity().findViewById(R.id.editText_price);
               String sPrice  = String.valueOf(EditTextPrice.getText());

               clear_EditTexts(EditTextCode, EditTextName,EditTextDescription,EditTextPrice);

               String incorrectFields =" ";
               boolean flag=true;

               if (sCode.matches("")) {
                   EditTextCode.requestFocus();
                   EditTextCode.setError("Το πεδίο Κωδικός είναι κένo!");
                   flag = false;
                   incorrectFields="Κωδικός";

               } else if (sCode.length() <= 7) {
                   EditTextCode.requestFocus();
                   EditTextCode.setError("Το πεδίο Κωδικός έχει μη έγκυρα δεδομένα!");
                   flag = false;
                   incorrectFields=" Κωδικός";
               }

               if (sName.matches("")) {
                   EditTextName.requestFocus();
                   EditTextName.setError("Το πεδίο Όνομα Παροχής είναι κενό!");
                   flag = false;
                   if (flag)
                   {incorrectFields="Όνομα Παροχής";}
                   else{incorrectFields="Kωδικός, Όνομα Παροχής";}
               }
                else if (sName.length() <= 3) {
                   EditTextName.requestFocus();
                   EditTextName.setError("Το πεδίο Όνομα Παροχής έχει λάθος δεδομένα!");
                   flag = false;
                   if (flag)
                   {incorrectFields="Όνομα Παροχής";}
                   else {incorrectFields="Κωδικός ,Όνομα Παροχής";}
               }


               if (sDescription.matches("")) {
                   EditTextDescription.requestFocus();
                   EditTextDescription.setError("Το πεδίο Περιγραφή είναι κενό!");
                   flag = false;
                   if (flag)
                   {incorrectFields="Περιγραφή";}
                   else {incorrectFields+=" ,Περιγραφή";}
               }
               if (sPrice.matches("")) {
                   EditTextPrice.requestFocus();
                   EditTextPrice.setError("Το πεδίο Τιμή  είναι κενό!");
                   flag = false;
                   if (flag)
                   {incorrectFields="Τιμή";}
                   else {incorrectFields+=" ,Τιμή";}
               }
               
               //pop-up message

               AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
               builder.setCancelable(true); //επιτρέπω στον χρήστη να πατάει έκτος παραθύρου
               if(flag){
               builder.setTitle("Υποβολή Παροχής");
               builder.setMessage("Κωδικός:{"+sCode+"}\n"+"Όνομα:{"+sName+"}\n"+"Περιγραφή:{"+sDescription+"}\n"+"Τιμή:{"+sPrice+"}");
               builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Toast myToast= Toast.makeText(getActivity(),"H υποβολή ακυρώθηκε!",Toast.LENGTH_SHORT);
                       myToast.show();
                       Navigation.findNavController(view).navigate(R.id.action_r2Fragment_to_psfFragment);
                       dialogInterface.cancel();

                   }
               });

          builder.setPositiveButton("Υποβολή", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                  Toast myToast= Toast.makeText(getActivity(),"H υποβολή έγινε!",Toast.LENGTH_SHORT);
                  myToast.show();
                  Navigation.findNavController(view).navigate(R.id.action_r2Fragment_to_psfFragment);
                  //σε αυτό το σημείο θα αποστέλω τα δεδομένα στη ΒΔ


              }
          });

        builder.show();}

               else
                   {
                       builder.setTitle("Υποβολή Παροχής");
                       builder.setMessage("Tα παρακάτω πεδία είναι συμπληρωμένα λάθος:\n"+incorrectFields+".\nΠαρακαλώ επαναλάβετε την διαδικάσια εξ αρχής.");
                       builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               Toast myToast= Toast.makeText(getActivity(),"H υποβολή ακυρώθηκε!",Toast.LENGTH_SHORT);
                               myToast.show();
                               Navigation.findNavController(view).navigate(R.id.action_r2Fragment_to_psfFragment);
                               dialogInterface.cancel();
                           }
                       });
                       builder.show();
                   }
           }

       });

    }
   private  void clear_EditTexts(EditText EditTextCode, EditText EditTextName, EditText EditTextDescription,  EditText EditTextPrice ) {
       EditTextCode.setText("   ");
       EditTextName.setText("   ");
       EditTextDescription.setText("   ");
       EditTextPrice.setText("   ");
   }


}
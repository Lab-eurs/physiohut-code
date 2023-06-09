package com.example.physiohut.R8;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.physiohut.AuthenticateUser;
import com.example.physiohut.R;
import com.example.physiohut.model.Patient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link R8Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class R8Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public R8Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment R8Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static R8Fragment newInstance(String param1, String param2) {
        R8Fragment fragment = new R8Fragment();
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
    ArrayList<String> myPList = new ArrayList<>();
    TextView textProv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container.removeAllViews();
        View rootView = inflater.inflate(R.layout.fragment_r8, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){
            myPList = bundle.getStringArrayList("provisionList");
        }else{
            myPList.add(" ");
        }
        textProv = rootView.findViewById(R.id.Provisions);
        textProv.setText(myPList+"");
        return  rootView;
    }
    private List<String> text = new ArrayList<String>();
    private PatientList patientList;
    private String name;
    private int ap_id=0;
    private int doctor_id=AuthenticateUser.doctorID;
    private int patient_id=1;
    private static final R8DataFetcher dbFetcher = new R8DataFetcher();



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        patientList = new PatientList(AuthenticateUser.doctorID);
        super.onViewCreated(view, savedInstanceState);

        //TODO customArrayViewAdapter
        //------------------------- Populate DropDown with patients ---------------------------------------------------//
        Spinner dropDown = (Spinner) getView().findViewById(R.id.PatientSpinner);
        ArrayAdapter<Patient> arrayAdapter = new ArrayAdapter<Patient>(requireContext(),android.R.layout.simple_spinner_item,patientList.getAllPatients());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(arrayAdapter);
        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                name = dropDown.getSelectedItem().toString();
                System.out.println(name);
                patient_id = Integer.parseInt(name.split("-")[1]);
                System.out.println("ASTHENIS ID: " + patient_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //------------------------koumpi gia popup paroxwn------------------------------------------------------------
        Button provisionBtn = view.findViewById(R.id.ProvitionsBtn);
        provisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProvisionFragment provisionFragment = new ProvisionFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.r8Fragment, provisionFragment);
                transaction.addToBackStack("tag");

                transaction.commit();
            }
        });

        //--------------------------navigation bar leitourgikotita------------------------------------------------------
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_r8Fragment_to_doctorFragment);
                        break;
                }
                return false;
            }
        });

        //---------------------------------------koumph gia popup calendar---------------------------------------------
        Button calendarbtn = view.findViewById(R.id.Calendar);
        calendarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        //---------------------------------------------koumpi submit-----------------------------------------------------
        TextInputLayout comment = view.findViewById(R.id.textInputLayout);
        EditText commentEditText = comment.getEditText();

        Button submit = view.findViewById(R.id.Submitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--------------------------------------popup epivevaiwshs----------------------------------------------
                String comment = commentEditText.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Επιβεβαίωση Ραντεβού");
                builder.setMessage("Ημερομηνία: "+ selectedDate+ "\n" + "Ώρα: "+ time+"\n"+"Σχόλιο: "+comment);
                builder.setPositiveButton("Υποβολή", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        System.out.println("DATA FOR APPOINTMENT: "+ time + selectedDate + myPList + patient_id + comment);
                        Toast.makeText(getContext(), "Ραντεβού έκλεισε για: "+time+ selectedDate, Toast.LENGTH_LONG).show();
                        try{
                            R8DataFetcher r8DataFetcher = new R8DataFetcher();
                            r8DataFetcher.createAppointment(doctor_id,patient_id,myPList,selectedDate);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        commentEditText.setText("");
                    }
                });
                builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Κάτι πήγε στραβά", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
    //----------------------------------------------------PopUp Calendar-----------------------------------------------
    private String selectedDate;
    private void showDialog(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.calendar);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);

        int width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.8);
        int height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.getWindow().setLayout(width, height);
        dialog.show();


        CalendarView calendarView = dialog.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String monthStr;
//                This might not be needed but it works now
                if(month+1 < 10){
                    monthStr = "0" + String.valueOf(month+1);

                }else{
                    monthStr = String.valueOf(month+1);
                }

                String dayStr;

                if(dayOfMonth < 10){
                    dayStr = "0" + String.valueOf(dayOfMonth);

                }else{
                    dayStr = String.valueOf(dayOfMonth);
                }
                selectedDate = year + "-" + monthStr + "-" + dayStr;
            }
        });

        //---------------------------------------------------------koumpi gia tis wres-------------------------------------------------
        Button select = dialog.findViewById(R.id.calendarSelection);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showHourDialog(selectedDate);
            }
        });
    }

    //-----------------------------------------------------dhmiourgia custom pop up gia tis wres----------------------------------------
    String time;
    private void showHourDialog(String date){
        Dialog hourDialog = new Dialog(getContext());
        hourDialog.setContentView(R.layout.hours);
        hourDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);
        hourDialog.show();

        TextView textView = hourDialog.findViewById(R.id.day);
        textView.setText(date);

        Button firstButton = hourDialog.findViewById(R.id.button11);
        Button secondButton = hourDialog.findViewById(R.id.button13);
        Button thirdButton = hourDialog.findViewById(R.id.button14);
        Button forthButton = hourDialog.findViewById(R.id.button15);
        Button fifthButton = hourDialog.findViewById(R.id.button16);
        Button calendarButton = (Button) getActivity().findViewById(R.id.Calendar);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "9:00- 11:00 ";
                calendarButton.setText(date+" "+time);
                calendarButton.setTextColor(getResources().getColor(R.color.black));
                hourDialog.dismiss();
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "11:00- 13:00";
                calendarButton.setText(date+" "+time);
                calendarButton.setTextColor(getResources().getColor(R.color.black));
                hourDialog.dismiss();
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "13:00- 15:00";
                calendarButton.setText(date+" "+time);
                calendarButton.setTextColor(getResources().getColor(R.color.black));
                hourDialog.dismiss();
            }
        });

        forthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "17:00- 19:00";
                calendarButton.setText(date+" "+time);
                calendarButton.setTextColor(getResources().getColor(R.color.black));
                hourDialog.dismiss();
            }
        });

        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "19:00- 21:00";
                calendarButton.setText(date+" "+time);
                calendarButton.setTextColor(getResources().getColor(R.color.black));
                hourDialog.dismiss();
            }
        });
    }
}
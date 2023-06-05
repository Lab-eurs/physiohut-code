package com.example.physiohut;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.physiohut.R5.OkHttpHandler;
import com.example.physiohut.R6.AppointmentsList;
import com.example.physiohut.model.Appointments;
import com.example.physiohut.model.Doctor;
import com.example.physiohut.model.Patient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorFragment extends Fragment implements SearchView.OnQueryTextListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView listView;
    private PatientAdapter adapter;
    private SearchView searchView;
    private ArrayList<Doctor> doctorArrayList = new ArrayList<>();
//    private final String ip = "192.168.1.66";

//    private final String myIP = "192.168.1.4";

    public DoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorFragment newInstance(String param1, String param2) {
        DoctorFragment fragment = new DoctorFragment();
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
        return inflater.inflate(R.layout.fragment_doctor, container, false);
    }

    ArrayList<String> app_list = new ArrayList<>();


    private AppointmentsList cbl;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //paizei na einai doctor id auto
        int patientID = 1;
        //genika egw thn klash appointment xrhsimopoiw
        cbl = new AppointmentsList(patientID);
        super.onViewCreated(view, savedInstanceState);

        listView =  view.findViewById(R.id.listView);
        ArrayAdapter<Appointments> arrayAdapter = new ArrayAdapter<Appointments>(getParentFragment().getContext(), R.layout.activity_listview, R.id.textView, cbl.getAppointmentList());
//        listView.setAdapter(arrayAdapter);
        app_list.add("Ραντεβού #1");
        app_list.add("Ραντεβού #2");
        app_list.add("Ραντεβού #3");
        app_list.add("Ραντεβού #4");
        app_list.add("Ραντεβού #5");
        listView =(RecyclerView) view.findViewById(R.id.listView);
//        CustomDoctorAdapter customDoctorAdapter = new CustomDoctorAdapter(getContext(), app_list);
        ArrayList<Appointments> appointments = new ArrayList<>();
        appointments.add(new Appointments("Giannhs","15/12/2023","1","1"));
        appointments.add(new Appointments("Giannhs Karagiannhs","12/05/2008","1","1"));
        appointments.add(new Appointments("Giannhs Karamitsos","15/12/2023","1","1"));
        appointments.add(new Appointments("Giannhs Karamitsos","15/12/2023","1","1"));
        appointments.add(new Appointments("Giannhs Karamitsos","15/12/2023","1","1"));
        RecyclerAdapter rcAdapter = new RecyclerAdapter(appointments);
        listView.setAdapter(rcAdapter);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));



        TabHost th = (TabHost) view.findViewById(R.id.patientandoc);
        th.setup();

        TabHost.TabSpec ts = th.newTabSpec("Ασθενής");
        ts.setContent(R.id.appointment);
        ts.setIndicator("Ασθενής");
        th.addTab(ts);

        TabHost.TabSpec ts2 = th.newTabSpec("ραντεβού");
        ts2.setContent(R.id.date);
        ts2.setIndicator("Ραντεβού");
        th.addTab(ts2);


        Button patientHistory = (Button) view.findViewById(R.id.btnR4);
        patientHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_r4Fragment);
            }
        });

        FloatingActionButton addPatient = (FloatingActionButton) view.findViewById(R.id.btnR3);
        addPatient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_r3Fragment);
            }
        });

        FloatingActionButton reqappointment = (FloatingActionButton) view.findViewById(R.id.btnR7);
        reqappointment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_r7Fragment);
            }
        });

        FloatingActionButton createAppointment = (FloatingActionButton) view.findViewById(R.id.btnR8);
        createAppointment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_r8Fragment);
            }
        });

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                        Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_mainFragment);
                        break;
                    case R.id.home:
                        Navigation.findNavController(view).navigate(R.id.action_doctorFragment_self);
                        break;
                }
                return false;
            }
        });

        // Find the list view, create dataArray, ArrayList and set url
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.list_view);
        ArrayList<Patient> dataArray = new ArrayList<>();
        String url = NetworkConstants.getUrlOfFile("get_patients_of_doctor.php") + "?doc_id=" + AuthenticateUser.doctorID;
        //take the data with OkHttpHandler class and put them in dataArray
       try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            dataArray = okHttpHandler.getPatientNames(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Put the data in the listView
        adapter = new PatientAdapter(dataArray,requireContext());
        // Find the list view and set its adapter
//        listView = view.findViewById(R.id.list_view);
//        ArrayList<String> itemList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Find the search view and set its listener and set an extra arrayList for the search
        searchView = view.findViewById(R.id.search_view);
//        ArrayList<String> finalDataArray = dataArray;
        ArrayList<Patient> finalDataArray = dataArray;
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterData(newText);
//                return true;
//            }
//
//            private void filterData(String query) {
//                List<Patient> filteredDataList = new ArrayList<>();
//
//                // Filter the original data list based on the query
//                for (Patient item : finalDataArray) {
//                    if (item.getName().toLowerCase().contains(query.toLowerCase())) {
//                        filteredDataList.add(item);
//                    }
//                }
//
//                // Update the adapter with the filtered data
//                adapter.clear();
//                adapter.addAll(filteredDataList);
//                adapter.notifyDataSetChanged();
//            }
//        });



    }




    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AppointmentViewHolder>{

        private ArrayList<Appointments> appointments;
        public RecyclerAdapter(ArrayList<Appointments> appointments){this.appointments = appointments;};

        @NonNull
        @Override
        public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_appointment_row, parent, false);
            return new AppointmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
            Appointments a = appointments.get(position);
            holder.getDateTextV().setText(a.getAppointmentDate());
            holder.getPatientTextV().setText(a.getPatientName());
        }

        @Override
        public int getItemCount() {
            return appointments.size();
        }

        public class AppointmentViewHolder extends RecyclerView.ViewHolder{
            private final TextView patientTextV;
            private final TextView dateTextV;
            public AppointmentViewHolder(View view){
                super(view);
                patientTextV = (TextView) view.findViewById(R.id.patientOfAppointment);
                dateTextV = (TextView) view.findViewById(R.id.dateOfAppointment);
            }

            public TextView getPatientTextV(){
                return patientTextV;
            }

            public TextView getDateTextV(){
                return dateTextV;
            }
        }
    }



    @Override
    public boolean onQueryTextSubmit(String s) {
        System.out.println("submit");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        System.out.println("change");
        return false;
    }



    public class CustomDoctorAdapter extends ArrayAdapter<String> {

        public CustomDoctorAdapter(Context context, List<String> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Inflate the list item layout
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview, parent, false);
            }

            // Get the current item from the data source
            String currentItem = getItem(position);

            // Set the text or perform any other operations on other views in the item layout
            TextView nameTextView = convertView.findViewById(R.id.textView);
            nameTextView.setText(currentItem);
            // Get the button reference and set the click listener
            Button buttonItem = convertView.findViewById(R.id.item_btn);
            buttonItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProvisionPatients provisionPatients = new ProvisionPatients();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.doctorhome, provisionPatients);
                    transaction.addToBackStack("tag");

                    transaction.commit();
                }
            });

            return convertView;
        }
    }

    public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> implements View.OnClickListener{

        private ArrayList<Patient> patients;
        Context mContext;
        public PatientAdapter(ArrayList<Patient> data,Context context) {
            super();
            this.mContext = context;
            this.patients = data;
        }
        public ArrayList<Patient> getPatients(){
            return patients;
        }



        @Override
        public void onClick(View v) {
            int position=(Integer) v.getTag();
            Object object= patients.get(position);
            Patient patient =(Patient)object;
            System.out.println("Clicked on patient:" + patient.getName());
        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext())
                    .inflate(R.layout.recycler_patient_row, parent, false);

            return new PatientViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
                Patient p = patients.get(position);
                holder.getPatientNameText().setText(p.getName());
                holder.getIdText().setText(p.getId());
                holder.getShowHistoryBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO with patient id navigate to R4 fragment
                        System.out.println("CLICKED ON PATIENT: " + p.getName());
                    }
                });
        }

        @Override
        public int getItemCount() {
            return this.patients.size();
        }

        public class PatientViewHolder extends RecyclerView.ViewHolder {

            private final TextView patientNameText;
            private final TextView idText;
            private final Button showHistoryBtn;

            public TextView getPatientNameText() {
                return patientNameText;
            }

            public TextView getIdText() {
                return idText;
            }

            public Button getShowHistoryBtn() {
                return showHistoryBtn;
            }

            public PatientViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                patientNameText = (TextView) view.findViewById(R.id.nameOfPatient);
                idText = (TextView) view.findViewById(R.id.patientId);
                showHistoryBtn = (Button) view.findViewById(R.id.showHistoryBtn);

            }





        }
    }
}


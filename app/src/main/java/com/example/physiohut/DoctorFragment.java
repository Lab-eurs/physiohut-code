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
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.physiohut.R4.R4Fragment;
import com.example.physiohut.R5.OkHttpHandler;
import com.example.physiohut.R6.AppointmentListAdapter;
import com.example.physiohut.R6.AppointmentsList;
import com.example.physiohut.R6.R6DataFetcher;
import com.example.physiohut.model.Appointments;
import com.example.physiohut.model.Doctor;
import com.example.physiohut.model.Patient;
import com.example.physiohut.model.Session;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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

    private RecyclerView patientRecyclerView;
    private RecyclerView appointmentsRecyclerView;
    private PatientAdapter adapter;
    private SearchView searchView;
    private ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    private R6DataFetcher r6DataFetcher = new R6DataFetcher();
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



    private AppointmentsList cbl;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //paizei na einai doctor id auto
        int patientID = 1;
        //genika egw thn klash appointment xrhsimopoiw
//        cbl = new AppointmentsList(patientID);
        super.onViewCreated(view, savedInstanceState);

        appointmentsRecyclerView = (RecyclerView) view.findViewById(R.id.appointmentRecView);
        ArrayList<Session> sessions = new ArrayList<>();
        try{
            sessions  = r6DataFetcher.fetchWeeklyAppointments(AuthenticateUser.doctorID);
        }catch (Exception e){

        }

        AppointmentListAdapter rcAdapter = new AppointmentListAdapter(sessions);
        appointmentsRecyclerView.setAdapter(rcAdapter);
        appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        ArrayAdapter<Appointments> arrayAdapter = new ArrayAdapter<Appointments>(getParentFragment().getContext(), R.layout.activity_listview, R.id.textView, cbl.getAppointmentList());
//        listView.setAdapter(arrayAdapter);



//        ArrayList<Appointments> appointments = new ArrayList<>();
//        appointments.add(new Appointments("Giannhs","15/12/2023","1","1"));
//        appointments.add(new Appointments("Giannhs Karagiannhs","12/05/2008","1","1"));
//        appointments.add(new Appointments("Giannhs Karamitsos","15/12/2023","1","1"));
//        appointments.add(new Appointments("Giannhs Karamitsos","15/12/2023","1","1"));
//        appointments.add(new Appointments("Giannhs Karamitsos","15/12/2023","1","1"));
//        RecyclerAdapter rcAdapter = new RecyclerAdapter(appointments);
//        patientRecyclerView.setAdapter(rcAdapter);
//        patientRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        TabHost th = (TabHost) view.findViewById(R.id.patientandoc);
        th.setup();

        TabHost.TabSpec ts = th.newTabSpec("Ασθενής");
        ts.setContent(R.id.patientsTab);
        ts.setIndicator("Ασθενής");
        th.addTab(ts);

        TabHost.TabSpec ts2 = th.newTabSpec("ραντεβού");
        ts2.setContent(R.id.appointmentsTab);
        ts2.setIndicator("Ραντεβού");
        th.addTab(ts2);




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
        RecyclerView appointmentRecView = (RecyclerView) view.findViewById(R.id.appointmentRecView);
        ArrayList<Patient> dataArray = new ArrayList<>();
        String url = NetworkConstants.getUrlOfFile("r5-get_patients_of_doctor.php") + "?doc_id=" + AuthenticateUser.doctorID;
        //take the data with OkHttpHandler class and put them in dataArray
       try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            dataArray = okHttpHandler.getPatientNames(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        patientRecyclerView =  view.findViewById(R.id.patientRecView);
        patientRecyclerView =(RecyclerView) view.findViewById(R.id.patientRecView);
        //Put the data in the listView
        adapter = new PatientAdapter(dataArray,requireContext());
        patientRecyclerView.setAdapter(adapter);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Find the search view and set its listener and set an extra arrayList for the search
        searchView = view.findViewById(R.id.search_view);
        ArrayList<Patient> finalDataArray = dataArray;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }

            private void filterData(String query) {
                ArrayList<Patient> filteredDataList = new ArrayList<>();

                // Filter the original data list based on the query
                for (Patient item : finalDataArray) {
                    if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                        filteredDataList.add(item);
                    }
                }
                PatientAdapter newPatientAdapter = new PatientAdapter(filteredDataList,getContext());
                patientRecyclerView.swapAdapter(newPatientAdapter,false);
            }
        });



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
                        System.out.println("CLICKED ON PATIENT: " + p.getId());
                        Bundle bundle = new Bundle();
                        bundle.putInt("patient_id",Integer.parseInt(p.getId()));
                        Fragment r4 = new R4Fragment();
                        r4.setArguments(bundle);

                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();

                        transaction.replace(R.id.patientsTab,r4,"hello");
                        transaction.commit();
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


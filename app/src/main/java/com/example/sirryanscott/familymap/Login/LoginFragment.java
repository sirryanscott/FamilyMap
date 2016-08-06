package com.example.sirryanscott.familymap.Login;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sirryanscott.familymap.HttpClient;
import com.example.sirryanscott.familymap.MainActivity;
import com.example.sirryanscott.familymap.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button loginButton;
    private View myView;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public LoginFragment() {
        // Required empty public constructor
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
        myView = inflater.inflate(R.layout.fragment_login, container, false);
        LoginData.getInstance().setMyView(myView);

        loginButton = (Button)myView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginData.getInstance().setLoginData();

                SignIn signIn = new SignIn();
                signIn.execute();

            }
        });
        return myView;
    }
    
    public class SignIn extends AsyncTask<Void, Void, Boolean>{
        protected Boolean doInBackground(Void... urls) {

            HttpClient httpClient = new HttpClient();

            boolean result = httpClient.login();
            return result;
        }

        protected void onPostExecute(Boolean result) {
            //make toast
            if(!result){
                Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
            }
            else{
                GetPeopleData getPeopleData = new GetPeopleData();
                getPeopleData.execute();
            }
        }
    }

    public class GetPeopleData extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            HttpClient httpClient = new HttpClient();

            boolean result = httpClient.peopleData();

            return result;
        }
        protected void onPostExecute(Boolean result) {
            if(result){


                String toastString = "Hello " +
                        LoginData.getInstance().getCurrentUser().getFirstName() +
                        " " +
                        LoginData.getInstance().getCurrentUser().getLastName();



                Toast.makeText(getActivity(), toastString, Toast.LENGTH_SHORT).show();

                GetEventData getEventData = new GetEventData();
                getEventData.execute();

            }
            else{
                Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class GetEventData extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            HttpClient httpClient = new HttpClient();

            boolean result = httpClient.eventData();
            return result;
        }
        protected void onPostExecute(Boolean result) {
            if(result){
                ((MainActivity)getActivity()).startMapFragment();
            }
        }
    }
}

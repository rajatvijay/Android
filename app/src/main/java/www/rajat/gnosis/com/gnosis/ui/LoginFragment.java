package www.rajat.gnosis.com.gnosis.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import www.rajat.gnosis.com.gnosis.R;
import www.rajat.gnosis.com.gnosis.navigationDrawer.NavigationDrawerActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    //Making the layout handlers global
    private EditText emailLabel;
    private EditText passwordLabel;
    private Button loginButton;

    //Log tag
    public final static String LOG_TAG = LoginFragment.class.getSimpleName();

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        //Get handle to all the layout feilds
        emailLabel = (EditText) rootView.findViewById(R.id.input_email);
        passwordLabel = (EditText) rootView.findViewById(R.id.input_password);
        loginButton = (AppCompatButton) rootView.findViewById(R.id.btn_login);

        //Set event listener on login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void login(){
        Log.d(LOG_TAG, "Login Button Clicked");

        final String email = emailLabel.getText().toString();
        final String password = passwordLabel.getText().toString();


        loginButton.setEnabled(false);

        if (!validate(email, password)) {
            onLoginFailed();
        }

        final ProgressDialog authenticatingDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        authenticatingDialog.setIndeterminate(true);
        authenticatingDialog.setMessage("Authenticating");
        authenticatingDialog.show();

        //No Idea What it is
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                // On complete call either onLoginSuccess or onLoginFailed
                if (!checkOnServer(email, password)){
                    onLoginFailed();
                }
                else {
                    onLoginSuccessful();
                }
                authenticatingDialog.dismiss();
            }
        }, 3000);

    }

    public boolean validate(String email, String password){

        boolean valid = true;

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLabel.setError("Enter a valid email address");
            valid = false;
        } else {
            emailLabel.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordLabel.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordLabel.setError(null);
        }

        return valid;
    }

    public void onLoginFailed(){
        Toast.makeText(getActivity(), "Login Failed, Please Try Again", Toast.LENGTH_SHORT).show();
        loginButton.setEnabled(true);
    }

    public void onLoginSuccessful(){
        Intent intent = new Intent(getActivity(), NavigationDrawerActivity.class);
        startActivity(intent);

    }

    public boolean checkOnServer(String email, String password){
        //TODO: Implement Login Logic
        return true;
    }
}

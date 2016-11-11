package mattsturgill.peoplemongo.Views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import mattsturgill.peoplemongo.Models.User;
import mattsturgill.peoplemongo.Network.RestClient;
import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Stages.PeopleMonListStage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static mattsturgill.peoplemongo.Constants.Constants.apiKey;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class RegisterView extends LinearLayout {
    private Context context;

    @Bind(R.id.email_field)
    EditText emailField;

    @Bind(R.id.fullname_field)
    EditText fullnameField;

    @Bind(R.id.password_field)
    EditText passwordField;

    @Bind(R.id.confirm_field)
    EditText confirmField;

    @Bind(R.id.register_button2)
    Button registerButton;

    @Bind(R.id.spinner)
    ProgressBar spinner;

    public RegisterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.register_button2)
    public void register(){
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(emailField.getWindowToken(),0);
        imm.hideSoftInputFromWindow(fullnameField.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordField.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(confirmField.getWindowToken(),0);

        String email = emailField.getText().toString();
        String fullname = fullnameField.getText().toString();
        String avatar = "yellow";
        String password = passwordField.getText().toString();
        String confirm = confirmField.getText().toString();
        String apiK = apiKey;

        if ( email.isEmpty() || fullname.isEmpty() || password.isEmpty() || confirm.isEmpty()){
            Toast.makeText(context, R.string.please_fill_fields, Toast.LENGTH_LONG).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(context, R.string.validemail, Toast.LENGTH_LONG).show();
        } else if (!password.equals(confirm)){
            Toast.makeText(context, R.string.Passwordsdontmatch, Toast.LENGTH_LONG).show();
        } else {
            registerButton.setEnabled(false);
            spinner.setVisibility(VISIBLE);

            User user = new User(email, fullname, null, null, password);
            RestClient restClient = new RestClient();
            restClient.getApiService().register(user).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){

                        Toast.makeText(context, "it works", Toast.LENGTH_SHORT).show();
                        Flow flow = PeopleMonGoApplication.getMainFlow();
                        History newHistory = History.single(new PeopleMonListStage());
                        flow.setHistory(newHistory, Flow.Direction.REPLACE);

                    } else {
                        resetView();
                        Toast.makeText(context, "Meow wrong message" + ": " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    resetView();
                    Toast.makeText(context, R.string.regfailed, Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        }
    }

    private void resetView(){
        registerButton.setEnabled(true);
        spinner.setVisibility(GONE);
    }
}

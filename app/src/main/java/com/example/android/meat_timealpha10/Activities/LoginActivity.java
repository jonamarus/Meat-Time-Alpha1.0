package com.example.android.meat_timealpha10.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.meat_timealpha10.Fragments.PasswordRecoveryFragment;
import com.example.android.meat_timealpha10.Models.TokenModel;
import com.example.android.meat_timealpha10.R;
import com.example.android.meat_timealpha10.RestService.RestClient;
import com.example.android.meat_timealpha10.RestService.RestService;
import com.example.android.meat_timealpha10.helpers.HelperMethods;
import com.example.android.meat_timealpha10.helpers.TokenHelper;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends FragmentActivity implements Validator.ValidationListener {
  @BindView(R.id.pwrecovery)
  public Button pwrecovery;

  @BindView(R.id.login_button)
  public LoginButton fbLoginBtn;

  @BindView(R.id.signup)
  public Button signup;

  @BindView(R.id.login_email)
  @Email
  public EditText email;

  @BindView(R.id.login_password)
  @Password()
  public EditText password;

  // public RestService restService;
  public FirebaseAuth mAuth;
  public Context context;
  public Validator validator;
  public CallbackManager callbackManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getIntent().getExtras();

    //set up notitle
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    //set up full screen
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_login);

    context = getApplicationContext();
    mAuth = FirebaseAuth.getInstance();

    ButterKnife.bind(this);

    if(mAuth.getCurrentUser() != null){
      toMainActivity();
    }

    validator = new Validator(this);
    validator.setValidationListener(this);

    callbackManager = CallbackManager.Factory.create();
    fbLoginBtn.setReadPermissions("email", "public_profile");

    fbLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
      @Override
      public void onSuccess(LoginResult loginResult) {
        facebookLogin(loginResult.getAccessToken());
        Log.d("FACEBOOK", loginResult.getAccessToken().getToken());
      }

      @Override
      public void onCancel() {

      }

      @Override
      public void onError(FacebookException exception) {

      }
    });

    if (bundle.containsKey("email") && bundle.containsKey("password")) {
      email.setText(bundle.getString("email"));
      password.setText(bundle.getString("password"));
    }

  }

  public void facebookLogin(AccessToken token) {
    AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
    mAuth.signInWithCredential(credential)
      .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
          if (task.isSuccessful()) {
            // Sign in success, update UI with the signed-in user's information
            Log.d("FirebasAUth", "signInWithCredential:success");
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
          } else {
            // If sign in fails, display a message to the user.
            Log.w("FirebaseAUth", "signInWithCredential:failure", task.getException());
            Toast.makeText(LoginActivity.this, "Authentication failed.",
              Toast.LENGTH_SHORT).show();
            //updateUI(null);
          }
        }
      });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    callbackManager.onActivityResult(requestCode, resultCode, data);
  }

  @OnClick(R.id.pwrecovery)
  public void startPwRecovery(View view) {
    Log.d("LoginActivity", "Password Recovery");

    FragmentManager manager = getFragmentManager();
    Fragment frag = manager.findFragmentByTag("fragment_password_recovery");
    if (frag != null) {
      manager.beginTransaction().remove(frag).commit();
    }

    PasswordRecoveryFragment passwordRecoveryDialog = new PasswordRecoveryFragment();
    passwordRecoveryDialog.show(manager, "fragment_password_recovery");
  }

  @OnClick(R.id.signup)
  public void startSignUp(View view) {
    Intent intent = new Intent(context, RegisterActivity.class);
    startActivity(intent);
  }

  @OnClick(R.id.sign_in)
  public void submitLogin() {
    validator.validate();
  }

  public void login() {
    Log.d("LOG IN", "Logging in ");
    mAuth.signInWithEmailAndPassword(email.toString(), password.toString())
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("FIREBASEAUTH", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    toMainActivity();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("FirebaseAuth", "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_login, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onValidationSucceeded() {
    login();
  }

  @Override
  protected void onResume() {
    super.onResume();
/*    if (HelperMethods.isLoggedIn(context)) {
      toMainActivity();
    }*/
  }

  @Override
  public void onValidationFailed(List<ValidationError> errors) {
    for (ValidationError error : errors) {
      View view = error.getView();
      String message = error.getCollatedErrorMessage(context);

      // Display error messages ;)
      if (view instanceof EditText) {
        ((EditText) view).setError(message);
      } else {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
      }
    }
  }

  private void toMainActivity() {
    Intent intent = new Intent(context, MainActivity.class);
    startActivity(intent);
  }


}

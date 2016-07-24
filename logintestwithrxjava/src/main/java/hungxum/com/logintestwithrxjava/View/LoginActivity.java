package hungxum.com.logintestwithrxjava.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hungxum.com.logintestwithrxjava.Model.Bean.User;
import hungxum.com.logintestwithrxjava.Presenter.LoginPresenter;
import hungxum.com.logintestwithrxjava.R;
import hungxum.com.logintestwithrxjava.View.IView.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private EditText nameEdit;
    private EditText pswEdit;
    private Button loginBtn;

    private User user;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginPresenter = new LoginPresenter(this);
    }

    public void initView(){
        nameEdit = (EditText) findViewById(R.id.edit_login_name);
        pswEdit = (EditText) findViewById(R.id.edit_login_password);
        loginBtn = (Button) findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(this);
    }
    @Override
    public User getUser() {
        user = new User();
        user.setName(nameEdit.getText().toString());
        user.setPassword(pswEdit.getText().toString());
        return user;
    }

    @Override
    public void showSuccessInfo(User user) {
        Toast.makeText(LoginActivity.this,"usename:" + user.getName() + "login successful!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFailInfo() {
        Toast.makeText(LoginActivity.this, "login fail!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                loginPresenter.Login();
                break;
        }
    }
}

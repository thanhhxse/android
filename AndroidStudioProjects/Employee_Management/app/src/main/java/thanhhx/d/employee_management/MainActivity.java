package thanhhx.d.employee_management;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Connection;
import java.sql.Statement;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    // Declaring layout button, edit texts
    Button btn_login;
    EditText ET_Username,ET_Password;
    ProgressBar progressBar;
    // End Declaring layout button, edit texts
    // Declaring connection variables
    Connection connect;
    String DBUserNameStr,DBPasswordStr,db,ip,UserNameStr,PasswordStr;
    //End Declaring connection variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting values from button, texts and progress bar
        btn_login = (Button) findViewById(R.id.loginButton);
        ET_Username = (EditText) findViewById(R.id.editText);
        ET_Password = (EditText) findViewById(R.id.editText2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // End Getting values from button, texts and progress bar

        // Declaring Server ip, username, database name and password
        ip = "10.0.2.2/1433";
        db = "Android_Project";
        DBUserNameStr = "sa";
        DBPasswordStr = "P@55word";
        // Declaring Server ip, username, database name and password
        progressBar.setVisibility(View.GONE);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserNameStr=ET_Username.getText().toString();
                PasswordStr=ET_Password.getText().toString();

                checklogin check_Login = new checklogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                check_Login.execute(UserNameStr,PasswordStr);
            }
        });
    }

    public class checklogin extends AsyncTask<String,String,String>
    {
        String ConnectionResult = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute()
        {

            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(String result)
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(MainActivity.this , "Login Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            String usernam = UserNameStr;
            String passwordd =PasswordStr;
            if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                ConnectionResult = "Please enter Username and Password";
            else
            {
                try
                {
                    ConnectionStr conStr=new ConnectionStr();

                    connect =conStr.connectionclasss(DBUserNameStr, DBPasswordStr, db, ip);        // Connect to database
                    if (connect == null)
                    {
                        ConnectionResult = "Check Your Internet Access!";
                    }
                    else
                    {
                        // Change below query according to your own database.
                        String query = "select * from UserDB where username= '" + usernam.toString() + "' and password = '"+ passwordd.toString() +"'  ";
                        Statement stmt = connect.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            ConnectionResult = "Login successful";
                            isSuccess=true;
                            connect.close();
                            changeScreen(ConnectionResult);

                        }
                        else
                        {
                            ConnectionResult = "Invalid Credentials!";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    ConnectionResult = ex.getMessage();
                }
            }
            return ConnectionResult;
        }
    }

    public void changeScreen(String loginResult){
        if(loginResult.equals("Login successful")){
            Intent intent = new Intent(this , TaskListActivity.class);
            startActivity(intent);
        }
    }


}

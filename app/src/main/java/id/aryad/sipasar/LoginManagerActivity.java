package id.aryad.sipasar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoginManagerActivity extends AppCompatActivity {

    EditText et_username, et_pass;
    Button btn_login;
    RelativeLayout rellay1;
    Handler handler = new Handler();
    Admin admin = new Admin();
    public static final String EXTRA_MESSAGE = "id.aryad.sipasar.MESSAGE";
    int adminId;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_manager_activity);

        String nilai_normal = "defaultValue";
        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

        if (sharedPreferences.getString("keyUsername", "defaultValue").compareTo(nilai_normal) < 0){
            Intent dashboard_intent = new Intent(LoginManagerActivity.this, DashboardActivity.class);
//            startActivity(dashboard_intent);
//            dashboard_intent.putExtra(EXTRA_MESSAGE, adminId);
            startActivity(dashboard_intent);
        }

        Admin admin_met = new Admin();

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 4000); //2000 waktu keluar splash

        et_username = (EditText) findViewById(R.id.et_username);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int status_login = 0;
                String str_username = et_username.getText().toString();
                String str_pass = et_pass.getText().toString();
                status_login = admin_met.status_login(str_username, str_pass);

                if (status_login == 0){
                    //Login tidak menuliskan apa-apa
                    Toast.makeText(getApplicationContext(),"Isi Username dan Password dengan benar", Toast.LENGTH_SHORT).show();
                }else if(status_login == 1){
                    //Login berhasil
                    //Toast.makeText(getApplicationContext(),"Login Sukses", Toast.LENGTH_SHORT).show();

//                    Intent dashboard_intent =new Intent (MainActivity.this, Dashboard.class);
//                    startActivity(dashboard_intent);
                    adminId = admin_met.getIdManager(str_username, str_pass);
                    Intent dashboard_intent = new Intent(LoginManagerActivity.this, DashboardActivity.class);
                    startActivity(dashboard_intent);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("keyUsername", str_username);
                    editor.putString("keyPass", str_pass);
                    editor.putInt("keyID", adminId);
//                    Log.d("admin", String.valueOf(adminId));

                    editor.apply();

                }else if (status_login == 2){
                    et_username.setText(null);
                    et_pass.setText(null);
                    //Login tidak sebagai manager
                    Toast.makeText(getApplicationContext(),"Login sebagai manager", Toast.LENGTH_SHORT).show();
                }else if(status_login == 3){
                    et_username.setText(null);
                    et_pass.setText(null);
                    //Login tidak aktif
                    Toast.makeText(getApplicationContext(),"Status user sudah tidak aktif", Toast.LENGTH_SHORT).show();
                }else if (status_login == 4){
                    et_username.setText(null);
                    et_pass.setText(null);
                    //Login username dan password salah
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginManagerActivity.this);
                    builder.setMessage("Username atau password anda salah, ulangi").setNegativeButton("Retry",null).create().show();
                }

            }
        });


    }
}
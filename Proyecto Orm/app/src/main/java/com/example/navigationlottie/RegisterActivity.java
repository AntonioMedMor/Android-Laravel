package com.example.navigationlottie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText et_name, et_email, et_password, et_confirmation;
    Button btn_register;
    String name, email, password, confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("REGISTRAR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirmation = findViewById(R.id.et_confirmation);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegister();
            }
        });

    }

    private void checkRegister() {
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        password = et_password.getText().toString();
        confirmation = et_confirmation.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            alertFail("Se requiere nombre, email y password");
        }
        else if (! password.equals(confirmation)){
            alertFail("La contraseña no es correcta");
        }
        else {
            sendRegister();
        }
    }

    private void sendRegister() {
        JSONObject params = new JSONObject();
        try {
            params.put("name", name);
            params.put("email", email);
            params.put("password", password);
            params.put("password_confirmation", confirmation);

        }catch (JSONException e){
            e.printStackTrace();
        }

        String data= params.toString();
        String url= getString(R.string.api_server)+"/register";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(RegisterActivity.this, url);
                http.setMethod("post");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code= http.getStatusCode();
                        if(code==201 || code==200){
                            alertSucess("Registro exitoso");
                        } else if (code == 442) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                 String msg = response.getString("message");
                                 alertFail(msg);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error "+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();

    }

    private void alertSucess(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Exitosos")
                .setIcon(R.drawable.baseline_check_box_24)
                .setMessage(s)
                .setPositiveButton("login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                }).show();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Fallido")
                .setIcon(R.drawable.baseline_warning_24)
                .setMessage(s)
                .setPositiveButton("login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
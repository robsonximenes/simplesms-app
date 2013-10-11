package com.ratodigital.simplesms;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {
	
	public static final String SENDER = "539939418841";

	public static EditText editSenha;
	public static EditText editEmail;
	private Button registrar;
	AlertDialogManager alert = new AlertDialogManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editSenha = (EditText) findViewById(R.id.senha);
		editEmail = (EditText) findViewById(R.id.email);
		registrar = (Button) findViewById(R.id.registrar);

		registrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Read EditText dat
				String senha = editSenha.getText().toString();
				String email = editEmail.getText().toString();

				// Check if user filled the form
				//if (senha.trim().length() > 0 && email.trim().length() > 0) {
					registrarDispositivo(email, senha);
//				} else {
//					// user doen't filled that data
//					// ask him to fill the form
//					alert.showAlertDialog(MainActivity.this,
//							"Registration Error!", "Please enter your details",
//							false);
//				}
			}

		});

	}

	private void registrarDispositivo(String email, String senha) {
		GCMRegistrar.checkDevice(this);

		// Make sure the manifest was properly set - comment out this line
		// while developing the app, then uncomment it when it's ready.
		GCMRegistrar.checkManifest(this);

		// Get GCM registration id
		String regId = GCMRegistrar.getRegistrationId(this);

		// Check if regid already presents
		if (regId.equals("")) {
			// Registration is not present, register now with GCM
			GCMRegistrar.register(this, SENDER);
			
		}else {
			// Device is already registered on GCM
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				// Skips registration.
				Toast.makeText(getApplicationContext(),
						"Already registered with GCM", Toast.LENGTH_LONG)
						.show();
			} else {
				// Atualizar no servidor...
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

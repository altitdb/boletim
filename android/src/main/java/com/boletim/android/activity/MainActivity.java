package com.boletim.android.activity;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.boletim.android.R;
import com.boletim.android.util.IOUtils;
import com.boletim.android.util.URLUtil;
import com.boletim.domain.Aluno;
import com.boletim.domain.Erro;
import com.google.gson.Gson;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		behaviorButtonOk();
	}

	private void behaviorButtonOk() {
		final Button buttonOk = (Button) findViewById(R.id.buttonOk);

		buttonOk.setOnClickListener(new View.OnClickListener() {

			public boolean isConnected() {
				ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()) {
					return true;
				} else {
					return false;
				}
			}

			@Override
			public void onClick(View v) {
				if (isConnected()) {
					EditText editTextRa = (EditText) findViewById(R.id.editTextRa);
					String ra = editTextRa.getText().toString();
					String url = URLUtil.URL_ALUNO + ra;
					new AlunoTask().execute(url);
				} else {
					Toast.makeText(MainActivity.this,
							"É necessário estar conectado a Internet",
							Toast.LENGTH_LONG).show();
				}
			}

		});
	}

	private class AlunoTask extends AsyncTask<String, Void, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(MainActivity.this, "Aguarde",
					"Consultando informações...");
		}

		@Override
		protected String doInBackground(String... args) {
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse httpResponse = httpclient.execute(new HttpGet(
						args[0]));
				StatusLine statusLine = httpResponse.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					InputStream inputStream = httpResponse.getEntity()
							.getContent();
					return IOUtils.toString(inputStream);
				}
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(String json) {
			
			Gson gson = new Gson();
			String text = StringUtils.EMPTY;
			Aluno aluno = gson.fromJson(json, Aluno.class);
			if (aluno != null && aluno.getIdAluno() == null) {
				Erro erro = gson.fromJson(json, Erro.class);
				if (erro.getMessage() == null) {
					text = "Algum erro inesperado aconteceu!";
				} else {
					text = erro.getMessage();
				}
			} else {
				text = "Welcome " + aluno.getNome();
				
				Intent intent = new Intent(MainActivity.this, BoletimActivity.class);
				intent.putExtra("aluno", json);
				startActivity(intent);
			}
			dialog.dismiss();
			Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
		}
	}

}

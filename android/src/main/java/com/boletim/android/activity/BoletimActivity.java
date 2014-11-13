package com.boletim.android.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.boletim.android.R;
import com.boletim.android.adapter.BoletimListExpandableAdapter;
import com.boletim.android.util.IOUtils;
import com.boletim.android.util.URLUtil;
import com.boletim.domain.BoletimNotasAnual;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BoletimActivity extends Activity {

	private BoletimListExpandableAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boletim);
		
		Bundle extras = getIntent().getExtras();
		String json = extras.getString("aluno");
		
		adapter = new BoletimListExpandableAdapter(this);
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListViewBoletim);
         
        listView.setAdapter(adapter);
	
		new BoletimTask().execute(URLUtil.URL_NOTAS, json);
	}

	private class BoletimTask extends AsyncTask<String, Void, List<BoletimNotasAnual>> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(BoletimActivity.this, "Aguarde",
					"Consultando informações...");
		}

		@Override
		protected List<BoletimNotasAnual> doInBackground(String... args) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(args[0]);
				StringEntity entity = new StringEntity(args[1]);
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				StatusLine statusLine = httpResponse.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					InputStream inputStream = httpResponse.getEntity()
							.getContent();
					String json = IOUtils.toString(inputStream);
					Gson gson = new Gson();
					List<BoletimNotasAnual> boletimNotas = gson.fromJson(json, new TypeToken<List<BoletimNotasAnual>>(){}.getType());
					return boletimNotas;
				}
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(final List<BoletimNotasAnual> boletimNotas) {
			super.onPostExecute(boletimNotas);
			adapter.setBoletimNotas(boletimNotas);
			adapter.notifyDataSetChanged();
			dialog.dismiss();
		}
	}
}

package com.example.headhunter.loaders;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

import com.example.headhunter.MainActivity;
import com.example.headhunter.parsers.MyJSONParser;

public class VacanciesLoader extends AsyncTask<Void, Void, Void> {
	
	String url = null;
	private MainActivity mainActivity;

	public VacanciesLoader(MainActivity mainActivity, String url) {
		this.mainActivity = mainActivity;
		this.url = url;
	}

	@Override
	protected Void doInBackground(Void... args) {
		DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = null;
        Log.i("INFO", "Vacancy loader doInBackground()");
		try {
			response = client.execute(request);
			HttpEntity httpEntity = response.getEntity();
			Log.i("DEBUG", response.getStatusLine().toString());
			InputStream stream = httpEntity.getContent();

			MyJSONParser parser = new MyJSONParser(mainActivity);
			parser.execute(stream);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}

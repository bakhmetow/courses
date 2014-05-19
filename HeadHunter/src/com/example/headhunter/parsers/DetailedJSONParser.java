package com.example.headhunter.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.headhunter.DetailedActivity;
import com.example.headhunter.R;

public class DetailedJSONParser extends AsyncTask<InputStream, Void, String> {
	
	public static final String JSON_DESCRIPTION = "description";
	
	private DetailedActivity activity;
	
	public DetailedJSONParser(DetailedActivity activity){
		this.activity = activity;
	}
	
	@Override
	protected String doInBackground(InputStream... params) {
		InputStream inputStream = null;
		String result = null;
		Log.i("INFO", "DetailedJSONParser doInBackground()");
		try {
			inputStream = params[0];
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			StringBuffer jsonString = new StringBuffer();
			int data = inputStreamReader.read();
			while (data != -1){
				jsonString.append((char)data);
				data = inputStreamReader.read();
			}
			try {
				JSONObject jsonObject = new JSONObject(jsonString.toString());
				result = jsonObject.getString(JSON_DESCRIPTION);
				result = result.replaceAll("<[ul]>|<[li]>|<br[ ]?[/][ ]?>", "\n");
				result = result.replaceAll("</[a-z]+>|<[a-z]+>", "");
				Log.i("INFO", "Result: " + result);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			inputStreamReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
        Log.i("INFO", "DetailedJSONParser onPostExecute()");
		TextView tvDescription = (TextView) activity.findViewById(R.id.tvDescription);
		tvDescription.setText(result);
        super.onPostExecute(result);
	}

}

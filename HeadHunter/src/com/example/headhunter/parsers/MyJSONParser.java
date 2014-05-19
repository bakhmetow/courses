package com.example.headhunter.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.headhunter.DetailedActivity;
import com.example.headhunter.MainActivity;
import com.example.headhunter.R;
import com.example.headhunter.adapters.MyListAdapter;
import com.example.headhunter.entities.Info;
import com.example.headhunter.entities.Item;
import com.google.gson.Gson;

public class MyJSONParser extends AsyncTask<InputStream, Void, List<Item>> {
	
	private MainActivity activity;
	
	public MyJSONParser(MainActivity activity){
		this.activity = activity;
	}
	
	@Override
	protected List<Item> doInBackground(InputStream... params) {
		InputStream inputStream = null;
		List<Item> itemsList = null;
        Log.i("INFO", "MyJSONParser doInBackground()");
		try {
			inputStream = params[0];
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			Gson gson = new Gson();
			Info info = gson.fromJson(inputStreamReader, Info.class);
			itemsList = info.getItems();
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
		
		return itemsList;
	}
	
	@Override
	protected void onPostExecute(List<Item> result) {
        Log.i("INFO", "MyJSONParser onPostExecute()");
		MyListAdapter adapter = new MyListAdapter(activity, result);
		ListView lv = (ListView) activity.findViewById(R.id.lvVacancies);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(activity, DetailedActivity.class);
				
				Item item = (Item) parent.getItemAtPosition(position);
				
				String vacancyName = item.getName();
				String vacancyId = item.getId();
				String published = item.getPublished_at();
				String date = published.substring(0,10);
		        String time = published.substring(11, 19);
				String employerName = item.getEmployer().getName();
				String employerImage = "";
				if (item.getEmployer() != null){
					if (item.getEmployer().getLogo_urls() != null){
						employerImage = item.getEmployer().getLogo_urls().getOriginal();
					}
				}
				
				intent.putExtra(MainActivity.VACANCY_EMPLOYER_IMAGE, employerImage);
				intent.putExtra(MainActivity.VACANCY_EMPLOYER_NAME, employerName);
				intent.putExtra(MainActivity.VACANCY_NAME, vacancyName);
				intent.putExtra(MainActivity.VACANCY_PUBLISH_DATE, date);
				intent.putExtra(MainActivity.VACANCY_PUBLISH_TIME, time);
				intent.putExtra(MainActivity.VACANCY_ID, vacancyId);
				
				activity.startActivity(intent);
			}
		});
		
		super.onPostExecute(result);
	}

}

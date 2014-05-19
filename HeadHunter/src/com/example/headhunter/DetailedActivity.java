package com.example.headhunter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.headhunter.parsers.DetailedVacanciesLoader;

public class DetailedActivity extends Activity {

	public static final String VACANCY_DETAILED_PREFIX = "https://api.hh.ru/vacancies/";
	public static final String VACANCY_SHOW_IN_BROWSER = "https://hh.ru/vacancy/";
	
	private TextView tvVacancyName, tvVacancyPublishDate, tvVacancyPublishTime, tvEmployerName;
	private Button btnShowOnSite;
	private ImageView ivEmployerImage;
	private String id = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		tvVacancyName = (TextView) findViewById(R.id.tvVacancyName);
		tvVacancyPublishDate = (TextView) findViewById(R.id.tvVacancyPublishDate);
		tvVacancyPublishTime = (TextView) findViewById(R.id.tvVacancyPublishTime);
		tvEmployerName = (TextView) findViewById(R.id.tvEmployerName);
		
		ivEmployerImage = (ImageView) findViewById(R.id.ivImage); 
		
		btnShowOnSite = (Button) findViewById(R.id.btnShowOnSite);
		btnShowOnSite.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (id != null){
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(VACANCY_SHOW_IN_BROWSER + id));
					startActivity(intent);
				}
			}
		});
		
		Intent intent = getIntent();
		tvVacancyName.setText(intent.getStringExtra(MainActivity.VACANCY_NAME));
		tvVacancyPublishDate.setText(intent.getStringExtra(MainActivity.VACANCY_PUBLISH_DATE));
		tvVacancyPublishTime.setText(intent.getStringExtra(MainActivity.VACANCY_PUBLISH_TIME));
		tvEmployerName.setText(intent.getStringExtra(MainActivity.VACANCY_EMPLOYER_NAME));
		
		SingleImageLoader imageLoader = new SingleImageLoader(ivEmployerImage);
		imageLoader.execute(intent.getStringExtra(MainActivity.VACANCY_EMPLOYER_IMAGE));
		
		id = intent.getStringExtra(MainActivity.VACANCY_ID);
		DetailedVacanciesLoader detailedVacanciesLoader = new DetailedVacanciesLoader(this, VACANCY_DETAILED_PREFIX + id);
		detailedVacanciesLoader.execute();
	}
	
	public class SingleImageLoader extends AsyncTask<String, Void, Drawable> {
		private ImageView imageView;
		
		public SingleImageLoader(ImageView imageView) {
			this.imageView = imageView;
		}
		
		@Override
		protected Drawable doInBackground(String... images) {
			Drawable drawable = null;
			String name = "image";
			if (images[0] != null) {
				try {
					drawable = Drawable.createFromStream((InputStream) new URL(images[0]).getContent(), name);
				} catch (MalformedURLException e) {
					Log.i("INFO", "Exception (1) in ImageLoader");
					e.printStackTrace();
				} catch (IOException e) {
					Log.i("INFO", "Exception (2) in ImageLoader");
					e.printStackTrace();
				}
			}
			
			return drawable;
		}
		
		@Override
		protected void onPostExecute(Drawable result) {
			super.onPostExecute(result);
			if (result != null){
				this.imageView.setImageDrawable(result);
			}
			else this.imageView.setImageResource(R.drawable.ic_launcher);
		}
	}
}

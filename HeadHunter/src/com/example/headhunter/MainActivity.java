package com.example.headhunter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.headhunter.loaders.VacanciesLoader;

public class MainActivity extends Activity implements OnClickListener {

	public static final String SEARCH_PREFIX = "https://api.hh.ru/vacancies";
	public static final String SEARCH_SUFFIX = "?text=";
	
	public static final String VACANCY_EMPLOYER_IMAGE = "vacancy_employer_image";
	public static final String VACANCY_NAME = "vacancy_name";
	public static final String VACANCY_PUBLISH_DATE = "vacancy_publish_date";
	public static final String VACANCY_PUBLISH_TIME = "vacancy_publish_time";
	public static final String VACANCY_EMPLOYER_NAME = "vacancy_employer_name";
	public static final String VACANCY_ID = "vacancy_id";
	
	private EditText etVacancy;
	private Button btnSearch;
	private VacanciesLoader vacanciesLoader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etVacancy = (EditText) findViewById(R.id.etVacancy);
		
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(this);
		
		Log.i("INFO", "onCreateMainActivity");
		vacanciesLoader = new VacanciesLoader(this, SEARCH_PREFIX);
		vacanciesLoader.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.btnSearch:
			String text = etVacancy.getText().toString();
			if (text.length()>0){
				String searchText = text.replace(' ', '+');
				vacanciesLoader = new VacanciesLoader(this, SEARCH_PREFIX + SEARCH_SUFFIX + searchText);
			}
			else{
				vacanciesLoader = new VacanciesLoader(this, SEARCH_PREFIX + SEARCH_SUFFIX);
			}
			vacanciesLoader.execute();
			break;
		}
	}

}

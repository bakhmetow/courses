package com.example.headhunter.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.example.headhunter.R;
import com.example.headhunter.adapters.MyListAdapter;

public class ImageLoader extends AsyncTask<String, Void, Void> {
	private MyListAdapter adapter;
	private List<Drawable> imageList = new ArrayList<Drawable>();
	private Context context;
	
	public ImageLoader(MyListAdapter adapter, Context context) {
		this.adapter = adapter;
		this.context = context;
		if (!imageList.isEmpty()){
			imageList.clear();
		}
		Log.i("INFO", "An imageLoader created");
	}
	
	@Override
	protected Void doInBackground(String... images) {
		for (int i=0; i<images.length; ++i){
			Drawable drawable = null;
			String name = "image";
			Log.i("INFO", "logo_url: " + images[i]);
			if (images[i] != null) {
				try {
					drawable = Drawable.createFromStream((InputStream) new URL(images[i]).getContent(), name);
					Collections.addAll(imageList, drawable);
					publishProgress();
				} catch (MalformedURLException e) {
					Log.i("INFO", "Exception (1) in ImageLoader");
					e.printStackTrace();
				} catch (IOException e) {
					Log.i("INFO", "Exception (2) in ImageLoader");
					e.printStackTrace();
				}
			}
			else {
				Collections.addAll(imageList, context.getResources().getDrawable(R.drawable.ic_launcher));
				Log.i("INFO", "Image[" + i + "]: is null");
			}
		}
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		adapter.notifyDataSetChanged();
		super.onProgressUpdate(values);
	}
	
	public List<Drawable> getImageList(){
		return this.imageList;
	}
}

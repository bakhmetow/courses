package com.example.headhunter.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.headhunter.R;
import com.example.headhunter.entities.Item;
import com.example.headhunter.loaders.ImageLoader;

public class MyListAdapter extends BaseAdapter {

	private List<Item> data;
	private Context context;
	private List<Drawable> imageList;
	private ImageLoader imageLoader;
	
	
	public MyListAdapter(Context context, List<Item> data){
		this.context = context;
		this.data = data;
		String[] imagesToLoad = new String[data.size()];
		for (int i=0; i < this.data.size(); ++i){
			if (this.data.get(i).getEmployer().getLogo_urls() != null){
				imagesToLoad[i] = this.data.get(i).getEmployer().getLogo_urls().getOriginal();
			}
		}
		imageLoader = new ImageLoader(this, context);
		imageLoader.execute(imagesToLoad);
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Item getItem(int pos) {
		return data.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		Item item = data.get(pos);
		if (convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.lv_vacancy_item, parent, false);
			
			ImageView employerImage = (ImageView) convertView.findViewById(R.id.ivImage);
			TextView vacancyName = (TextView) convertView.findViewById(R.id.tvVacancyName);
			TextView vacancyPublishDate = (TextView) convertView.findViewById(R.id.tvVacancyPublishDate);
			TextView vacancyPublishTime = (TextView) convertView.findViewById(R.id.tvVacancyPublishTime);
			TextView employerName = (TextView) convertView.findViewById(R.id.tvEmployerName);
			
			ViewHolder vh = new ViewHolder(employerImage, vacancyName, vacancyPublishDate, vacancyPublishTime, employerName);
			convertView.setTag(vh);
		}
		
		ViewHolder vh = (ViewHolder) convertView.getTag();
		imageList = imageLoader.getImageList();
		if (pos < imageList.size()){
			vh.employerImage.setImageDrawable(imageList.get(pos));
		}
		else {
			vh.employerImage.setImageResource(R.drawable.ic_launcher);
		}
		vh.vacancyName.setText(item.getName());
		String published = item.getPublished_at();
		String date = published.substring(0,10);
        String time = published.substring(11, 19);
		vh.vacancyDate.setText(date);
		vh.vacancyTime.setText(time);
		vh.employerName.setText(item.getEmployer().getName());
		
		return convertView;
	}
	
	public class ViewHolder{
		public final ImageView employerImage;
		public final TextView vacancyName;
		public final TextView vacancyDate;
		public final TextView vacancyTime;
		public final TextView employerName;
		
		public ViewHolder(ImageView employerImage, TextView vacancyName, TextView vacancyDate, TextView vacancyTime, TextView employerName) {
			this.employerImage = employerImage;
			this.vacancyName = vacancyName;
			this.vacancyDate = vacancyDate;
			this.vacancyTime = vacancyTime;
			this.employerName = employerName;
		}		
	}

}

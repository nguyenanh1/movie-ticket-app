package vn.anhnguyen.ticketmovie.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;

public class MoviePagerAdapter extends PagerAdapter {
    private List<MovieCategory> mList;
    private IEventClick mClick;

    public MoviePagerAdapter(List<MovieCategory> mList, IEventClick mClick) {
        this.mList = mList;
        this.mClick = mClick;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_moviepager, container,false);
        ImageView imageView = view.findViewById(R.id.image_movie);
        final int id = mList.get(position).getMovie().getId();
        final String url = CommonVls.URL+mList.get(position).getMovie().getImageUrl();
        Glide.with(container.getContext()).load(url).fitCenter().into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onClick(id);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    public interface IEventClick{
        void onClick(int id);
    }

}

package vn.anhnguyen.ticketmovie.presentation.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieTime;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;

public class AdapterMovieTime extends RecyclerView.Adapter<AdapterMovieTime.ViewHolder> {
    private List<MovieTime> mList;
    private IEventMovieTime mEvent;

    public AdapterMovieTime(List<MovieTime> mList,IEventMovieTime mEvent) {
        this.mList = mList;
        this.mEvent = mEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rcl_movietime,viewGroup,false);
        return new ViewHolder(view,mEvent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MovieTime data = mList.get(i);
        viewHolder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private IEventMovieTime mEvent;

        @BindView(R.id.text_time)
        CustomTextView mTextTime;
        @BindView(R.id.layout_item_movietime)
        RelativeLayout mLayoutItemMovieTime;

        public ViewHolder(@NonNull View itemView,IEventMovieTime event) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.mEvent = event;
        }

        @SuppressLint("SetTextI18n")
        void bindData(final MovieTime movieTime){
            int hh = movieTime.getTimeStart()/100;
            int mm= movieTime.getTimeStart()%100;
            mTextTime.setText(hh+":"+mm);
            mLayoutItemMovieTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEvent.onClick(movieTime);
                }
            });
        }
    }

    public interface IEventMovieTime {
        void onClick(MovieTime movieTime);
    }
}

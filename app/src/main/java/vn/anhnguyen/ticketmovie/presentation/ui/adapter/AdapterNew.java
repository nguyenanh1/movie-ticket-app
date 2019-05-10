package vn.anhnguyen.ticketmovie.presentation.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.NewsModel;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;

public class AdapterNew extends RecyclerView.Adapter<AdapterNew.ViewHolder> {
    private List<NewsModel> mList;
    private INewEvent mEvent;

    public AdapterNew(List<NewsModel> mList, INewEvent mEvent) {
        this.mList = mList;
        this.mEvent = mEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rcl_new,viewGroup,false);
        return new ViewHolder(view,mEvent,viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewsModel item = mList.get(i);
        viewHolder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private INewEvent mEvent;

        private Context context;

        @BindView(R.id.text_title)
        CustomTextView mTextTitle;
        @BindView(R.id.img_new)
        ImageView mImageNew;

        public ViewHolder(@NonNull View view, INewEvent mEvent, Context context) {
            super(view);
            ButterKnife.bind(this,view);
            this.mEvent = mEvent;
            this.context = context;
        }

        public void bindData(NewsModel item){
            mTextTitle.setText(item.getTitle());
            Glide.with(context).load(item.getImg())
                    .centerCrop()
                    .into(mImageNew);
        }
    }

    public interface INewEvent{

    }
}

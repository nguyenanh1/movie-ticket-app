package vn.anhnguyen.ticketmovie.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.TransMovie;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;

public class AdapterTransaction extends  RecyclerView.Adapter<AdapterTransaction.ViewHolder>{
    private List<TransMovie> mList;
    private IOnEventTransaction mEvent;

    public AdapterTransaction(List<TransMovie> mList, IOnEventTransaction mEvent) {
        this.mList = mList;
        this.mEvent = mEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rcl_trans,viewGroup,false);
        return new ViewHolder(view,mEvent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TransMovie transMovie = mList.get(i);
        viewHolder.bindData(transMovie);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_id_trans)
        CustomTextView mTextIdTrans;
        @BindView(R.id.text_time_trans)
        CustomTextView mTextTimeTrans;
        @BindView(R.id.layout_trans)
        RelativeLayout mLayoutTrans;
        private IOnEventTransaction mEvent;

        public ViewHolder(@NonNull View itemView,IOnEventTransaction mEvent) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.mEvent = mEvent;
        }

        public void bindData(final TransMovie transMovie){
            if(transMovie.getId()!=null){
                mTextIdTrans.setText(String.valueOf(transMovie.getId()));
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            mTextTimeTrans.setText(simpleDateFormat.format(transMovie.getTime()));
            mLayoutTrans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEvent.onClick(transMovie);
                }
            });
        }
    }

    public interface IOnEventTransaction{
        void onClick(TransMovie transMovie);
    }
}

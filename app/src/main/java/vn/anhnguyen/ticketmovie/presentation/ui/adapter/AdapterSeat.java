package vn.anhnguyen.ticketmovie.presentation.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketDetail;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class AdapterSeat extends RecyclerView.Adapter<AdapterSeat.ViewHolder> {
    private IOnEventSeat mEvent;
    private List<TicketDetail> mList;

    public AdapterSeat(IOnEventSeat mEvent, List<TicketDetail> mList) {
        this.mEvent = mEvent;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rcl_seat,viewGroup,false);
        return new ViewHolder(view,mEvent,viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TicketDetail ticketDetail = mList.get(i);
        viewHolder.bindData(ticketDetail);
        viewHolder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.layout_seat)
        LinearLayout mLayoutSeat;
        @BindView(R.id.text_position)
        CustomTextView mTextPosition;

        private IOnEventSeat mEvent;
        private Context context;

        public ViewHolder(@NonNull View itemView, IOnEventSeat mEvent, Context context) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.mEvent = mEvent;
            this.context = context;
        }

        public void bindData(final TicketDetail ticketDetail){

            if(ticketDetail.getPosition().getType()==1){
                 if(ticketDetail.getTicket().getStatus()==1){
                    mLayoutSeat.setBackgroundColor(CommonUtil.getColorFromRes(R.color.color_seat_default,context));
                }else if(ticketDetail.getTicket().getStatus()==2){
                    mLayoutSeat.setBackgroundColor(CommonUtil.getColorFromRes(R.color.color_seat_booked,context));
                    mLayoutSeat.setEnabled(false);
                }else if(ticketDetail.getTicket().getStatus()==3){
                    mLayoutSeat.setEnabled(false);
                    mLayoutSeat.setBackgroundColor(CommonUtil.getColorFromRes(R.color.color_seat_choose,context));
                }else {
                    mLayoutSeat.setBackgroundColor(CommonUtil.getColorFromRes(R.color.color_seat_choose,context));
                }

            }else {
                if(ticketDetail.getTicket().getStatus()==1){
                    mLayoutSeat.setBackgroundColor(CommonUtil.getColorFromRes(R.color.color_seat_vip,context));
                }else if(ticketDetail.getTicket().getStatus()==2){
                    mLayoutSeat.setBackgroundColor(CommonUtil.getColorFromRes(R.color.color_seat_booked,context));
                    mLayoutSeat.setEnabled(false);
                }else if(ticketDetail.getTicket().getStatus()==3){
                    mLayoutSeat.setEnabled(false);
                    mLayoutSeat.setBackgroundColor(CommonUtil.getColorFromRes(R.color.color_seat_choose,context));
                }else {
                    mLayoutSeat.setBackgroundColor(CommonUtil.getColorFromRes(R.color.color_seat_choose,context));
                }
            }
            int col = ticketDetail.getPosition().getCol();
            int row = ticketDetail.getPosition().getRow();
            String position = row+"-"+col;
            mTextPosition.setText(position);
            mLayoutSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEvent.onClick(ticketDetail,ticketDetail.getTicket().getStatus());
                    if(ticketDetail.getTicket().getStatus()==1){
                        ticketDetail.getTicket().setStatus(0);
                    }else {
                        ticketDetail.getTicket().setStatus(1);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface IOnEventSeat {
        void onClick(TicketDetail ticketDetail,int status);
    }
}

package vn.anhnguyen.ticketmovie.presentation.ui.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketRoom;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;

public class AdapterTicketRoom extends RecyclerView.Adapter<AdapterTicketRoom.ViewHolder>{
    private List<TicketRoom> mList;

    public AdapterTicketRoom(List<TicketRoom> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rcl_ticket,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TicketRoom ticketRoom = mList.get(i);
        viewHolder.bindData(ticketRoom);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_id_phong)
        CustomTextView mTextIdPhong;
        @BindView(R.id.text_position)
        CustomTextView mTextPosition;
        @BindView(R.id.text_row)
        CustomTextView mTextRow;
        @BindView(R.id.text_col)
        CustomTextView mTextCol;
        @BindView(R.id.text_id_ticket)
        CustomTextView mTextIdTicket;
        @BindView(R.id.text_price)
        CustomTextView mTextPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bindData(TicketRoom ticketRoom){
            if(ticketRoom.getZoomDetailEntity().getRoom()!=null){
                mTextIdPhong.setText(String.valueOf(ticketRoom.getZoomDetailEntity().getRoom()));
            }
            if(ticketRoom.getZoomDetailEntity().getPostion()!=null){
                mTextPosition.setText(String.valueOf(ticketRoom.getZoomDetailEntity().getPostion()));
            }
            if(ticketRoom.getZoomDetailEntity().getCol()!=null){
                mTextCol.setText(String.valueOf(ticketRoom.getZoomDetailEntity().getCol()));
            }
            if(ticketRoom.getZoomDetailEntity().getRow()!=null){
                mTextRow.setText(String.valueOf(ticketRoom.getZoomDetailEntity().getRow()));
            }
            if(ticketRoom.getTicketEntity().getIdTicket()!=null){
                mTextIdTicket.setText(String.valueOf(ticketRoom.getTicketEntity().getIdTicket()));
            }
            if(ticketRoom.getTicketEntity().getPrice()!=null){
                DecimalFormat format = new DecimalFormat("###,###");
                mTextPrice.setText(format.format(ticketRoom.getTicketEntity().getPrice())+ " VND");
            }
        }
    }
}

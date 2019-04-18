package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.Movie;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieTime;
import vn.anhnguyen.ticketmovie.domain.model.response.Room;
import vn.anhnguyen.ticketmovie.domain.model.response.Ticket;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketDetail;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterTicket;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterTicket;
import vn.anhnguyen.ticketmovie.presentation.ui.adapter.AdapterSeat;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class TicketActivity extends BaseActivity implements IPresenterTicket.IViewTicket,
    AdapterSeat.IOnEventSeat{
    @BindView(R.id.recycler_seat)
    RecyclerView mRecyclerSeat;
    @BindView(R.id.text_price)
    CustomTextView mTextPrice;
    @BindView(R.id.text_num_seat)
    CustomTextView mTextNumSeat;
    @BindView(R.id.text_name_movie)
    CustomTextView mTextNameMovie;
    @BindView(R.id.text_old)
    CustomTextView mTextOld;

    private final static String DATA = "data";
    private final static String MOVIE_TIME = "movie_time";
    private MovieTime movieTime =null;

    private AdapterSeat mAdapterSeat;
    private List<TicketDetail> mList;

    private List<TicketDetail> mListBook;

    private IPresenterTicket mPresenter;

    public static Intent getIntent(Context context, MovieTime movieTime){
        Intent i = new Intent(context,TicketActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MOVIE_TIME,movieTime);
        i.putExtra(DATA,bundle);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        //setTitle(CommonUtil.getStringFromRes(R.string.movie,this));
        initView();

        getData();

    }

    private void setUpRecyclerSeat(int col) {
        mList = new ArrayList<>();
        mAdapterSeat = new AdapterSeat(this,mList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,col,GridLayoutManager.HORIZONTAL,false);
        mRecyclerSeat.setLayoutManager(layoutManager);
        mRecyclerSeat.setAdapter(mAdapterSeat);
        mRecyclerSeat.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getmButtonBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mPresenter.getRooom(movieTime.getIdRoom());
        mPresenter.getMovie(movieTime.getIdMovie());
    }

    private void getData() {
        Bundle bundle = getIntent().getBundleExtra(DATA);
        this.movieTime = (MovieTime) bundle.getSerializable(MOVIE_TIME);

    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterTicket(this);
        mListBook = new ArrayList<>();
    }

    @Override
    public void showListTicket(List<TicketDetail> list) {
        mList.clear();
        mList.addAll(list);
        mAdapterSeat.notifyDataSetChanged();
    }

    @Override
    public void showRoom(Room room) {
        setUpRecyclerSeat(room.getNumCol());
        setTitle(room.getNameRoom());
        mPresenter.getTicket(movieTime.getId());
    }

    @Override
    public void showMovie(Movie movie) {
        mTextNameMovie.setText(movie.getName());
        if (movie.getOld() != null) {
            switch (movie.getOld()) {
                case 18:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C18");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_red, TicketActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_red, TicketActivity.this));
                    break;
                case 16:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C16");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_orangel, TicketActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_organe, TicketActivity.this));
                    break;
                case 13:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C13");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_yellow, TicketActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_yellow, TicketActivity.this));
                    break;
                case 0:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText(" P ");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_p, TicketActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_p, TicketActivity.this));
                    break;
                default:
                    mTextOld.setVisibility(View.GONE);
                    break;

            }
        }
    }

    @Override
    public void onClick(TicketDetail ticketDetail, int status) {
        if(status==1){
            mListBook.add(ticketDetail);
        }
        if(status==0){
            deleteTicketdetail(ticketDetail);
        }
        updateList();
    }
    private void deleteTicketdetail(TicketDetail ticketDetail){
        int index = -1;
        for(TicketDetail temp:mListBook){
            if(temp.getTicket().getIdTicket()==ticketDetail.getTicket().getIdTicket()){
                index = mListBook.indexOf(temp);
                break;
            }
        }
        if(index!=-1){
            mListBook.remove(index);
        }

    }

    @SuppressLint("SetTextI18n")
    private void updateList(){
        int price = 0;
        for(TicketDetail temp:mListBook){
            price = price+temp.getTicket().getPrice();
        }
        DecimalFormat format = new DecimalFormat("###,###.###");
        String priceS = format.format(price);
        if(mListBook.size()!=0){
            mTextPrice.setText(priceS+ "đ");
            mTextNumSeat.setText(mListBook.size() + "ghế");
        }else {
            mTextPrice.setText("");
            mTextNumSeat.setText("");
        }

    }
}

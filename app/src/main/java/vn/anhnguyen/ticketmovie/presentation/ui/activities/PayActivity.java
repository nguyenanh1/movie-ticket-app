package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.Movie;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieTime;
import vn.anhnguyen.ticketmovie.domain.model.response.Room;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketRoom;
import vn.anhnguyen.ticketmovie.domain.model.response.TransMovie;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterPay;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.adapter.AdapterTicketRoom;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomButton;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;


public class PayActivity extends BaseActivity implements IPresenterPay.IViewPay {
    @BindView(R.id.image_movie)
    ImageView mImageMovie;
    @BindView(R.id.text_name_movie)
    CustomTextView mTextNameMovie;
    @BindView(R.id.text_old)
    CustomTextView mTextOld;
    @BindView(R.id.text_old_contain)
    CustomTextView mTextOldContain;
    @BindView(R.id.text_date)
    CustomTextView mTextDate;
    @BindView(R.id.text_time)
    CustomTextView mTextTime;
    @BindView(R.id.text_room_name)
    CustomTextView mTextRoomName;
    @BindView(R.id.text_money)
    CustomTextView mTextMoney;
    @BindView(R.id.button_pay)
    CustomButton mButtonPay;
    @BindView(R.id.recycler_ticket)
    RecyclerView mRecyclerTicket;
    @BindView(R.id.text_num_ticket)
    CustomTextView mTextNumTicket;

    private final static String DATA = "data";
    private final static String TRANS_TICKET = "transaction";
    private final static String MOVI_TIME = "movietime";

    private IPresenterPay mPresenter;

    private TransMovie transMovie = null;
    private MovieTime movieTime = null;

    private List<TicketRoom> mList;
    private AdapterTicketRoom mAdapter;


    public static Intent getIntent(Context context, TransMovie transMovie, MovieTime movieTime){
        Intent intent = new Intent(context,PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(TRANS_TICKET,  transMovie);
        bundle.putSerializable(MOVI_TIME,movieTime);
        intent.putExtra(DATA,bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(CommonUtil.getStringFromRes(R.string.pay,this));
        setRecyclerTicketRoom();
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mPresenter.unHolder(transMovie.getId());
            }
        }).start();
    }

    private void setRecyclerTicketRoom() {
        mList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mAdapter = new AdapterTicketRoom(mList);
        mRecyclerTicket.setLayoutManager(layoutManager);
        mRecyclerTicket.setAdapter(mAdapter);
        mRecyclerTicket.setHasFixedSize(true);
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
        mButtonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.booking(transMovie.getId());
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn hủy quá trình đặt vé ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.unHolder(transMovie.getId());
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterPay(this);
        Bundle bundle = getIntent().getBundleExtra(DATA);
        transMovie = (TransMovie) bundle.getSerializable(TRANS_TICKET);
        movieTime = (MovieTime) bundle.getSerializable(MOVI_TIME);
        showMovieTime();
    }

    @SuppressLint("SetTextI18n")
    private void showMovieTime() {
        if(movieTime!=null){
            mTextDate.setText(CommonUtil.convertToDDMMYY(movieTime.getDateStart()));
            int time = movieTime.getTimeStart();
            int hh = time/100;
            int mm = time%100;
            mTextTime.setText(hh+":"+mm);
            mPresenter.getRooom(movieTime.getIdRoom());
            mPresenter.getMovie(movieTime.getIdMovie());
            mPresenter.getTicketRoom(transMovie.getId());
        }

    }

    @Override
    public void showRoom(Room room) {
        mTextRoomName.setText(room.getNameRoom());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showMovie(Movie movie) {
        if(movie.getThumb()!=null && !movie.getThumb().isEmpty()){
            Glide.with(this).load(movie.getThumb()).centerCrop().into(mImageMovie);
        }
        if(!movie.getName().isEmpty()){
            mTextNameMovie.setText(movie.getName());
        }
        if (movie.getOld() != null) {
            switch (movie.getOld()) {
                case 18:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C18");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_red, PayActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_red, PayActivity.this));
                    break;
                case 16:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C16");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_orangel, PayActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_organe, PayActivity.this));
                    break;
                case 13:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C13");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_yellow, PayActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_yellow, PayActivity.this));
                    break;
                case 0:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText(" P ");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_p, PayActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_p, PayActivity.this));
                    break;
                default:
                    mTextOld.setVisibility(View.GONE);
                    break;

            }
        }
        if (movie.getOld() != null) {
            if (movie.getOld() == 0) {
                mTextOldContain.setText("P - Phổ biến rộng rãi đến mọi đối tượng");
            } else {
                mTextOldContain.setText("C" + movie.getOld()
                        + " - Không dành cho khán giả dưới "
                        + movie.getOld() + " tuổi");
            }
        }

    }

    @Override
    public void showTicketRoom(List<TicketRoom> listTicketRoom) {
        mList.clear();
        mList.addAll(listTicketRoom);
        mAdapter.notifyDataSetChanged();
        showTong();
    }

    @SuppressLint("SetTextI18n")
    private void showTong() {
        int tong = 0;
        for (TicketRoom temp: mList){
            tong = tong+temp.getTicketEntity().getPrice();
        }
        DecimalFormat format = new DecimalFormat("###,###");
        mTextMoney.setText(format.format(tong)+" VNĐ");
        mTextNumTicket.setText(String.valueOf(mList.size()));
    }

    @Override
    public void unHolderSuccess() {
        if(!isFinishing()) {
            finish();
        }
    }

    @Override
    public void unHolderFail() {

    }

    @Override
    public void bookingSuccess() {
        Intent i = MainActivity.getIntent(this);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}

package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieTime;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMovieTime;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.adapter.AdapterMovieTime;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class MovieTimeActivity extends BaseActivity implements IPresenterMovieTime.IViewMovieTime,
        AdapterMovieTime.IEventMovieTime {
    @BindView(R.id.text_time_choose)
    CustomTextView mTextTimeChoose;
    @BindView(R.id.recycler_time)
    RecyclerView mRecyclerTime;

    private final static String ID_MOVIE = "id_movie";
    private final static String NAME_MOVIE = "name_movie";
    private int id;

    private IPresenterMovieTime mPresenter;

    private int dateM = 0;

    private AdapterMovieTime mAdapter;
    private List<MovieTime> mList;

    public static Intent getIntentExtra(Context context,Integer idmovie,String name){
        Intent i = new Intent(context,MovieTimeActivity.class);
        i.putExtra(ID_MOVIE,idmovie);
        i.putExtra(NAME_MOVIE,name);
        return i;
    }

    private HorizontalCalendar horizontalCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_time);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra(ID_MOVIE,-1);
        String name = getIntent().getStringExtra(NAME_MOVIE);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(name);
        setUpRecycletTime();
        initView();
    }

    private void setUpRecycletTime() {
        mList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL   ,false);
        mRecyclerTime.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterMovieTime(mList,this);
        mRecyclerTime.setAdapter(mAdapter);
        mRecyclerTime.setHasFixedSize(true);
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterMovieTIme(this);
        Calendar start = Calendar.getInstance();
        start.add(Calendar.DAY_OF_MONTH,-1);
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_MONTH,14);
        Calendar mCalDefault = Calendar.getInstance();
        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendar_view)
                .range(start, end)
                .datesNumberOnScreen(7)
                .defaultSelectedDate(mCalDefault)
                .build();
        setTextDateSelected(mCalDefault);
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                setTextDateSelected(date);
            }
        });
    }

    private void setTextDateSelected(Calendar cal){
        Date date = new Date(cal.getTimeInMillis());
        SimpleDateFormat format = new SimpleDateFormat("E dd, MM, yyyy");
        mTextTimeChoose.setText(format.format(date));
        SimpleDateFormat formatInt = new SimpleDateFormat("yyyyMMdd");
        try{
            dateM = Integer.parseInt(formatInt.format(date));
            mPresenter.getMovieTime(id,dateM);
        }catch (Exception e){
            e.printStackTrace();
        }
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
        if(!SharePrefUtils.instance().getLoginStatus()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn vui lòng đăng nhập để lấy thông tin lịch chiếu");
            builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    gotoLogin(false);
                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    onBackPressed();
                }
            });
            builder.setCancelable(false);
            builder.create().show();
        }
    }

    @Override
    public void onClick(MovieTime movieTime) {
        Intent i = TicketActivity.getIntent(MovieTimeActivity.this,movieTime);
        startActivity(i);
    }

    @Override
    public void showMovieTime(List<MovieTime> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}

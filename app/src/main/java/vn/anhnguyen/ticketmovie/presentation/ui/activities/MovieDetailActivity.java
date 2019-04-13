package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.Category;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMovieDetail;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomButton;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class MovieDetailActivity extends BaseActivity implements IPresenterMovieDetail.IViewMovieDetail,
        YouTubePlayer.OnInitializedListener {
    @BindView(R.id.text_movie_name)
    CustomTextView mTextMovieName;
    @BindView(R.id.button_booking)
    CustomButton mButtonBooking;
    @BindView(R.id.text_contain)
    CustomTextView mTextContain;
    @BindView(R.id.text_old)
    CustomTextView mTextOld;
    @BindView(R.id.text_time_start)
    CustomTextView mTextTimeStart;
    @BindView(R.id.text_cate)
    CustomTextView mTextCate;
    @BindView(R.id.text_director)
    CustomTextView mTextDirector;
    @BindView(R.id.text_actor)
    CustomTextView mTextAcor;
    @BindView(R.id.text_duration)
    CustomTextView mTextDuration;
    @BindView(R.id.youtube_player)
    YouTubePlayerView mYoutube;
    private IPresenterMovieDetail mPresenter;

    private static final String ID = "id";

    private String link = "";

    public static Intent getIntetExtra(Context context,int id){
        Intent intent = new Intent(context,MovieDetailActivity.class);
        intent.putExtra(ID,id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(CommonUtil.getStringFromRes(R.string.movie,this));
        initView();
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterMovieDetail(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int id = getIntent().getIntExtra(ID,-1);
        mPresenter.getMovieDetail(id);
        getmButtonBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mYoutube.initialize(CommonUtil.getStringFromRes(R.string.ap_youtube,this),this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showMovieDetail(MovieCategory movieCategory) {
        if (movieCategory.getMovie().getName() != null) {
            mTextMovieName.setText(movieCategory.getMovie().getName());
        }
        if (movieCategory.getMovie().getContain() != null) {
            mTextContain.setText(movieCategory.getMovie().getContain());
        }
        if (movieCategory.getMovie().getOld() != null) {
            if (movieCategory.getMovie().getOld() == 0) {
                mTextOld.setText("P - Phổ biến rộng rãi đến mọi đối tượng");
            } else {
                mTextOld.setText("C" + movieCategory.getMovie().getOld()
                        + " - Không dành cho khán giả dưới "
                        + movieCategory.getMovie().getOld() + " tuổi");
            }
        }
        if (movieCategory.getMovie().getPartTime() != null) {
            mTextTimeStart.setText(CommonUtil.convertToDDMMYY(movieCategory.getMovie().getPartTime()));
        }

        if (movieCategory.getCategorys() != null && movieCategory.getCategorys().size() != 0) {
            String cate = "";
            for (Category temp : movieCategory.getCategorys()) {
                cate = cate + temp.getName() + ", ";
            }
            cate = cate.trim();
            cate = cate.substring(0, cate.length() - 1);
            mTextCate.setText(cate);
        }
        if (movieCategory.getMovie().getDirector() != null) {
            mTextDirector.setText(movieCategory.getMovie().getDirector());
        }
        if (movieCategory.getMovie().getActor() != null) {
            mTextAcor.setText(movieCategory.getMovie().getActor());
        }

        if (movieCategory.getMovie().getTime() != null) {
            int time = movieCategory.getMovie().getTime();
            int hour = time / 60;
            int minute = time - hour * 60;

            mTextDuration.setText(hour + "Giờ " + minute + "Phút");
        }
        this.link = movieCategory.getMovie().getTrailer();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(link);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}

package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class YouTubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    @BindView(R.id.youtube_player)
    YouTubePlayerView mYoutube;
    @BindView(R.id.button_close)
    ImageButton mButtonClose;

    private static final String URL = "url";

    private String link = "";

    public static Intent getIntentExtra(Context context,String url){
        Intent i = new Intent(context,YouTubePlayerActivity.class);
        i.putExtra(URL,url);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_player);
        ButterKnife.bind(this);
        link = getIntent().getStringExtra(URL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mYoutube.initialize(CommonUtil.getStringFromRes(R.string.ap_youtube,this),this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(link);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(YouTubePlayerActivity.this,"Có lỗi!",Toast.LENGTH_SHORT).show();;
    }
}

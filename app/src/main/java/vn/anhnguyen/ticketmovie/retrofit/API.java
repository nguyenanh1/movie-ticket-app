package vn.anhnguyen.ticketmovie.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyLoginRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyRegisterRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.LoginResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetailMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovie;

public interface API {
    //Đăng ký
    @POST("/authenication/user/register")
    Call<BaseResponse> register(@Body BodyRegisterRequest bodyRegisterRequest);

    //Đăng nhập
    @POST("/authenication/user/login")
    Call<LoginResponse> login(@Body BodyLoginRequest bodyLoginRequest);

    //GET top 3 movie
    @GET("/user/movie/getTop")
    Call<ResponseGetMovie> getTopMovie();

    //GET is Showing movie
    @GET("/user/movie/getIsShowing")
    Call<ResponseGetMovie> getIsShowingMovie(@Query("partdate") int partdate,
                                             @Query("start") int start,
                                             @Query("limit") int limit);

    //Get Comming soon movie
    @GET("/user/movie/getCommingSoon")
    Call<ResponseGetMovie> getCommingSoonMovie(@Query("partdate") int partdate,
                                               @Query("start") int start,
                                               @Query("limit") int limit);

    // Xem chi tiết phim
    @GET("/user/movie/getDetail")
    Call<ResponseGetDetailMovie> getMovieDetail(@Query("id") int id);
}

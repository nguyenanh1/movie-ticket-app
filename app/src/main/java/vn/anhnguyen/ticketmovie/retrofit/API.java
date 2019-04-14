package vn.anhnguyen.ticketmovie.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangePasswordRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangeProfileRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyLoginRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyRegisterRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.LoginResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ProfileResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetailMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovie;

public interface API {
    //Đăng ký
    @POST("/authenication/user/register")
    Call<BaseResponse> register(@Body BodyRegisterRequest bodyRegisterRequest);

    //Đăng nhập
    @POST("/authenication/user/login")
    Call<LoginResponse> login(@Body BodyLoginRequest bodyLoginRequest);

    //Get Profile
    @GET("/authenication/user/profile")
    Call<ProfileResponse> getProfile(@Header("token")String token);

    //Change Profile
    @PUT("/authenication/user/change-profile")
    Call<BaseResponse> changeProfile(@Header("token")String token,
                                    @Body BodyChangeProfileRequest bodyChangeProfileRequest);

    //Change Passowrd
    @PUT("/authenication/user/change-password")
    Call<BaseResponse> changePassword(@Header("token")String token,
                                      @Body BodyChangePasswordRequest bodyChangePassword);

    //Logout
    @POST("/authenication/user/logout")
    Call<BaseResponse> logout(@Header("token")String token);

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

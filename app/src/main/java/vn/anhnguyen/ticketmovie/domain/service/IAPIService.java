package vn.anhnguyen.ticketmovie.domain.service;

import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangePasswordRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangeProfileRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyLoginRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyRegisterRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.LoginResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ProfileResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetaiLMovieTime;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetailMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetInfoRoom;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovieTime;

public interface IAPIService {
    //Đăng ký
    BaseResponse register(BodyRegisterRequest request);

    //Đăng nhập
    LoginResponse login(BodyLoginRequest request);

    //Profile
    ProfileResponse getProfile(String token);

    //Change Profile
    BaseResponse changeProfile(String token, BodyChangeProfileRequest request);

    //Change Password
    BaseResponse changePasswrod(String token, BodyChangePasswordRequest request);

    //Logout
    BaseResponse logout(String token);

    //Select TOP 5 MOVIE
    ResponseGetMovie getTopMovie();

    //GET MOVIE IS SHOWING
    ResponseGetMovie getIsShowingMovie(int partdate, int start, int limit);

    //GET MOVIE COMMING SOON
    ResponseGetMovie getCommingSoonMovie(int partdate, int start, int limit);

    //GET MOVIE DETAIL
    ResponseGetDetailMovie getMovieDetail(int id);

    //GET TIME
    ResponseGetMovieTime getMovieTime(String token,int idMovie,int date);

    //GET MOVIE TIME DETAIL
    ResponseGetDetaiLMovieTime getDetailMovieTime(String token,int idMovieTime);

    //GET Room
    ResponseGetInfoRoom getRoom(String token,int idRoom);
}

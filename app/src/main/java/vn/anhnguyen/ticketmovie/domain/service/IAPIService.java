package vn.anhnguyen.ticketmovie.domain.service;

import vn.anhnguyen.ticketmovie.domain.model.request.BodyLoginRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyRegisterRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.LoginResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetailMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovie;

public interface IAPIService {
    //Đăng ký
    BaseResponse register(BodyRegisterRequest request);
    //Đăng nhập
    LoginResponse login(BodyLoginRequest request);

    ResponseGetMovie getTopMovie();

    ResponseGetMovie getIsShowingMovie(int partdate, int start, int limit);

    ResponseGetMovie getCommingSoonMovie(int partdate, int start, int limit);

    ResponseGetDetailMovie getMovieDetail(int id);


}

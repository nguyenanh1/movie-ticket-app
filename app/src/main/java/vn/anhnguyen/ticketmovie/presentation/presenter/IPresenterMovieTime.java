package vn.anhnguyen.ticketmovie.presentation.presenter;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.model.response.MovieTime;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterMovieTime extends BasePresenter {
    void getMovieTime(int idMovie,int date);
    interface IViewMovieTime extends BaseView{
        void showMovieTime(List<MovieTime> list);
    }
}

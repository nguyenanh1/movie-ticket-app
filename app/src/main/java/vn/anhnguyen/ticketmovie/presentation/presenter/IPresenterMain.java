package vn.anhnguyen.ticketmovie.presentation.presenter;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.model.response.Movie;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterMain extends BasePresenter {
    void getTopMovie();
    void getMovieIsShowing(int start,int limit);
    void getMovieCommingSoon(int start,int limit);
    void logout();
    interface IViewMain extends BaseView{
        void showMovie(List<MovieCategory> movieCategory);
        void showLogoutSuccess();
    }
}

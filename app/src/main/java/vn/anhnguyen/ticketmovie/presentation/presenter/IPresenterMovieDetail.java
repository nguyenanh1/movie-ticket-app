package vn.anhnguyen.ticketmovie.presentation.presenter;

import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterMovieDetail extends BasePresenter {
    void getMovieDetail(int id);
    interface IViewMovieDetail extends BaseView {
        void showMovieDetail(MovieCategory movieCategory);
    }
}

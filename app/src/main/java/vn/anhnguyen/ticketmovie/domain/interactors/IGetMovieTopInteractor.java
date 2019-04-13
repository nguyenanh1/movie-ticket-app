package vn.anhnguyen.ticketmovie.domain.interactors;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;

public interface IGetMovieTopInteractor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void getTopMovieSuccess(List<MovieCategory> list);
    }
}

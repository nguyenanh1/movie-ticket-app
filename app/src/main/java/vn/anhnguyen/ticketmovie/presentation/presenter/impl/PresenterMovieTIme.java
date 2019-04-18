package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetMovieTimeInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetMovieTimeInteractor;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieTime;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMovieTime;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterMovieTIme extends AbstractPresenter implements IPresenterMovieTime ,
        IGetMovieTimeInteractor.Callback {
    private PresenterMovieTIme.IViewMovieTime mView;

    public PresenterMovieTIme(Executor executor, MainThread mainThread, IViewMovieTime mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void getMovieTime(int idMovie, int date) {
        mView.showProgress();
        IGetMovieTimeInteractor interactor = new GetMovieTimeInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),idMovie,date);
        interactor.execute();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void getListMovieTimeSuccess(List<MovieTime> list) {
        mView.hideProgress();
        mView.showMovieTime(list);
    }

    @Override
    public void onSessionTimeout(String message) {
        mView.hideProgress();
        mView.gotoLogin(false,message);
    }

    @Override
    public void onTokenTimeout(String message) {
        mView.hideProgress();
        mView.gotoLogin(false,message);
    }

    @Override
    public void onLoginOtherDevice(String message) {
        mView.hideProgress();
        mView.gotoLogin(false,message);
    }

    @Override
    public void onNoInternetConnection(String message) {
        mView.hideProgress();
        mView.showToast(message);
    }

    @Override
    public void onFailMessage(String message) {
        mView.hideProgress();
        mView.showToast(message);
    }
}

package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetDetailMovieInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetDetailMovieInteractor;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMovieDetail;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterMovieDetail extends AbstractPresenter implements IPresenterMovieDetail,
        IGetDetailMovieInteractor.Callback {
    private IPresenterMovieDetail.IViewMovieDetail mView;

    public PresenterMovieDetail(Executor executor, MainThread mainThread, IViewMovieDetail mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void getMovieDetail(int id) {
        mView.showProgress();
        IGetDetailMovieInteractor interactor = new GetDetailMovieInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),id);
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
    public void getDetailMovieSuccess(MovieCategory movieCategory) {
        mView.hideProgress();
        mView.showMovieDetail(movieCategory);
    }

    @Override
    public void onSessionTimeout(String message) {
        mView.hideProgress();
        mView.showToast(message);
    }

    @Override
    public void onTokenTimeout(String message) {
        mView.hideProgress();
        mView.showToast(message);
    }

    @Override
    public void onLoginOtherDevice(String message) {
        mView.hideProgress();
        mView.showToast(message);
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

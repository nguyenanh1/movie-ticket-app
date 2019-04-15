package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetMovieCommingSoonInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetMovieIsShowingInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetMovieTopInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.ILogoutInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetMovieCommingSoonInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetMovieIsShowingInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetMovieTopInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.LogoutInteractor;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMain;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterMain extends AbstractPresenter implements IPresenterMain,
        IGetMovieTopInteractor.Callback, IGetMovieIsShowingInteractor.Callback ,
        IGetMovieCommingSoonInteractor.Callback, ILogoutInteractor.Callback{
    IPresenterMain.IViewMain mView;

    public PresenterMain(Executor executor, MainThread mainThread, IViewMain mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void getTopMovie() {
        IGetMovieTopInteractor interactor = new GetMovieTopInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance());
        interactor.execute();
    }

    @Override
    public void getMovieIsShowing(int start, int limit) {
        IGetMovieIsShowingInteractor interactor = new GetMovieIsShowingInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),start,limit);
        interactor.execute();
    }

    @Override
    public void getMovieCommingSoon(int start, int limit) {
        IGetMovieCommingSoonInteractor interactor = new GetMovieCommingSoonInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),start,limit);
        interactor.execute();
    }

    @Override
    public void logout() {
        mView.showProgress();
        ILogoutInteractor interactor = new LogoutInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance());
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
    public void getTopMovieSuccess(List<MovieCategory> list) {
        mView.showMovie(list);
    }
    @Override
    public void getMovieIsShowingSuccess(List<MovieCategory> list) {
        mView.showMovie(list);
    }

    @Override
    public void getMovieCommingSoonSuccess(List<MovieCategory> list) {
        mView.showMovie(list);
    }

    @Override
    public void onSessionTimeout(String message) {
        mView.showToast(message);
    }

    @Override
    public void onTokenTimeout(String message) {
        mView.showToast(message);
    }

    @Override
    public void onLoginOtherDevice(String message) {
        mView.showToast(message);
    }

    @Override
    public void onNoInternetConnection(String message) {
        mView.showToast(message);
    }

    @Override
    public void onFailMessage(String message) {
        mView.showToast(message);
    }


    @Override
    public void logoutSuccess() {
        mView.hideProgress();
        mView.showLogoutSuccess();
    }
}

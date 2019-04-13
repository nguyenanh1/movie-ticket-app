package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.ILoginInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.LoginInteractor;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterLogin;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterLogin extends AbstractPresenter implements IPresenterLogin,
        ILoginInteractor.Callback {
    private IPresenterLogin.IViewLogin mView;

    public PresenterLogin(Executor executor, MainThread mainThread, IViewLogin mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void login(String email, String password) {
        mView.showProgress();
        ILoginInteractor interactor = new LoginInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(), email, password);
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
    public void loginSuccess(String message) {
        mView.hideProgress();
        mView.loginSuccess(message);
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

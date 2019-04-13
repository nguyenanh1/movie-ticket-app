package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IRegisterInteactor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.RegisterInteractor;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterRegister;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterRegister extends AbstractPresenter implements IPresenterRegister ,
        IRegisterInteactor.Callback {
    private IPresenterRegister.IViewRegister mView;

    public PresenterRegister(Executor executor, MainThread mainThread, IViewRegister mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void register(String email, String password, String rePassword, String lastname, String name, long birthday, int gender, String phone, String address) {
        mView.showProgress();
        IRegisterInteactor inteactor = new RegisterInteractor(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),
                this,APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),
                email,password,rePassword,lastname,name,birthday,gender,phone,address);
        inteactor.execute();
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
    public void registerSuccess(String message) {
        mView.hideProgress();
        mView.showRegisterSuccess(message);
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

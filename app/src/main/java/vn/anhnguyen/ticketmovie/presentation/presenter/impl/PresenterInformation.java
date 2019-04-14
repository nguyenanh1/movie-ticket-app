package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IChangeProfileInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.ChangeProfileInteractor;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterInformation;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterInformation extends AbstractPresenter implements IPresenterInformation ,
        IChangeProfileInteractor.Callback {
    private IPresenterInformation.IViewInformation mView;

    public PresenterInformation(Executor executor, MainThread mainThread, IViewInformation mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void changProfile(String lastname, String name, Long birthday, Integer gender, String phone, String address) {
        mView.showProgress();
        IChangeProfileInteractor interactor = new ChangeProfileInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),lastname,name,birthday,gender,phone,address);
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
    public void changeProfileSuccess(String message) {
        mView.hideProgress();
        mView.showToast(message);
        mView.showChangProfileSuccess();
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

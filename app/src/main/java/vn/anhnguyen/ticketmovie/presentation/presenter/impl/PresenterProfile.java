package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetProfileInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetProfileIneractor;
import vn.anhnguyen.ticketmovie.domain.model.response.User;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterProfile;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterProfile extends AbstractPresenter implements IPresenterProfile,
        IGetProfileInteractor.Calllback {
    private IPresenterProfile.IViewProfile mView;

    public PresenterProfile(Executor executor, MainThread mainThread, IViewProfile mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void getProfile() {
        mView.showProgress();
        IGetProfileInteractor interactor = new GetProfileIneractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance());
        interactor.execute();
    }

    @Override
    public void resume() {
        getProfile();
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
    public void getProfileSuccess(User user) {
        mView.hideProgress();
        mView.showProfile(user);
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

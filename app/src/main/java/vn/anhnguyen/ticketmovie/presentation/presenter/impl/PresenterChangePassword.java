package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IChangePasswordInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.ChangPasswordInteractor;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterChangePassword;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterChangePassword extends AbstractPresenter implements IPresenterChangePassword ,
        IChangePasswordInteractor.Callback {
    private IPresenterChangePassword.IViewChangePassword mView;

    public PresenterChangePassword(Executor executor, MainThread mainThread, IViewChangePassword mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void changePassword(String oldPass, String newPass, String reNewPass) {
        mView.showProgress();
        IChangePasswordInteractor interactor = new ChangPasswordInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),oldPass,newPass,reNewPass);
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
    public void changePasswordSuccess(String message) {
        mView.hideProgress();
        mView.showChangePasswordSuccess();
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

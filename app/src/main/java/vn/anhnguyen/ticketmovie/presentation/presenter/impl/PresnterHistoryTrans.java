package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetTransactionInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetTransactionInteractor;
import vn.anhnguyen.ticketmovie.domain.model.response.TransMovie;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterHistoryTrans;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresnterHistoryTrans extends AbstractPresenter implements IPresenterHistoryTrans,
        IGetTransactionInteractor.Callback {
    private IPresenterHistoryTrans.IViewHistoryTrans mView;

    public PresnterHistoryTrans(Executor executor, MainThread mainThread, IViewHistoryTrans mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void getTransactionSuccess(List<TransMovie> list) {
        mView.hideProgress();
        mView.showHistoryTrans(list);
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

    @Override
    public void getHistoryTrans() {
        mView.showProgress();
        IGetTransactionInteractor interactor = new GetTransactionInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance());
        interactor.execute();
    }

    @Override
    public void resume() {
        getHistoryTrans();
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
}

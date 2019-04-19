package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetDetailMovieInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetDetailMovieTimeInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetRoomInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IHoldTicketInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetDetailMovieInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetDetailMovieTimeInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetRoomInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.HoldTicketInteractor;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.domain.model.response.Room;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketDetail;
import vn.anhnguyen.ticketmovie.domain.model.response.TransMovie;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterTicket;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterTicket extends AbstractPresenter implements IPresenterTicket,
        IGetDetailMovieTimeInteractor.Callback, IGetRoomInteractor.Callback,
        IGetDetailMovieInteractor.Callback , IHoldTicketInteractor.Callback {
    private IPresenterTicket.IViewTicket mView;

    public PresenterTicket(Executor executor, MainThread mainThread, IViewTicket mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void getTicket(int idMovieTime) {
        mView.showProgress();
        IGetDetailMovieTimeInteractor interactor = new GetDetailMovieTimeInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),idMovieTime);
        interactor.execute();
    }

    @Override
    public void getRooom(int idRoom) {
        mView.hideProgress();
        IGetRoomInteractor interactor = new GetRoomInteractor(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),this,
                APIService.getInstance(),DeviceUtils.instance(),SharePrefUtils.instance(),idRoom);
        interactor.execute();
    }

    @Override
    public void getMovie(int idMovie) {
        mView.showProgress();
        IGetDetailMovieInteractor interactor = new GetDetailMovieInteractor(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),this,
                APIService.getInstance(),DeviceUtils.instance(),SharePrefUtils.instance(),idMovie);
        interactor.execute();
    }

    @Override
    public void hold(List<Integer> mListId) {
        mView.showProgress();
        IHoldTicketInteractor interactor = new HoldTicketInteractor(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),this,
                APIService.getInstance(),DeviceUtils.instance(),SharePrefUtils.instance(),mListId);
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
    public void holdSucess(TransMovie transMovie) {
        mView.hideProgress();
        mView.holdSucess(transMovie);
    }

    @Override
    public void holdFail(String message) {
        mView.hideProgress();
        mView.showToast(message);
    }

    @Override
    public void getDetailMovieTimeSuccwss(List<TicketDetail> mList) {
        mView.hideProgress();
        mView.showListTicket(mList);
    }

    @Override
    public void getRoomSuccess(Room room) {
        mView.hideProgress();
        mView.showRoom(room);
    }

    @Override
    public void getDetailMovieSuccess(MovieCategory movieCategory) {
        mView.hideProgress();
        mView.showMovie(movieCategory.getMovie());
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

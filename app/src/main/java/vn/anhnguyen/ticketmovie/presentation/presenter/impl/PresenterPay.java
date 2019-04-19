package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.domain.interactors.IBookingTicketInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetDetailMovieInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetDetailMovieTimeInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetRoomInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetTicketHolderInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.IUnHoldTicketInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.BookingTicketInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetDetailMovieInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetRoomInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.GetTicketHolderInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.impl.UnHoldTicketInteractor;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.domain.model.response.Room;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketDetail;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketRoom;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterPay;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;
import vn.anhnguyen.ticketmovie.retrofit.APIService;
import vn.anhnguyen.ticketmovie.service.DeviceUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class PresenterPay extends AbstractPresenter implements IPresenterPay,
        IGetRoomInteractor.Callback, IGetDetailMovieInteractor.Callback, IGetDetailMovieTimeInteractor.Callback,
        IUnHoldTicketInteractor.Callback, IGetTicketHolderInteractor.Callback, IBookingTicketInteractor.Callback {
    IPresenterPay.IViewPay mView;

    public PresenterPay(Executor executor, MainThread mainThread, IViewPay mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void getDetailMovieSuccess(MovieCategory movieCategory) {
        mView.hideProgress();
        mView.showMovie(movieCategory.getMovie());
    }

    @Override
    public void getDetailMovieTimeSuccwss(List<TicketDetail> mList) {
        mView.hideProgress();
        //mView.showRoom(room);
    }

    @Override
    public void getRoomSuccess(Room room) {
        mView.hideProgress();
        mView.showRoom(room);
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
    public void unHolder(int id) {
       // mView.showProgress();
        IUnHoldTicketInteractor interactor = new UnHoldTicketInteractor(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),this,
                APIService.getInstance(),DeviceUtils.instance(),SharePrefUtils.instance(),id);
        interactor.execute();
    }

    @Override
    public void getRooom(int idRoom) {
        mView.showProgress();
        IGetRoomInteractor interactor = new GetRoomInteractor(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),this,
                APIService.getInstance(),DeviceUtils.instance(),SharePrefUtils.instance(),idRoom);
        interactor.execute();
    }

    @Override
    public void getMovie(int idMovie) {
        mView.showProgress();
        IGetDetailMovieInteractor interactor = new GetDetailMovieInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),idMovie);
        interactor.execute();
    }

    @Override
    public void getTicketRoom(int idTrans) {
        mView.showProgress();
        IGetTicketHolderInteractor interactor = new GetTicketHolderInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),idTrans);
        interactor.execute();
    }

    @Override
    public void booking(int idTrans) {
        mView.showProgress();
        IBookingTicketInteractor interactor = new BookingTicketInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this,
                APIService.getInstance(), DeviceUtils.instance(), SharePrefUtils.instance(),idTrans);
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
    public void unHolderSucesss() {
        mView.hideProgress();
        mView.unHolderSuccess();
    }

    @Override
    public void unHolderFail(String message) {
        mView.hideProgress();
        mView.unHolderFail();
    }

    @Override
    public void getTicketHolderSuccess(List<TicketRoom> mList) {
        mView.hideProgress();
        mView.showTicketRoom(mList);
    }

    @Override
    public void bookingSuccess(String message) {
        mView.hideProgress();
        mView.showToast(message);
        mView.bookingSuccess();
    }
}

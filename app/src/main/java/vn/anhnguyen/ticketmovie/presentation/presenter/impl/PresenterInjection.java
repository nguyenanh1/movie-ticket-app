package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterChangePassword;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterInformation;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterLogin;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMain;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMovieDetail;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMovieTime;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterPay;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterProfile;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterRegister;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterTicket;
import vn.anhnguyen.ticketmovie.presentation.ui.MainThreadImpl;

public class PresenterInjection {
    private static PresenterInjection sInjection;

    public static PresenterInjection getInjection() {
        if (sInjection == null) {
            sInjection = new PresenterInjection();
        }
        return sInjection;
    }

    public IPresenterRegister newPresenterRegister(IPresenterRegister.IViewRegister view){
        return new PresenterRegister(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterLogin newPresenterLogin(IPresenterLogin.IViewLogin view){
        return new PresenterLogin(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterMain newPresenterMain(IPresenterMain.IViewMain view){
        return new PresenterMain(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterMovieDetail newPresenterMovieDetail(IPresenterMovieDetail.IViewMovieDetail view){
        return new PresenterMovieDetail(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterProfile newPresenterProfile(IPresenterProfile.IViewProfile view){
        return new PresenterProfile(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterInformation newPresenterInformation(IPresenterInformation.IViewInformation view){
        return new PresenterInformation(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterChangePassword newPresnterChangePassword(IPresenterChangePassword.IViewChangePassword view){
        return new PresenterChangePassword(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterMovieTime newPresenterMovieTIme(IPresenterMovieTime.IViewMovieTime view){
        return new PresenterMovieTIme(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterTicket newPresenterTicket(IPresenterTicket.IViewTicket view){
        return new PresenterTicket(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }

    public IPresenterPay newPresenterPay(IPresenterPay.IViewPay view){
        return new PresenterPay(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),view);
    }
}


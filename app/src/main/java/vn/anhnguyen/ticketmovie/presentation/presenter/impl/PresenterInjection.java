package vn.anhnguyen.ticketmovie.presentation.presenter.impl;

import vn.anhnguyen.ticketmovie.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterLogin;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMain;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMovieDetail;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterRegister;
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
}

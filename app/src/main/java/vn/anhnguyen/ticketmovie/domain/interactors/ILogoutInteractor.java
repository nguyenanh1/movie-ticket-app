package vn.anhnguyen.ticketmovie.domain.interactors;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;

public interface ILogoutInteractor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void logoutSuccess();
    }
}

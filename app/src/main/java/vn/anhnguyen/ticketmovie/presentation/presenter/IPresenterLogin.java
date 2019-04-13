package vn.anhnguyen.ticketmovie.presentation.presenter;

import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterLogin extends BasePresenter {
    void login(String email,String password);
    interface IViewLogin extends BaseView{
        void loginSuccess(String message);
    }
}

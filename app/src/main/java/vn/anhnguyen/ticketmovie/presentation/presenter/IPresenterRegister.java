package vn.anhnguyen.ticketmovie.presentation.presenter;

import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterRegister extends BasePresenter {
    void register(String email,String password,String rePassword,String lastname,String name,
                  long birthday, int gender, String phone,String address);

    interface IViewRegister extends BaseView {
        void showRegisterSuccess(String message);
    }
}

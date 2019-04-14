package vn.anhnguyen.ticketmovie.presentation.presenter;

import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterChangePassword extends BasePresenter {
    void changePassword(String oldPass,String newPass,String reNewPass);
    interface IViewChangePassword extends BaseView {
        void showChangePasswordSuccess();
    }
}

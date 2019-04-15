package vn.anhnguyen.ticketmovie.presentation.presenter;

import vn.anhnguyen.ticketmovie.domain.model.response.User;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterProfile extends BasePresenter {
    void getProfile();
    void logout();

    interface IViewProfile extends BaseView{
        void showProfile(User user);
        void showLogoutSuccess();
    }
}

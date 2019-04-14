package vn.anhnguyen.ticketmovie.presentation.presenter;

import vn.anhnguyen.ticketmovie.presentation.presenter.base.AbstractPresenter;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterInformation extends BasePresenter {
    void changProfile(String lastname, String name, Long birthday, Integer gender, String phone, String address);
    interface IViewInformation extends BaseView{
        void showChangProfileSuccess();
    }
}

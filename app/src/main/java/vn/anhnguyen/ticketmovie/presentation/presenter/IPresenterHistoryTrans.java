package vn.anhnguyen.ticketmovie.presentation.presenter;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.model.response.TransMovie;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterHistoryTrans extends BasePresenter {
    void getHistoryTrans();

    interface IViewHistoryTrans extends BaseView {
        void showHistoryTrans(List<TransMovie> list);
    }
}

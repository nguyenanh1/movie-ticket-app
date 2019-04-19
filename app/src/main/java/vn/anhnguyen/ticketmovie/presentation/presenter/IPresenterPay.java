package vn.anhnguyen.ticketmovie.presentation.presenter;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.model.response.Movie;
import vn.anhnguyen.ticketmovie.domain.model.response.Room;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketRoom;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterPay extends BasePresenter {

    void unHolder(int idTrans);

    void getRooom(int idRoom);

    void getMovie(int idMovie);

    void getTicketRoom(int idTrans);

    void booking(int idTrans);

    interface IViewPay extends BaseView{
        void showRoom(Room room);

        void showMovie(Movie movie);

        void showTicketRoom(List<TicketRoom> listTicketRoom);

        void unHolderSuccess();

        void unHolderFail();

        void bookingSuccess();
    }
}

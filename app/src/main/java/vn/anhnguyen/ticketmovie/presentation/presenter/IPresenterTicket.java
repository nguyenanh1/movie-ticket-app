package vn.anhnguyen.ticketmovie.presentation.presenter;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.model.response.Movie;
import vn.anhnguyen.ticketmovie.domain.model.response.Room;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketDetail;
import vn.anhnguyen.ticketmovie.presentation.presenter.base.BasePresenter;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;

public interface IPresenterTicket extends BasePresenter {
    void getTicket(int idMovieTime);

    void getRooom(int idRoom);

    void getMovie(int idMovie);
    interface IViewTicket extends BaseView{
        void showListTicket(List<TicketDetail> mList);

        void showRoom(Room room);

        void showMovie(Movie movie);
    }
}

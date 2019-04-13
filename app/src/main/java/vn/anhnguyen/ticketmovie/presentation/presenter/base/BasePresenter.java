/*
 * Copyright (c) 6/2018. phanpc@gmail.com
 * Last modified 6/25/18 8:24 AM
 */

package vn.anhnguyen.ticketmovie.presentation.presenter.base;

public interface BasePresenter {
    //Functions for call from UI's View
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onStop() method.
     */
    void stop();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    /**
     * Method that should signal the appropriate view to showLoading the appropriate error with the provided message.
     */
    void onError(String message);

    //Functions for call from interactor (use case)
}

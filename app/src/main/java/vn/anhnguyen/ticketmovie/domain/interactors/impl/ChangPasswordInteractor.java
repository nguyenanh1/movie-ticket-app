package vn.anhnguyen.ticketmovie.domain.interactors.impl;

import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.interactors.IChangePasswordInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.base.AbstractInteractor;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangePasswordRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.domain.service.IAPIService;
import vn.anhnguyen.ticketmovie.domain.service.IDeviceUtils;
import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class ChangPasswordInteractor extends AbstractInteractor implements IChangePasswordInteractor {

    private IChangePasswordInteractor.Callback mCallback;
    private IAPIService mAPIService;
    private IDeviceUtils mDeviceUtils;
    private ISharedPrefUtils mSharedPrefUtils;
    private String old_password;
    private String new_password;
    private String re_new_password;

    public ChangPasswordInteractor(Executor threadExecutor, MainThread mainThread, Callback mCallback,
                                   IAPIService mAPIService, IDeviceUtils mDeviceUtils, ISharedPrefUtils mSharedPrefUtils,
                                   String old_password, String new_password, String re_new_password) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mAPIService = mAPIService;
        this.mDeviceUtils = mDeviceUtils;
        this.mSharedPrefUtils = mSharedPrefUtils;
        this.old_password = old_password;
        this.new_password = new_password;
        this.re_new_password = re_new_password;
    }

    @Override
    public void run() {
        if(old_password.isEmpty()||new_password.isEmpty()||re_new_password.isEmpty()){
            notifyError("Bạn phải nhập đầy đủ thông tin");
        }else {
            if(old_password.equals(new_password)){
               notifyError("Mật khẩu mới không được trùng với mật khẩu cũ");
            }else {
                if(!new_password.equals(re_new_password)){
                    notifyError("Mạt khẩu nhập lại không đúng");
                }else {
                    if(mDeviceUtils.hasInternetConnection()){
                        String oldMd5 = CommonVls.md5(old_password);
                        String newMd5 = CommonVls.md5(new_password);
                        BodyChangePasswordRequest request = new BodyChangePasswordRequest(oldMd5,newMd5);
                        String token = mSharedPrefUtils.getLoginStatusToken();
                        BaseResponse response = mAPIService.changePasswrod(token,request);
                        if(response!=null){
                            switch (response.getCode()){
                                case CommonVls.SUCCESS:
                                    notifySuccess("Đổi mật khẩu thành công");
                                    break;
                                case CommonVls.ARGUMENT_NOT_VALID:
                                    notifyError("Bạn chưa nhập đầy đủ thông tin");
                                    break;
                                case CommonVls.PASSWORD_WRONG:
                                    notifyError("Mật khẩu hiện tại không chính xác");
                                case CommonVls.LOGIN_OTHER_DEVICE:
                                    notifyLoginOtherDevice("Tài khoản của bạn đăng nhập ở 1 thiết bị khác");
                                    break;
                                case CommonVls.TOKEN_IN_VALID:
                                    notifyTokenTimeout("Phiên đăng nhập hết bạn");
                                    break;
                                case CommonVls.TOKEN_IS_EMPTY:
                                    notifyTokenTimeout("Phiên đăng nhập hết bạn");
                                    break;
                                case CommonVls.SESSION_TIME_OUT:
                                    notifySessionTimeout(response.getMesg());
                                    break;
                                case CommonVls.TOKEN_TIME_OUT:
                                    notifyTokenTimeout(response.getMesg());
                                    break;
                                default:
                                    notifyError(response.getMesg());
                            }
                        }
                    }else {
                        notifyNoInternet();
                    }
                }
            }
        }
    }

    private void notifySuccess(final String meessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.changePasswordSuccess(meessage);
            }
        });
    }

    private void notifySessionTimeout(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSessionTimeout(message);
            }
        });
    }

    private void notifyTokenTimeout(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onTokenTimeout(message);
            }
        });
    }

    private void notifyLoginOtherDevice(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLoginOtherDevice(message);
            }
        });
    }

    private void notifyError(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFailMessage(message);
            }
        });
    }

    private void notifyNoInternet() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onNoInternetConnection("Không có kết nối mạng, vui lòng kiểm tra lại");
            }
        });
    }
}

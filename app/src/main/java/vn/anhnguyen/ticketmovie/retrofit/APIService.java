package vn.anhnguyen.ticketmovie.retrofit;

import retrofit2.Call;
import retrofit2.Response;
import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangePasswordRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangeProfileRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyLoginRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyRegisterRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.LoginResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ProfileResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetaiLMovieTime;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetailMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetInfoRoom;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovieTime;
import vn.anhnguyen.ticketmovie.domain.service.IAPIService;

public class APIService implements IAPIService {

    private static APIService sInstance;

    public static APIService getInstance() {
        if (sInstance == null) {
            sInstance = new APIService();
        }
        return sInstance;
    }

    @Override
    public BaseResponse register(BodyRegisterRequest request) {
        API api = RetrofitInterface.getAPIService();
        Call<BaseResponse> call = api.register(request);
        try {
            Response<BaseResponse> response = call.execute();
            if(response.code() == CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                BaseResponse baseResponse = new BaseResponse();
                baseResponse.setCode(code);
                baseResponse.setMesg(mesg);
                return baseResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LoginResponse login(BodyLoginRequest request) {
        API api = RetrofitInterface.getAPIService();
        Call<LoginResponse> call = api.login(request);
        try {
            Response<LoginResponse>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                LoginResponse loginResonse = new LoginResponse();
                loginResonse.setCode(code);
                loginResonse.setMesg(mesg);
                return loginResonse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProfileResponse getProfile(String token) {
        API api = RetrofitInterface.getAPIService();
        Call<ProfileResponse> call = api.getProfile(token);
        try {
            Response<ProfileResponse>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                ProfileResponse profileResponse = new ProfileResponse();
                profileResponse.setCode(code);
                profileResponse.setMesg(mesg);
                return profileResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BaseResponse changeProfile(String token, BodyChangeProfileRequest request) {
        API api = RetrofitInterface.getAPIService();
        Call<BaseResponse> call = api.changeProfile(token,request);
        try {
            Response<BaseResponse> response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                BaseResponse baseResponse = new BaseResponse();
                baseResponse.setCode(code);
                baseResponse.setMesg(mesg);
                return baseResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BaseResponse changePasswrod(String token, BodyChangePasswordRequest request) {
        API api = RetrofitInterface.getAPIService();
        Call<BaseResponse> call = api.changePassword(token,request);
        try {
            Response<BaseResponse> response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                BaseResponse baseResponse = new BaseResponse();
                baseResponse.setCode(code);
                baseResponse.setMesg(mesg);
                return baseResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BaseResponse logout(String token) {
        API api = RetrofitInterface.getAPIService();
        Call<BaseResponse> call = api.logout(token);
        try {
            Response<BaseResponse> response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                BaseResponse baseResponse = new BaseResponse();
                baseResponse.setCode(code);
                baseResponse.setMesg(mesg);
                return baseResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseGetMovie getTopMovie() {
        API api = RetrofitInterface.getAPIService();
        Call<ResponseGetMovie> call = api.getTopMovie();
        try {
            Response<ResponseGetMovie>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                ResponseGetMovie responseGetMovie = new ResponseGetMovie();
                responseGetMovie.setCode(code);
                responseGetMovie.setMesg(mesg);
                return responseGetMovie;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseGetMovie getIsShowingMovie(int partdate, int start, int limit) {
        API api = RetrofitInterface.getAPIService();
        Call<ResponseGetMovie> call = api.getIsShowingMovie(partdate,start,limit);
        try {
            Response<ResponseGetMovie>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                ResponseGetMovie responseGetMovie = new ResponseGetMovie();
                responseGetMovie.setCode(code);
                responseGetMovie.setMesg(mesg);
                return responseGetMovie;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseGetMovie getCommingSoonMovie(int partdate, int start, int limit) {
        API api = RetrofitInterface.getAPIService();
        Call<ResponseGetMovie> call = api.getCommingSoonMovie(partdate,start,limit);
        try {
            Response<ResponseGetMovie>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                ResponseGetMovie responseGetMovie = new ResponseGetMovie();
                responseGetMovie.setCode(code);
                responseGetMovie.setMesg(mesg);
                return responseGetMovie;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseGetDetailMovie getMovieDetail(int id) {
        API api = RetrofitInterface.getAPIService();
        Call<ResponseGetDetailMovie> call = api.getMovieDetail(id);
        try {
            Response<ResponseGetDetailMovie>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                ResponseGetDetailMovie responseGetDetailMovie = new ResponseGetDetailMovie();
                responseGetDetailMovie.setCode(code);
                responseGetDetailMovie.setMesg(mesg);
                return responseGetDetailMovie;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseGetMovieTime getMovieTime(String token,int idMovie, int date) {
        API api = RetrofitInterface.getAPIService();
        Call<ResponseGetMovieTime> call = api.getMovieTime(token,idMovie,date);
        try {
            Response<ResponseGetMovieTime>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                ResponseGetMovieTime responseGetMovieTime = new ResponseGetMovieTime();
                responseGetMovieTime.setCode(code);
                responseGetMovieTime.setMesg(mesg);
                return responseGetMovieTime;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseGetDetaiLMovieTime getDetailMovieTime(String token, int idMovieTime) {
        API api = RetrofitInterface.getAPIService();
        Call<ResponseGetDetaiLMovieTime> call = api.getTicket(token,idMovieTime);
        try {
            Response<ResponseGetDetaiLMovieTime>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                ResponseGetDetaiLMovieTime responseGetDetaiLMovieTime = new ResponseGetDetaiLMovieTime();
                responseGetDetaiLMovieTime.setCode(code);
                responseGetDetaiLMovieTime.setMesg(mesg);
                return responseGetDetaiLMovieTime;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseGetInfoRoom getRoom(String token, int idRoom) {
        API api = RetrofitInterface.getAPIService();
        Call<ResponseGetInfoRoom> call = api.getRoom(token,idRoom);
        try {
            Response<ResponseGetInfoRoom>  response = call.execute();
            if(response.code()==CommonVls.CODE_SUCCESS){
                return response.body();
            }else {
                String code = String.valueOf(response.code());
                String mesg = response.message();
                ResponseGetInfoRoom responseGetInfoRoom = new ResponseGetInfoRoom();
                responseGetInfoRoom.setCode(code);
                responseGetInfoRoom.setMesg(mesg);
                return responseGetInfoRoom;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

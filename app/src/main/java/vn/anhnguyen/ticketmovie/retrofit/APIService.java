package vn.anhnguyen.ticketmovie.retrofit;

import retrofit2.Call;
import retrofit2.Response;
import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyLoginRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyRegisterRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.LoginResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetailMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovie;
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
}

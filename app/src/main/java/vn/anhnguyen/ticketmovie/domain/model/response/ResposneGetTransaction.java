package vn.anhnguyen.ticketmovie.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResposneGetTransaction extends BaseResponse {
    @SerializedName("data")
    @Expose
    private TransMovie data;

    public TransMovie getData() {
        return data;
    }

    public void setData(TransMovie data) {
        this.data = data;
    }
}

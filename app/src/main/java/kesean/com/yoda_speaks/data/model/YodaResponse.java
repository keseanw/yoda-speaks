package kesean.com.yoda_speaks.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kesean on 3/28/18.
 */

public class YodaResponse {

    @SerializedName("success")
    private Success success;
    @SerializedName("contents")
    private Contents contents;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

}

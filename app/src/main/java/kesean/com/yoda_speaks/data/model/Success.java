package kesean.com.yoda_speaks.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kesean on 3/31/18.
 */

public class Success {

    @SerializedName("total")
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

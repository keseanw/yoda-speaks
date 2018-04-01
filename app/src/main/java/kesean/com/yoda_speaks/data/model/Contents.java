package kesean.com.yoda_speaks.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kesean on 3/31/18.
 */

public class Contents {

    @SerializedName("translated")
    private String translated;
    @SerializedName("text")
    private String text;
    @SerializedName("translation")
    private String translationType;

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslationType() {
        return translationType;
    }

    public void setTranslationType(String translationType) {
        this.translationType = translationType;
    }
}

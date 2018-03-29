package kesean.com.yoda_speaks.data.model;

/**
 * Created by Kesean on 3/28/18.
 */

public class YodaResponse {

    private String translated;

    private String text;

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

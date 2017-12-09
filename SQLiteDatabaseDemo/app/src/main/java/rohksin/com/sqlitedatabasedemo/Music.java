package rohksin.com.sqlitedatabasedemo;

/**
 * Created by Illuminati on 12/9/2017.
 */

public class Music {

    private String displayName;
    private String createdDate;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString()
    {
        return displayName+"    "+createdDate;
    }


}

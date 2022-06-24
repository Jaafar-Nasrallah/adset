import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDate;

public class AdClick {
    private String userId;
    private String adId;
    private String country;
    private LocalDate date;

    public AdClick(String userId, String adId, String country) {
        this.userId = userId;
        this.adId = adId;
        this.country = country;
        this.date = LocalDate.now();
    }

    public String getUserId() {
        return userId;
    }

    public String getAdId() {
        return adId;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getDate() {
        return date;
    }
}

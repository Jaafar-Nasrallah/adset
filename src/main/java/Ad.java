public class Ad {
    private String id;
    private String title;
    private String imageLink;
    private String url;
    private Adset adset;
    private AdClick[] adClicks;

    public AdClick[] GetAdClicks(){
        return this.adClicks;
    }

    public void setAdClicks(AdClick[] adClicks) {
        this.adClicks = adClicks;
    }

    public Ad(String id, String title, String imageLink, String url, Adset adset, AdClick[] adClicks) {
        this.id = id;
        this.title = title;
        this.imageLink = imageLink;
        this.url = url;
        this.adset = adset;
        this.adClicks = adClicks;
    }
}

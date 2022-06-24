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

}

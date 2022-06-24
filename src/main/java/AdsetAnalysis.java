public class AdsetAnalysis {
    private String country;
    private int clicks;
    private float price;

    public AdsetAnalysis(String country, int clicks, float price) {
        this.country = country;
        this.clicks = clicks;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{Country:"+this.country+",Clicks:"+this.clicks+",Price:"+this.price+"$}";
    }
}

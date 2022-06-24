import java.util.ArrayList;
import java.util.HashMap;

public class Adset implements IAdsetManager {


    private String Id;
    private String Title;
    private float Budget;
    private Platform platform;
    private float RemainingBudget;
    private float CPC;
    private Ad[] Ads;
    private Status status;

    @Override
    public boolean CheckIfAdsetIsActive() {
        //function returns true if Adset status is set to Active, else it returns false
        return this.status == Status.Active;
    }

    @Override
    public void UpdateAdsetBudget(AdClick[] adClicks) throws NoRemainingBudgetException {
        if (!CheckIfAdsetIsActive()) {
            //if Adset is completed throw NoRemainingBudgetException
            throw new NoRemainingBudgetException("Adset status is completed");
        } else {
            //If Adset is active, deduct current AdClicks cost from the remaining budget
            //and set the status to completed if the remaining budget is 0 or less.
            //Which indicates the budget has been exhausted
            float currentAdClickCost = adClicks.length * this.CPC;
            RemainingBudget -= currentAdClickCost;
            if (RemainingBudget <= 0) {
                this.status = Status.Completed;
            }
        }
    }

    @Override
    public AdsetAnalysis[] RetrieveAdsetPerformancePerCountry() {
        //loop over all the Ads in the Adlist
        //loop over all the AdClicks in each of the above Ads
        //increment each click by adding the value to a hashmap with the country being the key
        //if the key (country name) does not exist, create the key
        HashMap<String, Integer> countryClicks = new HashMap<>();
        for (Ad ad : this.Ads) {
            for (AdClick adclick : ad.GetAdClicks()) {
                String country = adclick.getCountry();
                if (countryClicks.containsKey(country)) {
                    int currentClicks = countryClicks.get(country);
                    countryClicks.replace(country, currentClicks + 1);
                } else {
                    countryClicks.put(country, 1);
                }
            }
        }

        AdsetAnalysis[] adsetAnalyses = new AdsetAnalysis[countryClicks.size()];
        int i = 0;
        //loop over the hashmap keys, and fill in the adsetAnalysis array
        for (HashMap.Entry<String,Integer> entry : countryClicks.entrySet()) {
            String countryName = entry.getKey();
            Integer clicks = entry.getValue();
            float price = clicks*this.CPC;
            adsetAnalyses[i] = new AdsetAnalysis(countryName,clicks,price);
            i++;
        }

        return adsetAnalyses;
    }


}

public interface IAdsetManager {

    boolean CheckIfAdsetIsActive();
    void UpdateAdsetBudget(AdClick[] adClicks) throws NoRemainingBudgetException;
    AdsetAnalysis[] RetrieveAdsetPerformancePerCountry();

}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ValidateAdsetBudget {

    @Test
    void TestCreateDemoAdset(){
        AdClick[] adclicks1 = new AdClick[100];
        for (int i =0;i<100;i++){
            adclicks1[i] = new AdClick("1","2","Lebanon");
        }
        AdClick[] adclicks2 = new AdClick[100];
        for (int i =0;i<100;i++){
            adclicks2[i] = new AdClick("1","2","UAE");
        }
        AdClick[] adclicks3 = new AdClick[100];
        for (int i =0;i<100;i++){
            adclicks3[i] = new AdClick("1","2","UAE");
        }

        Adset demoAdset = new Adset("123","Demo Adset",500,Platform.desktop,500,1,new Ad[0],Status.Active);
        Ad ad1 = new Ad("1","Ad1","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",demoAdset,adclicks1);
        Ad ad2 = new Ad("2","Ad2","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",demoAdset,adclicks2);
        Ad ad3 = new Ad("3","Ad3","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",demoAdset,adclicks3);
        Ad[] adsArray = new Ad[3];
        adsArray[0] = ad1;
        adsArray[1] = ad2;
        adsArray[2] = ad3;
        demoAdset.setAds(adsArray);

        try {
            demoAdset.UpdateAdsetBudget(adclicks1);
            demoAdset.UpdateAdsetBudget(adclicks2);
            demoAdset.UpdateAdsetBudget(adclicks3);
            assertEquals(200,demoAdset.getRemainingBudget());
            assertEquals(Status.Active,demoAdset.getStatus());
            AdsetAnalysis[] result = demoAdset.RetrieveAdsetPerformancePerCountry();

            if (result[0].getCountry() == "Lebanon"){
                assertEquals(100,result[0].getClicks());
                assertEquals(200,result[1].getClicks());
            }
            else{
                assertEquals(100,result[1].getClicks());
                assertEquals(200,result[0].getClicks());
            }

        } catch (NoRemainingBudgetException e) {
            System.out.println(e.getMessage());
        }
        AdClick[] adclicks4 = new AdClick[100];
        for (int i =0;i<100;i++){
            adclicks4[i] = new AdClick("1","2","Lebanon");
        }
        AdClick[] adclicks5 = new AdClick[100];
        for (int i =0;i<100;i++){
            adclicks5[i] = new AdClick("1","2","UAE");
        }
        Ad ad4 = new Ad("4","Ad4","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",demoAdset,adclicks4);
        Ad ad5 = new Ad("5","Ad5","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png","https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",demoAdset,adclicks5);
        Ad[] adsArray2 = new Ad[5];
        adsArray2[0] = ad1;
        adsArray2[1] = ad2;
        adsArray2[2] = ad3;
        adsArray2[3] = ad4;
        adsArray2[4] = ad5;
        demoAdset.setAds(adsArray2);
        try {
            demoAdset.UpdateAdsetBudget(adclicks4);
            demoAdset.UpdateAdsetBudget(adclicks5);
            assertEquals(0,demoAdset.getRemainingBudget());
            assertEquals(Status.Completed,demoAdset.getStatus());
            AdsetAnalysis[] result = demoAdset.RetrieveAdsetPerformancePerCountry();
            if (result[0].getCountry() == "Lebanon"){
                assertEquals(200,result[0].getClicks());
                assertEquals(300,result[1].getClicks());
            }
            else{
                assertEquals(200,result[1].getClicks());
                assertEquals(300,result[0].getClicks());
            }
            assertThrows(NoRemainingBudgetException.class,() ->{demoAdset.UpdateAdsetBudget(adclicks1);});

        } catch (NoRemainingBudgetException e) {
            System.out.println(e.getMessage());
        }

    }

}
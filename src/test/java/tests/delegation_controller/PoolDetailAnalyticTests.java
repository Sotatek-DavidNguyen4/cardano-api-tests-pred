package tests.delegation_controller;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import microservices.common.models.ErrorResponse;
import microservices.common.steps.BaseSteps;
import microservices.delegation.steps.DelegationPoolDetailAnalyticSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.net.HttpURLConnection;
import util.CreateParameters;

import java.util.Map;


@Epic("cardano")
@Feature("api-delegation")
public class PoolDetailAnalyticTests extends BaseTest {
    DelegationPoolDetailAnalyticSteps delegationPoolDetailAnalyticSteps = new DelegationPoolDetailAnalyticSteps();
    Object poolView = "pool1rtseflldkqytw7lm3mpek9g5m04ennkv6ejysxqjj52zyaq46rf";
    @Test(description = "verify that get data successfully from params 'poolViews' ", groups={"delegation", "delegation-detail-analytics"})
    public void verifyGetDataFromPoolDetailAnalyticsSuccessfully(){
        Map<String, Object> param = new CreateParameters().withPoolView(poolView).getParamsMap();

        //successfully
        delegationPoolDetailAnalyticSteps
                .getDataForPoolDetailAnalytics(param)
                .validateStatusCode(HttpURLConnection.HTTP_OK);
    }
    @Test(description = "verify that get data uSsuccessfully from params 'poolViews' ", groups={"delegation", "delegation-detail-analytics"}, dataProvider = "paramInvalidPoolView")
    public void verifyGetDataFromPoolDetailAnalyticsUnsuccessfully(Object poolView){
        Map<String, Object> param = new CreateParameters().withAddress(poolView).getParamsMap();

        ErrorResponse errorResponse = (ErrorResponse) delegationPoolDetailAnalyticSteps
                .getDataForPoolDetailAnalytics(param)
                .saveResponseObject(ErrorResponse.class);
        delegationPoolDetailAnalyticSteps.then_verifyErrorResponse(HttpURLConnection.HTTP_BAD_REQUEST, errorResponse.getErrorMessage(), errorResponse.getErrorCode());
    }
    @DataProvider(name ="paramInvalidPoolView")
    public Object[][] dataSetInvalidSize(){
        return new Object[][]{
                {"pool1547tew8vmuj0g6vj3k5jfddudextcw6hsk2hwgg6pkhk7lwph"},
                {null},
                {" "},
                {"&^^^*"},
                {1234},
                {"testpooolrewwiii7654ggfggf"}
        };
    }
}

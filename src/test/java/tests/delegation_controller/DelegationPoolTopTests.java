package tests.delegation_controller;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import microservices.delegation.models.HeaderModel;
import microservices.delegation.steps.DelegationTopSteps;
import org.testng.annotations.Test;
import util.CreateParameters;

import java.net.HttpURLConnection;
import java.util.Map;
@Epic("cardano")
@Feature("api-delegation")
public class DelegationPoolTopTests extends BaseTest {
    DelegationTopSteps delegationSteps = new DelegationTopSteps();
    @Test(description = "verify that get data from header successfully", groups={"delegation", "delegation-top"})
    public void verifyGetDataFromPoolDetailAnalyticsSuccessfully(){
        // page = 0 size = 10 search = ""
        int page = 0;
        Map<String, Object> param = new CreateParameters()
                .withPage(page)
                .withSearch("")
                .getParamsMap();
        //successfully
        delegationSteps
                .getDataForDelegationHeader(param)
                .validateStatusCode(HttpURLConnection.HTTP_OK);
    }
}

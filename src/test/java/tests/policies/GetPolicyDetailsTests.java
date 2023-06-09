package tests.policies;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import microservices.common.constants.APIErrorCode;
import microservices.common.constants.APIErrorMessage;
import microservices.policy.models.detail.PolicyDetail;
import microservices.policy.steps.PolicySteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

@Epic("cardano")
@Feature("api-policy_controller")
@Story("Get a policy detail")
public class GetPolicyDetailsTests extends BaseTest {
    private PolicySteps policySteps = new PolicySteps();
    private PolicyDetail policyDetail = new PolicyDetail();

    @Test(description = "verify get policy detail successfully", groups={"policy"},dataProvider = "getTokenByPoliciesSuccess")
    public void getTokenByPoliciesSuccess(Object policyId){

        policyDetail = (PolicyDetail) policySteps.getPolicyDetail(policyId)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(PolicyDetail.class);
        policySteps.verifyResponsePolicyDetail(policyDetail,policyId,3);
    }
    @DataProvider(name="getTokenByPoliciesSuccess")
    public Object[][] getTokenByPoliciesSuccess(){
        return new Object[][]{
                {"fa1ab2cbdca59874005d9186b85245ed2503acaa63ab4121ab7c3879"},
        };
    }

    @Test(description = "verify get policy detail Unsuccessfully", groups={"policy"},dataProvider = "getTokenByPoliciesUnSuccess")
    public void getTokenByPoliciesUnSuccess(Object policyId){
        policySteps.getPolicyDetail(policyId)
                .then_verifyErrorResponse(400, APIErrorMessage.POLICY_NOT_FOUND, APIErrorCode.POLICY_NOT_FOUND);
    }
    @DataProvider(name="getTokenByPoliciesUnSuccess")
    public Object[][] getTokenByPoliciesUnSuccess(){
        return new Object[][]{
                {123},
                {"@#$"},
                {" "},
                {"asset1c6t4elexwkpuzq08ssylhhmc78ahlz0sgw5a7y"},
                {"asset1c0vymmx0nysjaa8q5vah78jmuqyew3kjm48azr"},
        };
    }
}

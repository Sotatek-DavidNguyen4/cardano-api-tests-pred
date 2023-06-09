package tests.tokens;

import base.BaseTest;
import microservices.token.constants.TokenConstants;
import microservices.token.models.TokenModel;
import microservices.token.steps.TokenSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class Token extends BaseTest {
    private TokenSteps tokenSteps = new TokenSteps();
    private String token = "asset1r0eaxq2lg8hx4r6ntexpxt2ezuduqyxlwxhrt2";
    @Test(description = "verify that get a token", groups = {"token", "token_tokenId"})
    public void getATokenSuccess(){
        Map<String, Object> expect = new HashMap<>();
        expect.put("name", "4b555431");
        expect.put("displayName", "KUT1");
        expect.put("policy", "e38748c08c510a4a5d712922a0f91269b8446ac565068f653c517475");
        expect.put("fingerprint", "asset1r0eaxq2lg8hx4r6ntexpxt2ezuduqyxlwxhrt2");
        TokenModel tokenModel = (TokenModel)
        tokenSteps.getAToken(token)
                .validateStatusCode(HttpURLConnection.HTTP_OK)
                .saveResponseObject(TokenModel.class);
        tokenSteps.verifyResponseOfGetToken(tokenModel, expect);
    }
    @Test(description = "verify that get a token with tokenId invalid", groups = {"token", "token_tokenId"}, dataProvider = "tokenIdInvalid")
    public void getATokenFail(Object tokenId){
        tokenSteps.getAToken(tokenId)
                .then_verifyErrorResponse(HttpURLConnection.HTTP_BAD_REQUEST, TokenConstants.ERROR_MESSAGE, TokenConstants.ERROR_CODE);
    }
    @DataProvider(name = "tokenIdInvalid")
    public Object[][] DatasetWithTokenIdInvalid(){
        return new Object[][]{
                {1},
//                {null},
                {"@#$"},
                {"  "},
                {"asset1c6t4elexwkpuzq08ssylhhmc78ahlz0sgw5a7y"},
                {"asset1c0vymmx0nysjaa8q5vah78jmuqyew3kjm48azr"}
        };
    }
}

package data;

import com.google.gson.JsonObject;
import microservices.txn.models.TransactionResponse;
import util.JsonUtils;
import util.ObjectMappingUtils;

public class ApiResponseData {
    public static TransactionResponse FIRST_TRANSACTION;
    public static TransactionResponse RANDOM_TRANSACTION;
    public static TransactionResponse TRANSACTION_HAVE_30000000000_ADA;
    public static TransactionResponse TRANSACTION_HAVE_29999998493561943_ADA;
    public static TransactionResponse TRANSACTION_BYRON_ERA;
    public static TransactionResponse TRANSACTION_SHELLY_ERA;
    public static TransactionResponse TRANSACTION_ALLEGRA_ERA;
    public static TransactionResponse TRANSACTION_MARY_ERA;
    public static TransactionResponse TRANSACTION_ALOZO_ERA;
    public static TransactionResponse TRANSACTION_BABBAGE_ERA;


    public ApiResponseData() {
        JsonObject map = null;
        if (System.getProperty("cardanoAPI.baseEnv").contains("mainnet")){
            map = JsonUtils.readJsonFile("mainnet/mainnet_api_data.json");
        }
        else if(System.getProperty("cardanoAPI.baseEnv").contains("preprod")){
            map = JsonUtils.readJsonFile("preProd/pre_prod_api_data.json");
        }

        /*--------------------*/
        /* TRANSACTION API */
        /*--------------------*/

        FIRST_TRANSACTION = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("first_transaction").toString(), TransactionResponse.class);
        RANDOM_TRANSACTION = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("random_transaction").toString(), TransactionResponse.class);
        TRANSACTION_HAVE_30000000000_ADA = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("transaction_have_30000000000_ada").toString(), TransactionResponse.class);
        TRANSACTION_HAVE_29999998493561943_ADA = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("transaction_have_29999998493561943_ada").toString(), TransactionResponse.class);
        TRANSACTION_BYRON_ERA = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("transaction_byron_era").toString(), TransactionResponse.class);
        TRANSACTION_SHELLY_ERA = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("transaction_shelly_era").toString(), TransactionResponse.class);
        TRANSACTION_ALLEGRA_ERA = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("transaction_allegra_era").toString(), TransactionResponse.class);
        TRANSACTION_MARY_ERA = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("transaction_mary_era").toString(), TransactionResponse.class);
        TRANSACTION_ALOZO_ERA = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("transaction_alozo_era").toString(), TransactionResponse.class);
        TRANSACTION_BABBAGE_ERA = (TransactionResponse) ObjectMappingUtils.parseJsonToModel(map.getAsJsonObject("transaction_babbage_era").toString(), TransactionResponse.class);

    }
}

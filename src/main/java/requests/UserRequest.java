package requests;

import java.util.Map;

import base.BaseRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import enums.RequestMethod;
import utils.Constants;

public class UserRequest extends BaseRequest {

  String url;

  public UserRequest(APIRequestContext requestContext) {
    super(requestContext);
  }

  public APIResponse getUser(Map<String, String> params) {
    url = Constants.API_URL + Constants.PATH_USERS;
    return sendRequest(RequestMethod.GET, url, params, null);
  }

  public APIResponse createUser(Map<String, String> data) {
    url = Constants.API_URL + Constants.PATH_USERS;
    return sendRequest(RequestMethod.POST, url, null, data);
  }
}

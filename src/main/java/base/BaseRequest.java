package base;

import java.util.Map;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import enums.RequestMethod;

public class BaseRequest {

  protected APIRequestContext requestContext;

  public BaseRequest(APIRequestContext requestContext) {
    this.requestContext = requestContext;
  }

  protected APIResponse sendRequest(RequestMethod method, String url, Map<String, String> params, Map<String, String> data) {
    RequestOptions requestOptions = RequestOptions.create().setHeader("Content-Type", "application/json");

    if(params != null && !params.isEmpty()) {
      for (Map.Entry<String, String> entry : params.entrySet()) {
        requestOptions.setQueryParam(entry.getKey(), entry.getValue());
      }
    }
      requestOptions.setData(data);
    switch (method) {
      case GET:
        return requestContext.get(url,requestOptions);
      case POST:
        return requestContext.post(url, requestOptions);
      default:
        throw new IllegalStateException("Unexpected value: " + method);
    }
  }
}

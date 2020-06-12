package com.cvl.regression.postman;

import com.cvl.regression.commons.ResponseCreator;
import com.cvl.regression.model.PostmanReq;
import com.cvl.regression.model.PostmanResponse;

import java.util.Map;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Log4j2
@Controller
public class PostmanService {
  
  @Autowired
  private ResponseCreator responseCreator;

  /**
   * Method which will process request from postman and send response back to controller.
   * 
   * @param- request from postman.
   * @return- PostmanResponse.
  */
  public PostmanResponse processRequest(final PostmanReq request) {
    
    HttpEntity<Object> entityReq = new HttpEntity<>(request.getPayload(), prepareHeaders(request));

    RestTemplate restTemplate = new RestTemplate();

    String url = queryParams(request);
    
    log.info("Request URL: {}", url);
    log.info("Request : {}", entityReq);

    ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.valueOf(
                                 request.getHttpMethod().toUpperCase()),entityReq, Object.class);
    
    return responseCreator.preparePostmanResponse(response.getStatusCode()
                                                               .toString(),response.getBody());
  } // End of processRequest.
  
  /**
   * Method which will add headers to the request.
   * 
   * @param- request.
   * @return- Headers.
  */
  private HttpHeaders prepareHeaders(final PostmanReq request) {
    
    HttpHeaders httpHeaders = new HttpHeaders();
    
    @SuppressWarnings("unchecked")
    Map<String,String> headerFromReq = (Map<String, String>) request.getHeaders();

    //Adding header parameters.
    if (request.getHeaders() != null) {
      for (Map.Entry<String, String> entry : headerFromReq.entrySet()) {
        httpHeaders.set(entry.getKey(), entry.getValue());
      }
    }
  
    return httpHeaders;
  } // End of prepareHeaders.
  
  /**
   * Method which will add query params to the url.
   * 
   * @param- request.
   * @return- url with params.
  */
  private String queryParams(final PostmanReq request) {
    
    @SuppressWarnings("unchecked")
    Map<String,String> params = (Map<String, String>) request.getParams();
    
    UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(request.getUrl());

    //Adding query parameters.
    if (request.getParams() != null) {
      for (Map.Entry<String, String> entry : params.entrySet()) {
        uri.queryParam(entry.getKey(), entry.getValue());
      }
    }
    
    return uri.toUriString();
  } // End of queryParams.
} // End of PostmanService.
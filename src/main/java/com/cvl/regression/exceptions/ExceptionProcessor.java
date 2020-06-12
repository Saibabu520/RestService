package com.cvl.regression.exceptions;

import com.cvl.regression.commons.ConstantsUtil;
import com.cvl.regression.commons.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

/**
 * The class which will provide exception handling logic to create failure message 
 * with provided message.
 * 
 * @author  Saibabu.
 * @version 1.0.
*/
@Controller
public class ExceptionProcessor {
  
  @Autowired
  private Environment props;

  /**
   * The method will create failure response status and message 
   * and return it to controller class.
   * 
   * @param: exception
  */ 
  public Response processException(final Exception e) {
    
    Response response = Response.builder().status(ConstantsUtil.FAILURE).build();

    if (e instanceof DataException) {
      response.setMessage(e.getMessage());
    } else if (e instanceof HttpServerErrorException) {
      response.setCode(String.valueOf(((HttpServerErrorException) e).getRawStatusCode()));
      response.setMessage(props.getProperty(String.valueOf(
                                     ((HttpServerErrorException) e).getRawStatusCode())));
    } else if (e instanceof HttpClientErrorException) {
      response.setCode(String.valueOf(((HttpClientErrorException) e).getRawStatusCode()));
      response.setMessage(props.getProperty(String.valueOf(
                                     ((HttpClientErrorException) e).getRawStatusCode())));
    } else if (e instanceof ResourceAccessException) {
      response.setMessage(ConstantsUtil.CONNECTION);
    } else {
      response.setMessage(ConstantsUtil.INTERNAL_ERROR);
    }
    return response;
  } // End of processException.
} // End of ExceptionProcessor.
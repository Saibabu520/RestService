package com.cvl.regression.postman;

import com.cvl.regression.exceptions.ExceptionProcessor;
import com.cvl.regression.model.PostmanReq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostmanController {
  
  @Autowired
  private PostmanService pmService;
  
  @Autowired
  private ExceptionProcessor exceptionProcessor;
  
  @PostMapping(value = "/postman")
  private Object pmRequest(@RequestBody PostmanReq request) {
    
    try {
      return pmService.processRequest(request);
    } catch (Exception e) {
      e.printStackTrace();
      return exceptionProcessor.processException(e);
    }
  } // End of processRequest.
} // End of PostmanController.
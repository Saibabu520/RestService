package com.cvl.regression.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostmanResponse {

  private String httpStatus;
  
  private Object response;
} // End of PostmanResponse.
package com.cvl.regression.model;

import lombok.Data;

@Data
public class PostmanReq {

  private String url;
  
  private String httpMethod;
  
  private Object headers;
  
  private Object params;
  
  private Object payload;
} // End of PostmanReq.
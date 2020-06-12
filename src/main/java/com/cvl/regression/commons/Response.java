package com.cvl.regression.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Response {

  private String code;
  
  private String status;
  
  private Object message;
  
  private Object data;
} // End of Response.
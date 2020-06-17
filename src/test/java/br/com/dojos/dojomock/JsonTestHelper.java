package br.com.dojos.dojomock;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTestHelper {

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
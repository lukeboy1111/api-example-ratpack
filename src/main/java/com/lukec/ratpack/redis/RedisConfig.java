package com.lukec.ratpack.redis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisConfig {
  private String url;
  private String name;
  private Integer port;
  private String auth;

}

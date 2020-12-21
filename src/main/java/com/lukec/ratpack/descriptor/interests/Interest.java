package com.lukec.ratpack.descriptor.interests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Interest {
    private String name;
    private Integer geekFactor;
    private String description;
}

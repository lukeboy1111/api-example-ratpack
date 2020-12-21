package com.lukec.ratpack.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Age {
    private Integer age;
    private Integer actsAge;
    public Age(int age, int acts) {
	this.age = age;
	this.actsAge = acts;
    }

}

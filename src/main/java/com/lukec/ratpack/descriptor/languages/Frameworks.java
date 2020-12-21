package com.lukec.ratpack.descriptor.languages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Frameworks extends BaseFrameworkAndLanguage {
    private Boolean isCurrent;
    
    public Frameworks(String name, int rating, Boolean isCurrent) {
	this.name = name;
	this.personalEnjoyment = rating;
    }
    
}

package com.lukec.ratpack.descriptor.languages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammingLanguage extends BaseFrameworkAndLanguage {
    private String variant;
    private Boolean coreLanguage;
    private Boolean basicKnowledge;
    
    public ProgrammingLanguage(String name, boolean b, int rating) {
	this.name = name;
	this.variant = "";
	this.coreLanguage = b;
	this.basicKnowledge = !b;
	this.personalEnjoyment = rating;
    }
    
    public ProgrammingLanguage(String name, String variant, boolean b, int rating) {
   	this.name = name;
   	this.variant = variant;
   	this.coreLanguage = b;
   	this.basicKnowledge = !b;
   	this.personalEnjoyment = rating;
       }

}

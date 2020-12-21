package com.lukec.ratpack.descriptor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.lukec.ratpack.descriptor.interests.Interest;
import com.lukec.ratpack.descriptor.languages.Frameworks;
import com.lukec.ratpack.descriptor.languages.ProgrammingLanguage;
import com.lukec.ratpack.descriptor.languages.SpokenLanguage;
import com.lukec.ratpack.descriptor.location.Town;
import com.lukec.ratpack.descriptor.util.PersonUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LukeCampbell {
    private String firstName;
    private String lastName;
    private Age age;
    private Town town;
    private List<Interest> interests;
    private List<SpokenLanguage> spokenLanguages;
    private List<ProgrammingLanguage> programmingLanguages;
    private List<Frameworks> frameworksAndStuff;
    
    @JsonCreator
    public LukeCampbell() {
	firstName = "Luke";
	lastName = "Campbell";
	age = new Age(49, 12);
	town = new Town("Ramsey", "Isle of Man");
	interests = PersonUtils.makeLukesInterests();
	spokenLanguages = PersonUtils.makeLukesLanguages();
	programmingLanguages = PersonUtils.makeLukesProgramming();
	frameworksAndStuff = PersonUtils.makeLukesFrameworks();
    }
    
}

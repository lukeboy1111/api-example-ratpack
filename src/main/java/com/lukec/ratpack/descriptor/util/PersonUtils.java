package com.lukec.ratpack.descriptor.util;

import java.util.ArrayList;
import java.util.List;

import com.lukec.ratpack.descriptor.interests.Interest;
import com.lukec.ratpack.descriptor.languages.Frameworks;
import com.lukec.ratpack.descriptor.languages.ProgrammingLanguage;
import com.lukec.ratpack.descriptor.languages.SpokenLanguage;

public class PersonUtils {
    public static List<Interest> makeLukesInterests() {
	List<Interest> interests = new ArrayList<>();
	interests.add(new Interest("Football", 1, "Scottish and English Football"));
	interests.add(new Interest("Podcasts", 5, "The Hairdryer Treatment"));
	interests.add(new Interest("NFL", 5, "The Chicago Bears"));
	interests.add(new Interest("Fantasy Sports", 10, "I play in 10 leagues, winning two of them last year"));
	return interests;
    }

    public static List<SpokenLanguage> makeLukesLanguages() {
	List<SpokenLanguage> list = new ArrayList<>();
	list.add(new SpokenLanguage("English", true, false, true, true));
	list.add(new SpokenLanguage("Scottish", true, false, true, true));
	list.add(new SpokenLanguage("Italian", false, true, true, false));
	list.add(new SpokenLanguage("Spanish", false, true, true, false));
	list.add(new SpokenLanguage("Mandarin", false, true, false, true));
	return list;
    }

    public static List<ProgrammingLanguage> makeLukesProgramming() {
	List<ProgrammingLanguage> list = new ArrayList<>();
	list.add(new ProgrammingLanguage("Java", true, 10));
	list.add(new ProgrammingLanguage("Perl", true, 10));
	list.add(new ProgrammingLanguage("Perl", "mod_perl", true, 10));
	list.add(new ProgrammingLanguage("PHP", true, 7));
	list.add(new ProgrammingLanguage("GoLang", false, 3));
	list.add(new ProgrammingLanguage("Python", false, 1));
	list.add(new ProgrammingLanguage("HTML", true, 8));
	list.add(new ProgrammingLanguage("CSS", true, 8));
	list.add(new ProgrammingLanguage("JavaScript", true, 10));
	list.add(new ProgrammingLanguage("JavaScript", "TypeScript", true, 9));
	list.add(new ProgrammingLanguage("JavaScript", "ECMA Script 6 / 7", true, 10));
	return list;
    }

    public static List<Frameworks> makeLukesFrameworks() {
	List<Frameworks> list = new ArrayList<>();
	list.add(new Frameworks("FreeBSD", 10, false));
	list.add(new Frameworks("CentOS", 10, true));
	list.add(new Frameworks("Windows 10", 8, true));
	list.add(new Frameworks("OS X", 10, true));
	list.add(new Frameworks("Debian", 9, true));
	list.add(new Frameworks("Ubuntu", 8, true));
	list.add(new Frameworks("Bootstrap", 9, true));
	list.add(new Frameworks("Angular", 10, true));
	list.add(new Frameworks("React / Redux", 10, true));
	list.add(new Frameworks("SpringBoot", 10, true));
	list.add(new Frameworks("Ratpack", 8, true));
	return list;
    }
}

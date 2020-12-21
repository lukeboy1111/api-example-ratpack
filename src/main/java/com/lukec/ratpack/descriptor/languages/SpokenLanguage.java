package com.lukec.ratpack.descriptor.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpokenLanguage extends BaseDescriptor {
    
    private Boolean nativelySpoken;
    private Boolean basicKnowledge;
    private Boolean written;
    private Boolean spoken;
    
    public SpokenLanguage(String name, boolean b, boolean c, boolean d, boolean e) {
	this.name = name;
	this.nativelySpoken = b;
	this.basicKnowledge = c;
	this.written = d;
	this.spoken = e;
    }
}

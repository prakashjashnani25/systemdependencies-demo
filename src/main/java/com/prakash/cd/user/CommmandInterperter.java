package com.prakash.cd.user;

@FunctionalInterface
public interface CommmandInterperter {

	public UserCommand interpretate(String input);
}

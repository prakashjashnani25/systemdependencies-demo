package com.prakash.cd.action;

import com.prakash.cd.user.UserCommand;

@FunctionalInterface
public interface SystemAction {

	public String doExecute(UserCommand commmand);
}

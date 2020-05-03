package com.prakash.cd.input.reader;

import com.prakash.cd.action.SystemAction;
import com.prakash.cd.user.CommmandInterperter;

public interface SystemReader<T> {
	
	public String read(T t,CommmandInterperter ct,SystemAction sa);
}

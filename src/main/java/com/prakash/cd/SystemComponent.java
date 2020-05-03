package com.prakash.cd;
/**
 * Specification Of System Component
 * @author Prakash
 *
 */

import java.util.LinkedHashSet;
import java.util.Set;

import com.prakash.cd.SException.BusinessExceptionCode;

public class SystemComponent {


	public char[] getComponentName() {
		return componentName;
	}

	public void setComponentName(char[] componentName) {
		if(componentName.length>10)
			throw new SException(BusinessExceptionCode.ITEM_SIZE_GT_10);
		this.componentName = componentName;
	}

	public Set<SystemComponent> getDependsOn() {
		return dependsOn;
	}

	
	public void setDependsOn(Set<SystemComponent> dependsOn) {
		this.dependsOn = dependsOn;
	}

	public Boolean getIsInstalled() {
		return isInstalled;
	}

	public void setIsInstalled(Boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	private char [] componentName =new char[10];
	
	private Set<SystemComponent> dependsOn=new LinkedHashSet<>();
	
	private Boolean isInstalled = false;
	

	@Override
	public String toString() {
		return ""+new String(componentName);
	}

	@Override
	public int hashCode() {
		String str = new String(this.componentName);
		return str.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemComponent other = (SystemComponent) obj;
		String str1=new String(this.componentName);
		String other1 =new String(other.getComponentName());
//		System.out.println("EQUALS "+str1 +" othe 1 "+other1 + "result - "+str1.equalsIgnoreCase(other1));
		return str1.equalsIgnoreCase(other1);
	}
	
		
}

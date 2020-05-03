package com.prakash.cd.config;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import com.prakash.cd.SystemComponent;

public class SystemComponentConfig {

	private static final String NEW_LINE = "\n";

	private static SystemComponentConfig sc;

	private final String INSTALL = "INSTALLING ";

	static {
		sc = new SystemComponentConfig();
	}

	private SystemComponentConfig() {
		installedSystemComponents = new LinkedHashSet<SystemComponent>();

		availableComponents = new LinkedHashSet<>();

	}

	public static SystemComponentConfig getSC() {
		return sc;
	}

	private Set<SystemComponent> installedSystemComponents = new LinkedHashSet<SystemComponent>();

	private Set<SystemComponent> availableComponents = new LinkedHashSet<>();

	public String addComponent(SystemComponent comp) {

		final SystemComponent comp1 = comp;
		Optional<SystemComponent> compExists = availableComponents.stream().filter(cmp1 -> cmp1.equals(comp1))
				.findAny();
		final StringBuffer result = new StringBuffer(" ");
		if (!compExists.isPresent()) {
			availableComponents.add(comp);
		} else
			comp = compExists.get();

		comp.getDependsOn().forEach(cmp1 -> {
			if (!cmp1.getIsInstalled()) {
				result.append(addComponent(cmp1));
			}
		});
		if (comp.getIsInstalled())
			return "";

		comp.setIsInstalled(true);
		installedSystemComponents.add(comp);
		result.append(INSTALL + comp.toString() + NEW_LINE);
		return result.toString();
	}

	public Set<SystemComponent> getAvailableComponents() {
		return availableComponents;
	}

	public boolean removeComponent(SystemComponent comp) {
		/*if (!installedSystemComponents.contains(comp)) {
			return false;
		}
*/
		long count = installedSystemComponents.stream().filter(cmp1 -> {
			return cmp1.getDependsOn().contains(comp);
		}).count();

		if (count > 0) {
			System.out.println(comp+" is still needed.");
			return false;
		}
		comp.setIsInstalled(false);
		installedSystemComponents.remove(comp);
		System.out.println("REMOVING " + comp);
		removeDependent(comp.getDependsOn());
		return true;

	}

	private void removeDependent(Set<SystemComponent> compList) {
		compList.stream().forEach(comp -> {
			if (installedSystemComponents.contains(comp))
				removeComponent(comp);
		});
	}

	public Set<SystemComponent> getCompList() {
		/*
		 * Set<SystemComponent> systemComponents = new LinkedHashSet<>();
		 * systemComponents.addAll(installedSystemComponents);
		 */
		return installedSystemComponents;
	}

	public SystemComponent getSystemComponent(char[] compName) {
		SystemComponent sys = new SystemComponent();
		sys.setComponentName(compName);
		if (availableComponents.contains(sys)) {
			return availableComponents.stream().filter(comp -> sys.equals(comp)).findFirst().get();
		}
		return sys;
	}

}

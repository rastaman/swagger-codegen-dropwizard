package io.brillo.swagger.codegen.dropwizard;

import java.io.File;

import com.wordnik.swagger.codegen.CodegenConfig;
import com.wordnik.swagger.codegen.CodegenType;
import com.wordnik.swagger.codegen.SupportingFile;

public class DropwizardServerCodegen extends com.wordnik.swagger.codegen.languages.JaxRSServerCodegen
		implements CodegenConfig {

	protected String servicesPackage;
	protected String mainClass;
	protected String applicationName;
	protected String basePackage;
	protected String appShortName;

	public CodegenType getTag() {
		return CodegenType.SERVER;
	}

	public String getName() {
		return "dropwizard8";
	}

	public String getHelp() {
		return "Generates a Java Dropwizard 0.8/JDK8 Server application.";
	}

	public DropwizardServerCodegen() {
		super();
		basePackage = "io.brillo";
		servicesPackage = basePackage + ".core";
		this.applicationName = "Brillo";
		this.appShortName = "brillo";
		mainClass = basePackage + "." + applicationName + "Application";

		init();
	}

	public void init() {
		super.init();
		additionalProperties.put("basePackage", basePackage);
		additionalProperties.put("servicesPackage", servicesPackage);
		additionalProperties.put("mainClass", mainClass);
		additionalProperties.put("applicationName", applicationName);
		additionalProperties.put("appShortName", appShortName);

		// Dropwizard classes
		supportingFiles.add(new SupportingFile("application.mustache",
				(sourceFolder + File.separator + basePackage).replace(".", java.io.File.separator),
				applicationName + "Application.java"));
		supportingFiles.add(new SupportingFile("configuration.mustache",
				(sourceFolder + File.separator + basePackage).replace(".", java.io.File.separator),
				applicationName + "Configuration.java"));
		supportingFiles.add(new SupportingFile("module.mustache",
				(sourceFolder + File.separator + basePackage).replace(".", java.io.File.separator),
				applicationName + "Module.java"));

		// Dropwizard and docker descriptors
		supportingFiles.add(new SupportingFile("dropwizard.mustache", "", appShortName + ".yml"));
		supportingFiles.add(new SupportingFile("docker.mustache", "", "Dockerfile"));
	}

	public String getServicesPackage() {
		return servicesPackage;
	}

	public void setServicesPackage(String servicesPackage) {
		this.servicesPackage = servicesPackage;
		init();
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
		init();
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
		init();
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
		init();
	}

	public String getAppShortName() {
		return appShortName;
	}

	public void setAppShortName(String appShortName) {
		this.appShortName = appShortName;
		init();
	}

}
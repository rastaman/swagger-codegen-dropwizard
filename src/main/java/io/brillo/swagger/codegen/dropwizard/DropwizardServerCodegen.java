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
		applicationName = "BIO";
		mainClass = basePackage + "." + applicationName + "Application";

		init();
	}

	public void init() {
		super.init();
		additionalProperties.put("basePackage", basePackage);
		additionalProperties.put("servicesPackage", servicesPackage);
		additionalProperties.put("mainClass", mainClass);
		additionalProperties.put("applicationName", applicationName);

		supportingFiles.add(new SupportingFile("application.mustache",
				(sourceFolder + File.separator + basePackage).replace(".", java.io.File.separator),
				applicationName + "Application.java"));
		supportingFiles.add(new SupportingFile("configuration.mustache",
				(sourceFolder + File.separator + basePackage).replace(".", java.io.File.separator),
				applicationName + "Configuration.java"));
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

}
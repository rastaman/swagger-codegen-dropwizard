package io.brillo.swagger.codegen.dropwizard;

import com.wordnik.swagger.codegen.CodegenConfig;
import com.wordnik.swagger.codegen.CodegenType;

public class DropwizardServerCodegen extends com.wordnik.swagger.codegen.languages.JaxRSServerCodegen
		implements CodegenConfig {

	protected String servicesPackage;
	protected String mainClass;

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
		servicesPackage = "io.brillo.srv.core";
		mainClass = "io.brillo.srv.BIOApplication";
		init();
	}

	public void init() {
		super.init();
		additionalProperties.put("servicesPackage", servicesPackage);
		additionalProperties.put("mainClass", mainClass);
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

}
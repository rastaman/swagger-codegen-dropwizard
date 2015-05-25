package io.brillo.swagger.codegen.dropwizard;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.wordnik.swagger.codegen.CodegenConfig;
import com.wordnik.swagger.codegen.CodegenOperation;
import com.wordnik.swagger.codegen.CodegenType;
import com.wordnik.swagger.codegen.SupportingFile;
import com.wordnik.swagger.models.Operation;
import com.wordnik.swagger.models.properties.ArrayProperty;
import com.wordnik.swagger.models.properties.MapProperty;
import com.wordnik.swagger.models.properties.Property;

public class DropwizardServerCodegen extends com.wordnik.swagger.codegen.languages.JaxRSServerCodegen implements CodegenConfig {

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
    outputFolder = "generated-code/dropwizard";
    modelTemplateFiles.put("model.mustache", ".java");
    apiTemplateFiles.put("api.mustache", ".java");
    templateDir = "JavaJaxRS";
    apiPackage = "io.swagger.api";
    modelPackage = "io.swagger.model";

    init();

    languageSpecificPrimitives = new HashSet<String>(
      Arrays.asList(
        "String",
        "boolean",
        "Boolean",
        "Double",
        "Integer",
        "Long",
        "Float")
      );
  }

  @Override
  public String getTypeDeclaration(Property p) {
    if(p instanceof ArrayProperty) {
      ArrayProperty ap = (ArrayProperty) p;
      Property inner = ap.getItems();
      return getSwaggerType(p) + "<" + getTypeDeclaration(inner) + ">";
    }
    else if (p instanceof MapProperty) {
      MapProperty mp = (MapProperty) p;
      Property inner = mp.getAdditionalProperties();

      return getTypeDeclaration(inner);
    }
    return super.getTypeDeclaration(p);
  }

  @Override
  public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co, Map<String, List<CodegenOperation>> operations) {
    String basePath = resourcePath;
    if(basePath.startsWith("/"))
      basePath = basePath.substring(1);
    int pos = basePath.indexOf("/");
    if(pos > 0)
      basePath = basePath.substring(0, pos);

    if(basePath == "")
      basePath = "default";
    else {
      if(co.path.startsWith("/" + basePath))
        co.path = co.path.substring(("/" + basePath).length());
        co.subresourceOperation = !co.path.isEmpty();
    }
    List<CodegenOperation> opList = operations.get(basePath);
    if(opList == null) {
      opList = new ArrayList<CodegenOperation>();
      operations.put(basePath, opList);
    }
    opList.add(co);
    co.baseName = basePath;
  }

  public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
    Map<String, Object> operations = (Map<String, Object>)objs.get("operations");
    if(operations != null) {
      List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
      for(CodegenOperation operation : ops) {
        if(operation.returnType == null)
          operation.returnType = "Void";
        else if(operation.returnType.startsWith("List")) {
          String rt = operation.returnType;
          int end = rt.lastIndexOf(">");
          if(end > 0) {
            operation.returnType = rt.substring("List<".length(), end);
            operation.returnContainer = "List";
          }
        }
        else if(operation.returnType.startsWith("Map")) {
          String rt = operation.returnType;
          int end = rt.lastIndexOf(">");
          if(end > 0) {
            operation.returnType = rt.substring("Map<".length(), end);
            operation.returnContainer = "Map";
          }
        }
        else if(operation.returnType.startsWith("Set")) {
          String rt = operation.returnType;
          int end = rt.lastIndexOf(">");
          if(end > 0) {
            operation.returnType = rt.substring("Set<".length(), end);
            operation.returnContainer = "Set";
          }
        }
      }
    }
    return objs;
  }

  public void init() {
    //super.init();
    additionalProperties.put("invokerPackage", invokerPackage);
    additionalProperties.put("groupId", groupId);
    additionalProperties.put("artifactId", artifactId);
    additionalProperties.put("artifactVersion", artifactVersion);
    additionalProperties.put("title", title);

    supportingFiles.clear();
    supportingFiles.add(new SupportingFile("pom.mustache", "", "pom.xml"));
    supportingFiles.add(new SupportingFile("README.mustache", "", "README.md"));
    supportingFiles.add(new SupportingFile("ApiException.mustache", 
      (sourceFolder + File.separator + apiPackage).replace(".", java.io.File.separator), "ApiException.java"));
    supportingFiles.add(new SupportingFile("ApiOriginFilter.mustache", 
      (sourceFolder + File.separator + apiPackage).replace(".", java.io.File.separator), "ApiOriginFilter.java"));
    supportingFiles.add(new SupportingFile("ApiResponseMessage.mustache", 
      (sourceFolder + File.separator + apiPackage).replace(".", java.io.File.separator), "ApiResponseMessage.java"));
    supportingFiles.add(new SupportingFile("NotFoundException.mustache", 
      (sourceFolder + File.separator + apiPackage).replace(".", java.io.File.separator), "NotFoundException.java"));
    supportingFiles.add(new SupportingFile("web.mustache", 
      ("src/main/webapp/WEB-INF"), "web.xml"));
  }

  public String getInvokerPackage() {
    return invokerPackage;
  }

  public void setInvokerPackage(String invokerPackage) {
    this.invokerPackage = invokerPackage;
    init();
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
    init();
  }

  public String getArtifactId() {
    return artifactId;
  }

  public void setArtifactId(String artifactId) {
    this.artifactId = artifactId;
    init();
  }

  public String getArtifactVersion() {
    return artifactVersion;
  }

  public void setArtifactVersion(String artifactVersion) {
    this.artifactVersion = artifactVersion;
    init();
  }

  public String getSourceFolder() {
    return sourceFolder;
  }

  public void setSourceFolder(String sourceFolder) {
    this.sourceFolder = sourceFolder;
    init();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
    init();
  }
  

}
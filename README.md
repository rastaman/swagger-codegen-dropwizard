# A sample Swagger code generator for Dropwizard 0.8 / JDK 8.

It is a sample (simple) Swagger code generator for Dropwizard 0.8, because i need it and the official Dropwizard codegenerators from Swagger.io
didn't have landed in the swagger-codegen github repository today.

Note: In order to use this tutorial with Maven you need to use my forks of [swagger-codegen](https://github.com/rastaman/swagger-codegen/) and [swagger-codegen-maven-plugin](https://github.com/rastaman/swagger-codegen-maven-plugin/). I work to put these patches upstream, but it can takes some time before it is done.

```
    <build>
        <plugins>
            <!-- Swagger -->
            <plugin>
                <groupId>org.garethevans.maven.plugins</groupId>
                <artifactId>swagger-codegen-maven-plugin</artifactId>
                <version>0.0.1-SNAPSHOT</version>
                <configuration>
                    <inputSpec>src/main/resources/api.yaml</inputSpec>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>io.brillo.swagger.codegen</groupId>
                        <artifactId>swagger-codegen-dropwizard</artifactId>
                        <version>1.0-SNAPSHOT</version>
                    </dependency>
                </dependencies>
                <executions>
                    <execution>
                        <id>generate-server</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <language>io.brillo.swagger.codegen.dropwizard.DropwizardServerCodegen</language>
                            <templateDirectory>src/main/templates/Dropwizard_0_8_1</templateDirectory>
                            <parameters>
                                <invokerPackage>io.expansible.resources</invokerPackage>
                                <apiPackage>io.expansible.api</apiPackage>
                                <modelPackage>io.expansible.model</modelPackage>
                            </parameters>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

## What it does ?

This generator only add the @Timed annotation to every operation. It should be extended in the near future.

# DriftFX Samples Repository

This repo contains samples for DriftFX. 


## Usage

To simple start the samples use gradle:

### Java 8

TBD

### Java 11

#### OS-X/Linux

```
export JAVA_HOME=/path/to/your/jdk-11
export JAVA_HOME_11=$JAVA_HOME
gradle run11
```

#### Windows

TBD

## Java 11 Notes

eclipse VM arguments

```
--module-path ${project_classpath:org.eclipse.fx.drift.samples} 
--add-modules javafx.controls

--add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED 
--add-exports javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED 
--add-exports javafx.graphics/com.sun.javafx.util=ALL-UNNAMED 
--add-exports javafx.graphics/com.sun.javafx.tk=ALL-UNNAMED 
--add-exports javafx.graphics/com.sun.javafx.geom=ALL-UNNAMED 
--add-exports javafx.graphics/com.sun.javafx.geom.transform=ALL-UNNAMED 
--add-exports javafx.graphics/com.sun.javafx.scene.text=ALL-UNNAMED 
--add-exports javafx.graphics/com.sun.javafx.font=ALL-UNNAMED 
--add-exports javafx.graphics/com.sun.prism.paint=ALL-UNNAMED 
--add-opens javafx.graphics/com.sun.prism=ALL-UNNAMED
--add-opens javafx.graphics/com.sun.prism.es2=ALL-UNNAMED 
--add-opens javafx.graphics/com.sun.prism.d3d=ALL-UNNAMED 
--add-opens javafx.graphics/com.sun.prism.impl=ALL-UNNAMED
```



Resourcerer
===========

A small annotation processor for Eclipse that checks @Resource annotations from the [Swing Application Framework](https://appframework.dev.java.net/) against the properties file that defines the values for the injected resources.

The values of injected resources are displayed with a little info marker on the left side of the Eclipse editor.

If injected resources are not defined in the properties file, the processor marks the resource with a compiler warning. This makes it easy to spot typos in the properties file for example.

Usage
=====

* Open project properties in Eclipse.
* Go to section Java Compiler -> Annotation Processing -> Factory Path.
* Enable project specific settings and add resourcerer.jar.

* Go to Java Compiler -> Annotation Processing.
* Enable project specific settings and add a new options key value pair

  resourceFile = /absolute/path/to/JDesktop/resource/properties/file

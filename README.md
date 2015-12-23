osgi-jee
========

This project provides a various examples of JEE framework usages in an OSGi context. It focuses on some JEE specifications and provides test projects on these specifications to test the integration of standard frameworks in an OSGi context.

Currently, the project only focus on JPA.

The goal is to provide a Eclipse target plateform and a JUnit test plugin to validate the ability to integration standard JEE frameworks (hibernate, openjpa, ...) in a Eclipse/Equinox context/

First, download an Eclipse package and install [the target platform dsl](https://https://github.com/mbarbero/fr.obeo.releng.targetplatform) inside.

For each specification, checkout the bundles included in the directory named by the specification. Then, install the target defined in the xxx.environment bundle. That's it! just run the test plugin to perform test on the given specification.


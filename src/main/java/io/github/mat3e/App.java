package io.github.mat3e;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.*;


public class App {
    public static void main(String[] args) throws Exception {
        var webapp = new WebAppContext();
        webapp.setResourceBase("src/main/webapp");
        webapp.setContextPath("/");
        webapp.setConfigurations(new Configuration[]
                {
                        new AnnotationConfiguration(),
                        new WebInfConfiguration(),
                        new WebXmlConfiguration(),
                        new MetaInfConfiguration(),
                        new FragmentConfiguration(),
                        new EnvConfiguration(),
                        new PlusConfiguration(),
                        new JettyWebXmlConfiguration()
                });
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
        //daje do zrozumienia jetty że skanowane przez niego klasy są na tym samym poziomie, gdzie wynikowy .jar

        var server = new Server(8083);
        server.setHandler(webapp);

        server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {
            @Override
            public void lifeCycleStopped(LifeCycle event) {
                HibernateUtil.close();
            }
        });
        server.start();
        server.join();
        //Jetty embedded – używanie jetty jak zwykłych klas javowych
        // (https://www.eclipse.org/jetty/documentation/9.4.19.v20190610/maven-and-jetty.html).

//        var logger = LoggerFactory.getLogger(App.class);
//        logger.info("Hello World");
        // uruchominie servera lokalnego jetty z konsoli, przez pluginy mavena. (plik pom.xlm) `jetty-maven-plugin`
    }
}

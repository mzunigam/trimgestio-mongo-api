package pe.edu.sacooliveros.trismegisto.mongo.api.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@javax.ws.rs.ApplicationPath("/api/v1")
public class ApplicationConfig extends Application {

    private final Set<Object> singletons = new HashSet<>();
    private final HashSet<Class<?>> classes = new HashSet<>();

    public ApplicationConfig() {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
        singletons.add(corsFilter);

        classes.add(AcademiaApi.class);
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public HashSet<Class<?>> getClasses() {
        return classes;
    }

}

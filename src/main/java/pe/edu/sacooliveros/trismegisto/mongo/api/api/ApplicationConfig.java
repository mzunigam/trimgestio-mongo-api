package pe.edu.sacooliveros.trismegisto.mongo.api.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

import pe.edu.sacooliveros.trismegisto.mongo.api.filters.CorsFilter;
import pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao.MongoDBConexion;


@javax.ws.rs.ApplicationPath("/api/v1")
public class ApplicationConfig extends Application {

    private final Set<Object> singletons = new HashSet<>();
    private final HashSet<Class<?>> classes = new HashSet<>();

    public ApplicationConfig() {

        singletons.add(new CorsFilter());

        classes.add(AcademiaApi.class);
        classes.add(GoogleApi.class);
        classes.add(MongoDBConexion.class);
        classes.add(MatriculaApi.class);
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

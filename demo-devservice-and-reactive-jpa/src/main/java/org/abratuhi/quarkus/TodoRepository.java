package main.java.org.abratuhi.quarkus;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

@ApplicationScoped
public class TodoRepository implements PanacheRepository<Todo> {
}
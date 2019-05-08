package pl.coderslab;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.coderslab.user.User;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthHandler {

    private Long id;
    private boolean isLogged;

    public AuthHandler() {
    }

    public AuthHandler(Long id, boolean isLogged) {
        this.id = id;
        this.isLogged = isLogged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return new User(id);
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
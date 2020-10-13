package mvc_hiber.model;

import java.util.Set;

public interface UserDetails {
    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    String getRole();

    void setRole(String role);

    String getConfirmPassword();

    void setConfirmPassword(String confirmPassword);

    Set<Role> getRoles();

    void setRoles(Set<Role> roles);
}

package mvc_hiber.model;

import java.util.Set;

public interface GrantedAutority {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    public void setUsers(Set<User> users);

    String toString();
}

package mvc_hiber.service;



public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

}

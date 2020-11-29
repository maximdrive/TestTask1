package com.service;

import com.beans.User;
import com.service.exception.ServiceException;

import java.util.ArrayList;

public interface UserService {
    int signIn(String login, String password) throws ServiceException;

    void registration(User us) throws ServiceException;

    ArrayList<User> showUsers() throws ServiceException;

    User showUserInfo(int id) throws ServiceException;

    void signOut() throws ServiceException;

    void deleteUser(int id) throws ServiceException;

    void editUser(int id, User us) throws ServiceException;
}

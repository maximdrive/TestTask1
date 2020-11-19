package com.service;

import com.beans.User;
import com.dao.exception.DaoException;
import com.service.exception.ServiceException;

import java.util.ArrayList;

public interface UserService {
    int SignIn(String login, String password) throws ServiceException;
    void Registration(User us)throws ServiceException;
    ArrayList<User> showUsers() throws ServiceException;
    User showUserInfo(int id) throws ServiceException;
    void signOut() throws ServiceException;
    void deleteUser(int id) throws ServiceException;
    void editUser(int id, User us) throws ServiceException;
}

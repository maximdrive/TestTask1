package com.dao;

import com.beans.User;
import com.dao.exception.DaoException;

import java.util.ArrayList;

public interface UserDAO {

    int SignIn(String login, String password) throws DaoException;

    void Registration(User us) throws DaoException;
    ArrayList<User> showUsers();
    User showUserInfo(int id) throws DaoException;
    void deleteUser(int id) throws DaoException;
    void signOut() throws DaoException;
    void editUser(int id, User us) throws DaoException;
}

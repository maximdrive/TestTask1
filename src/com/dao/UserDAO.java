package com.dao;

import com.beans.User;
import com.dao.exception.DaoException;

import java.util.ArrayList;

public interface UserDAO {

    int signIn(String login, String password) throws DaoException;

    void registration(User us) throws DaoException;

    ArrayList<User> showUsers();

    User showUserInfo(int id) throws DaoException;

    void deleteUser(int id) throws DaoException;

    void signOut() throws DaoException;

    void editUser(int id, User us) throws DaoException;
}

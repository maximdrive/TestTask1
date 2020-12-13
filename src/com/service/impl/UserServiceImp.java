package com.service.impl;


import com.beans.User;
import com.dao.UserDAO;
import com.dao.exception.DaoException;
import com.dao.factory.DaoFactory;
import com.service.UserService;
import com.service.exception.ServiceException;

import java.util.ArrayList;

public class UserServiceImp implements UserService {


    @Override
    public int signIn(String login, String password) throws ServiceException {
        if (login == null || login.isEmpty())
            throw new ServiceException("Login is not set");
        int id;
        try {
            DaoFactory instance = DaoFactory.getInstance();
            UserDAO dao = instance.getUserDAO();
            id = dao.signIn(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public void registration(User us) throws ServiceException {
        if (us.getLogin().isEmpty() || us.getPassword().isEmpty() || us.getInfo().getName().isEmpty() ||
                us.getInfo().getSurname().isEmpty() || us.getInfo().getPhone().isEmpty() ||
                us.getInfo().getEmail().isEmpty())
            throw new ServiceException("Fill up all fields");
        for(int i =0; i<us.getInfo().getRole().size(); i++) {
            if (us.getInfo().getRole().get(i) == null)
                throw new ServiceException("Role is incorrect");
        }

        try {
            DaoFactory instance = DaoFactory.getInstance();
            UserDAO dao = instance.getUserDAO();
            dao.registration(us);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public ArrayList<User> showUsers() throws ServiceException {
        ArrayList<User> users;

        DaoFactory instance = DaoFactory.getInstance();
        UserDAO dao = instance.getUserDAO();
        users = dao.showUsers();

        if (users.isEmpty())
            throw new ServiceException("No users in file");
        return users;

    }

    @Override
    public User showUserInfo(int id) throws ServiceException {
        if (id < 0)
            throw new ServiceException("Wrong id");
        DaoFactory instance = DaoFactory.getInstance();
        UserDAO dao = instance.getUserDAO();
        try {
            return dao.showUserInfo(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void signOut() throws ServiceException {
        DaoFactory instance = DaoFactory.getInstance();
        UserDAO dao = instance.getUserDAO();
        try {
            dao.signOut();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        if (id < 0)
            throw new ServiceException("Incorrect id");

        DaoFactory instance = DaoFactory.getInstance();
        UserDAO dao = instance.getUserDAO();
        try {
            dao.deleteUser(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void editUser(int id, User us) throws ServiceException {
        if (id < 0 || us == null)
            throw new ServiceException("Incorrect data");
        try {
            DaoFactory instance = DaoFactory.getInstance();
            UserDAO dao = instance.getUserDAO();
            dao.editUser(id, us);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

    }
}

package com.dao.factory;

import com.dao.UserDAO;
import com.dao.impl.FileUserDao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final UserDAO fileUserImpl = new FileUserDao();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return fileUserImpl;
    }

}

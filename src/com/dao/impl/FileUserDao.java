package com.dao.impl;

import com.beans.User;
import com.dao.UserDAO;
import com.dao.exception.DaoException;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUserDao implements UserDAO {
    ArrayList<User> users;
    User user;

    public FileUserDao(){
        users = readAllFile();
    }
    @Override
    public int SignIn(String login, String password) throws DaoException {
        for(User us : users){
            if(login.equals(us.getLogin()) && password.equals(us.getPassword()))
                return us.getId();
        }
        throw new DaoException("Incorrect login or password");
    }

    @Override
    public void Registration(User us) throws DaoException {
        if(!isLoginUnique(us.getLogin()))
            throw new DaoException("Login is not unique");
        if(!isEmailCorrect(us.getInfo().getEmail()))
            throw new DaoException("Email is not correct");
        for(String phone : us.getInfo().getPhone()){
            if(!isPhoneCorrect(phone))
                throw new DaoException("Phone is not correct");
        }


        us.setId(users.size()+1);
        users.add(us);
        writeFile();

    }

    @Override
    public ArrayList<User> showUsers() {
        return users;
    }

    @Override
    public User showUserInfo(int id) throws DaoException {
        if(id > users.size()) throw new DaoException("Incorrect id");
        return findUserByID(id);
    }

    @Override
    public void deleteUser(int id)throws DaoException {
        for(User us : users){
            if(id == us.getId()) {
                users.remove(us);
                return;
            }
            throw new DaoException("ID hasn't been found");
        }
    }


    @Override
    public void signOut() throws DaoException {
        writeFile();
    }

    @Override
    public void editUser(int id, User us) throws DaoException {
        users.remove(findUserByID(id));
        if(us != null) {
            Registration(us);
            us.setId(id);
        }
        else throw new DaoException("No info has been given");
    }

    public User findUserByID(int id){
        for(User us : users){
            if(id == us.getId())
                return us;
        }
        return null;
    }



    public void writeFile() throws DaoException {
        try {
            FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(users);
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
    public ArrayList<User> readAllFile(){
        try {
            FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            users = (ArrayList<User>) oi.readObject();
            if(users.size() == 0 ){
                createDefaultUser();
            }
            return users;
            //System.out.println(users);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    private boolean isLoginUnique(String login){
        for(User us : users){
            if(login.equals(us.getLogin()))
                return false;
        }
        return true;
    }

    private boolean isEmailCorrect(String mail){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    private boolean isPhoneCorrect(String phone){
        Pattern pattern = Pattern.compile("(\\+*)375\\d{9}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private void createDefaultUser(){
        User us = new User();
        us.setLogin("admin");
        us.setPassword("admin");
        us.setId(1);
        us.setPerson("Admin","Adminov","admin@mail.ru","admin","375291684205");
        users.add(us);
    }
}

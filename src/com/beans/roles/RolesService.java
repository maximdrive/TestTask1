package com.beans.roles;

import com.beans.User;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RolesService {
    EnumMap<Roles,Integer> roleMap;
    public RolesService(){
        roleMap = new EnumMap<>(Roles.class);
        roleMap.put(Roles.USER, 1);
        roleMap.put(Roles.CUSTOMER,1);
        roleMap.put(Roles.ADMIN,2);
        roleMap.put(Roles.PROVIDER,2);
        roleMap.put(Roles.SUPER_ADMIN,3);
    }

    public EnumMap<Roles, Integer> getRoleMap() {
        return roleMap;
    }
    public boolean isSuperAdminExists(List<Roles> roles){
        for (Roles role : roles) {
            if (role.equals(Roles.SUPER_ADMIN)) {
                return true;
            }
        }
        return false;
    }
    public boolean isRoleLevelUnique(Map<Roles,Integer> roleMap, Roles retRole, User us){
        for(int i = 0; i<us.getInfo().getRole().size(); i++){
            if(roleMap.get(retRole).equals(roleMap.get(us.getInfo().getRole().get(i)))){
                return false;
            }
        }
        return true;
    }
}

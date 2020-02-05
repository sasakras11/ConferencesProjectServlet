package com;


import com.context.AppContext;
import com.entity.Role;
import com.entity.User;

public class Starter {

    public static void main(String[] args) {

        for (User user: AppContext.getUserDao().findByRole(Role.SPEAKER)
             ) {
            System.out.println(user.getStatus()+" ");
            System.out.print(user.getUsername());
        }
    }

}




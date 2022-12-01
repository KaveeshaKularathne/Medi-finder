package com.app.Medifinder.Service;

public interface PasswordEncoder {

    String encode(CharSequence var1);

    boolean matches(CharSequence var1,String var2);

    default boolean upgradeEncodeing(String encodedPassword){
        return false;
    }
}

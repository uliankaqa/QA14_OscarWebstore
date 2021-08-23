package com.telran.oscar.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

   /*  @DataProvider
   public Iterator<Object[]> positiveRegistration()  throws IOException {
        return getDataProviderFromFile("/data_providers/user/positiveRegistration.csv");
    }*/

    @DataProvider
    public Iterator<Object[]> registrationAndLoginPositive() throws IOException {
        return getDataProviderFromFile("/data_providers/user/loginPositive.csv");
    }

    @DataProvider
    public Iterator<Object[]> loginNegativeIncorrectEmail() throws IOException {
        return getDataProviderFromFile("/data_providers/user/incorrectEmail.csv");
    }

    @DataProvider
    public Iterator<Object[]> loginNegativeIncorrectPassword() throws IOException {
        return  getDataProviderFromFile("/data_providers/user/incorrectPassword.csv");
    }

    @DataProvider
    public Iterator<Object[]> loginNegativeIncorrectConfirmPassword () throws IOException {
        return getDataProviderFromFile("/data_providers/user/incorrectConfirmPassword.csv");
    }


    private Iterator<Object[]> getDataProviderFromFile(String src) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream(src)));
        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(","));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }
}

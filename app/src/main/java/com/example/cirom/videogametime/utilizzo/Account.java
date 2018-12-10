package com.example.cirom.videogametime.utilizzo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Account {

    public static GoogleApiClient mGoogleApiClient;
    public static String personName ;
    public static String personGivenName ;
    public static String personFamilyName;
    public static String personEmail;
    public static String personId;
    public static Uri personPhoto;
    public static GoogleSignInAccount acct;
    public static ArrayList<String> consoleQuery;
    public static boolean Accesso=false;
    public static SharedPreferences mSettings;

    public static ArrayList<String> getConsoleQuery() {
        return consoleQuery;
    }

    public static void setConsoleQuery(ArrayList<String> consoleQuery) {
        Account.consoleQuery = consoleQuery;
    }

    public static String getPersonName() {
        return personName;
    }

    public static void setPersonName(String personName) {
        Account.personName = personName;
    }

    public static String getPersonGivenName() {
        return personGivenName;
    }

    public static void setPersonGivenName(String personGivenName) {
        Account.personGivenName = personGivenName;
    }

    public static String getPersonFamilyName() {
        return personFamilyName;
    }

    public static void setPersonFamilyName(String personFamilyName) {
        Account.personFamilyName = personFamilyName;
    }

    public static String getPersonEmail() {
        return personEmail;
    }

    public static void setPersonEmail(String personEmail) {
        Account.personEmail = personEmail;
    }

    public static String getPersonId() {
        return personId;
    }

    public static void setPersonId(String personId) {
        Account.personId = personId;
    }

    public static Uri getPersonPhoto() {
        return personPhoto;
    }

    public static void setPersonPhoto(Uri personPhoto) {
        Account.personPhoto = personPhoto;
    }

    public static GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public static void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        Account.mGoogleApiClient = mGoogleApiClient;
    }

    public static GoogleSignInAccount getAcct() {
        return acct;
    }

    public static void setAcct(GoogleSignInAccount acct) {
        Account.acct = acct;
    }

    public static boolean isAccesso() {
        return Accesso;
    }

    public static void setAccesso(boolean accesso) {
        Accesso = accesso;
    }
}

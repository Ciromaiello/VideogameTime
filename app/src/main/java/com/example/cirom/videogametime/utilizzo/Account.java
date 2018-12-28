package com.example.cirom.videogametime.utilizzo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Account {

    public  static ArrayList<Giochi> giochiscelti;
    public static GoogleApiClient mGoogleApiClient;
    private String personName ;
    public static String personGivenName ;
    public static String personFamilyName;
    public static String personEmail;
    private String personId;
    private String personPhoto;
    private ArrayList<String> idGiochiScelti;
    public static GoogleSignInAccount acct;
    public static ArrayList<String> consoleQuery;
    public static ArrayList<String> genQuery;
    public static ArrayList<String> nomige;
    public static ArrayList<String> nomipi;
    public static boolean utente = false;
    public static boolean Accesso = false;
    public static SharedPreferences mSettings;

    public static ArrayList<Giochi> getGiochiscelti() {
        return giochiscelti;
    }

    public static void setGiochiscelti(ArrayList<Giochi> giochiscelti) {
        Account.giochiscelti = giochiscelti;
    }

    public Account(String personName, String personId, String personPhoto) {
        this.personName = personName;
        this.personId = personId;
        this.personPhoto = personPhoto;
    }

    public Account() {
    }

    public static ArrayList<String> getConsoleQuery() {
        return consoleQuery;
    }

    public static void setConsoleQuery(ArrayList<String> consoleQuery) {
        Account.consoleQuery = consoleQuery;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public  String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
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

    public ArrayList<String> getIdGiochiScelti() {
        return idGiochiScelti;
    }

    public void setIdGiochiScelti(ArrayList<String> idGiochiScelti) {
        this.idGiochiScelti = idGiochiScelti;
    }


}

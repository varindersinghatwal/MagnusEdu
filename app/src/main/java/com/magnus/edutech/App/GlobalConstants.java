package com.magnus.edutech.App;

public class GlobalConstants

{
    // Application Folder Name
    public static final String APPLICATION_FOLDER_NAME = "Magnus";
    public static final String APPLICATION_SUBFOLDER_VIDEOS = "Videos";
    public static final String APPLICATION_SUBFOLDER_OTHERS = "Others";
    public static final String FONT_PATH_LIGHT = "fonts/Roboto-Medium.ttf";/*arial.ttf*/

    public static final String LOGGED_IN = "logged_in";

    // REQUEST VALUE :

    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String HAS_BUNDLE = "has_bundle";

    public static final int ACTIVITY_LOGIN = 20;
    public static final int ACTIVITY_REGISTRATION = 30;

    // For fragments
    public static final int FRAGMENT_HOME_COURSE = 42;
    public static final int FRAGMENT_CHAPTERS_LIST = 43;
    public static final int FRAGMENT_VIDEOS_LIST = 44;
    public static final int FRAGMENT_ABOUT_US = 45;

    // Login Service
    public static final String EMAIL = "email";
    public static final String USER_NAME = "username";
    public static final String MOBILE = "mobile";
    public static final String PASSWORD = "password";
    public static final String RE_PASSWORD = "repassword";
    public static final String FULL_NAME = "fullname";
    public static final String FROM = "from";
    public static final String MESSAGE = "message";
    public static final String AMOUNT = "amount";
    public static final boolean DEBUG = false;


    // Registration Service


    // User Cluster Detail
    // Key
    public static final String SUBJECT = "subject";
    public static final String VIDEOS = "videos";
    public static final String CATEGORY = "category";
    //ID
    public static final String SUBJECT_ID = "subject_id";
    public static final String NAME = "name";
    public static final String CATEGORY_ID = "category_id";
    public static final String VIDEOS_ID = "video_id";
    public static final String PRODUCT_ID = "product_id";
    public static final String URL = "url";

    public static final String PURCHASED = "purchased";
    public static final String RESULT = "result";
    public static final String VIDEOS_COUNT = "videosCount";
    public static final String STATUS = "status";
    public static final String CLIENT_ID = "client_id";
    public static final String DONWLOAD = "download";
    public static final String PAID_STATUS = "paid_status";


    //
    public static final int GET_VIDEOS = 10;
    public static final int DOWNLOAD_VIDEOS = 11;
    public static final int LOGIN = 13;
    public static final int STATUS_UPDATE = 15;
    public static final int UPDATE_ON_SERVER = 16;
    public static final int REGISTRATION = 31;
    public static final int LOGIN_AFTER_REGISTRATION = 32;
    public static final int FORGET_PASSWORD = 33;


    // Time Out
    public static final int TIMEOUT_MILLISEC = 16000;
    // API URL http://magnusedutech.org/admin/paidclient.php
    public static final String MAIN_URL = "http://magnusedutech.org/admin/";
    public static final String GET_VIDEO_DATA = MAIN_URL + "videotree.php";
    public static final String UPDATE_STATUS_API = MAIN_URL + "mobile_paid_status.php";
    public static final String LOGIN_API = MAIN_URL + "mobile_login.php";
    public static final String REGISTRATION_API = MAIN_URL + "mobile_register.php";
    public static final String FORGET_PASSWORD_API = MAIN_URL + "mobile_forgot_password.php";
    public static final String UPDATE_ON_SERVER_API = MAIN_URL + "mobile_transaction_entry.php";
    public static final String DOWNLOAD_VIDEO_URL = MAIN_URL + "videos5630encry/";
    public static final String DOWNLOAD_VIDEO_EXTENSION = ".mp4";


}

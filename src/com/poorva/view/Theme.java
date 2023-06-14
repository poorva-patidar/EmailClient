package com.poorva.view;

public enum Theme {
    LIGHT,
    DEFAULT,
    DARK;

    public static String getCssPath(Theme theme){
        switch(theme){
            case LIGHT:
                return "css/themeLight.css";

            case DEFAULT:
                return "css/themeDefault.css";

            case DARK:
                return "css/themeDark.css";


            default:
                return null;
        }
    }
}

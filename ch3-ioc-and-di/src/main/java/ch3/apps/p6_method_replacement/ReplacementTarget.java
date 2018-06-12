package ch3.apps.p6_method_replacement;

/*
 * Bean with method formatMessage() which should be replaced
 */

class ReplacementTarget {

    public String formatMessage(String msg){
        return "<h1>" + msg + "</h1>";
    }

    public String formatMessage(Object msg){
        return "<h1>" + msg + "</h1>";
    }
}

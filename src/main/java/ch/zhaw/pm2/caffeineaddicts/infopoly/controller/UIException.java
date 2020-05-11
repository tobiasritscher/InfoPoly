package ch.zhaw.pm2.caffeineaddicts.infopoly.controller;

public class UIException extends RuntimeException{

    private final String message;

    public UIException(String message) {
        this.message = message;
        new InformationalWindow("",message);
    }

    @Override
    public String toString(){
        return message;
    }
}

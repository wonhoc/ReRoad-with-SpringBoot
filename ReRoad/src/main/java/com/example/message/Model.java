package com.example.message;

public class Model {
    private String message;
    private String fromLogin;
    private int boardNo;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromLogin() {
        return fromLogin;
    }
    public void setFromLogin(String fromLogin) {
        this.fromLogin = fromLogin;
    }

    public int getBoardNo(){
        return boardNo;
    }

    public void setBoardNo(){
        this.boardNo = boardNo;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "message='" + message + '\'' +
                ", fromLogin='" + fromLogin + '\'' +
                ", boardNo='" + boardNo + '\'' +'}';
    }
}

package ru.javaprojects.usersserver.model;

public class VerifyPasswordResponse {
    private boolean result;

    public VerifyPasswordResponse(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

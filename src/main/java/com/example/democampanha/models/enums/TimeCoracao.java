package com.example.democampanha.models.enums;

public enum TimeCoracao {

    PALMEIRAS(1),
    CORINTHIANS(2),
    SAO_PAULO(3),
    SANTOS(4),
    FLAMENGO(5);

    private int code;

    private TimeCoracao(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TimeCoracao valueOf(int code) {
        for (TimeCoracao value : TimeCoracao.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid TimeCoracao code");
    }
}

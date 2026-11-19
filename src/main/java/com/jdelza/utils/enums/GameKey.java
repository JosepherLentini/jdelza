package com.jdelza.utils.enums;

public enum GameKey {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    HEIGHT("8"),
    NINE("9"),
    ZERO("0"),
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h"),
    I("i"),
    J("j"),
    K("k"),
    L("l"),
    M("m"),
    N("n"),
    O("o"),
    P("p"),
    Q("q"),
    R("r"),
    S("s"),
    T("t"),
    U("u"),
    V("v"),
    W("w"),
    X("x"),
    Y("y"),
    Z("z"),
    DOT("."),
    HYPHEN("-");

    private final String value;

    GameKey(String value) {
        this.value = value;
    }


    public static GameKey fromValue(String s) {
        for (GameKey k : values()) {
            if (k.value.equals(s)) {
                return k;
            }
        }
        throw new IllegalArgumentException("Nessun tasto per: " + s);
    }


    public String getValue(){
        return this.value;
    }



}

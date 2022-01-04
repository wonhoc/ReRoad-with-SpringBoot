package com.example.member.attribute;

public enum SocialType {
    GOOGLE("google");

    private final String ROLE_PREFIX = "ROLE_MEMBER";
    private String name;

    SocialType(String name){ this.name = name; }

    public String getRoleType() {return ROLE_PREFIX; }
    public String getValue() { return name;}
    public boolean isEquals(String authority) { return this.getRoleType().equals(authority); }
}

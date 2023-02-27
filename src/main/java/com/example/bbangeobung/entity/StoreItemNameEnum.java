package com.example.bbangeobung.entity;

public enum StoreItemNameEnum {
    팥("팥"),  // 사용자 권한
    슈크림("슈크림");  // 관리자 권한

    private String name;

    StoreItemNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
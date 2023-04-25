package kafka.boardproject.sys.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


public enum UserRoleEnum {
    USER,  // 사용자 권한
    ADMIN  // 관리자 권한
}
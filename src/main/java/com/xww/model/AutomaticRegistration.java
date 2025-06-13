package com.xww.model;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // 运行时保留，供扫描使用
@Documented
public @interface AutomaticRegistration {
}


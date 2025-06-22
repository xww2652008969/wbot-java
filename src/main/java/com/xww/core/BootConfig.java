package com.xww.core;

import lombok.Data;

@Data
public class BootConfig {
    private String wsUrl;
    private String wsToken;
    private String httpUrl;
    private String httpToken;
    private boolean debug;
}

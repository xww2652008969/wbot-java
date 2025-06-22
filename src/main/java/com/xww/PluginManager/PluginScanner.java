package com.xww.PluginManager;

import com.xww.core.BasePlugins;
import com.xww.model.BotPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginScanner {
    /**
     * 扫描整个类路径，查找所有标注 @Plugin 的类
     *
     * @return 插件类集合
     */
    public static Set<BasePlugins> scanAllPlugins() {
        Set<Class<BotPlugin>> pluginClasses = new HashSet<>();
        // 获取类路径下所有资源（包括目录和 JAR）
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(""); // 获取类路径根资源
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String protocol = resource.getProtocol();
                if ("file".equals(protocol)) {
                    pluginClasses.addAll(scanFileSystemClasspath(resource.getFile()));
                } else if ("jar".equals(protocol)) {
                    pluginClasses.addAll(scanJarFile(resource));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        var a = new HashSet<BasePlugins>();
        for (var p : pluginClasses) {
            try {
                BasePlugins instance = (BasePlugins) p.getDeclaredConstructor().newInstance();
                a.add(instance);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    /**
     * 扫描文件系统中的类路径目录（递归所有子目录）
     */
    private static Set<Class<BotPlugin>> scanFileSystemClasspath(String directoryPath) {
        Set<Class<BotPlugin>> classes = new HashSet<>();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            return classes;
        }

        // 递归扫描目录下的所有 .class 文件
        scanDirectoryForClasses(directory, "", classes);
        return classes;
    }

    /**
     * 递归扫描目录，提取 .class 文件并转换为类名
     */
    private static void scanDirectoryForClasses(File directory, String currentPackage, Set<Class<BotPlugin>> classes) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                String subPackage = currentPackage.isEmpty() ? file.getName() : currentPackage + "." + file.getName();
                scanDirectoryForClasses(file, subPackage, classes);
            } else if (file.getName().endsWith(".class")) {
                String className = currentPackage.isEmpty()
                        ? file.getName().substring(0, file.getName().length() - 6)
                        : currentPackage + "." + file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(BotPlugin.class)) {
                        @SuppressWarnings("unchecked")
                        Class<BotPlugin> botPluginClass = (Class<BotPlugin>) clazz;
                        classes.add(botPluginClass);
                    }
                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    System.err.println("警告：无法加载类 " + className + "，原因：" + e.getMessage());
                }
            }
        }
    }

    /**
     * 扫描 JAR 文件中的所有 .class 文件
     */
    private static Set<Class<BotPlugin>> scanJarFile(URL jarUrl) throws IOException {
        Set<Class<BotPlugin>> classes = new HashSet<>();
        URLConnection connection = jarUrl.openConnection();
        if (connection instanceof JarURLConnection) {
            JarFile jarFile = ((JarURLConnection) connection).getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class")) {
                    String className = entryName
                            .substring(0, entryName.length() - 6)
                            .replace('/', '.');
                    try {
                        Class<?> clazz = Class.forName(className);
                        if (clazz.isAnnotationPresent(BotPlugin.class)) {

                            @SuppressWarnings("unchecked")
                            Class<BotPlugin> botPluginClass = (Class<BotPlugin>) clazz;
                            classes.add(botPluginClass);
                        }
                    } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    }
                }
            }
            jarFile.close();
        }
        return classes;
    }
}
package com.pi.cmd.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.pi.main.PI;

public class CommandLoader{

	public static ArrayList<CommandBase> commandsArray = new ArrayList<CommandBase>();
	
	public CommandLoader(PI instance) {
	}
	
	/**
	 * Credits to Sab for helping with loading classes into an array!
	 */
    public List<CommandBase> loadCommands(String packageName) {
        try {
    		String jarPath = "";
			try {
				jarPath = PI.class.getProtectionDomain().getCodeSource().getLocation().toURI().toString().substring(5).replace("%20", " ");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
            @SuppressWarnings("resource")
			JarFile jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> entries = jarFile.entries();
            JarEntry jarEntry;
            while(entries.hasMoreElements()) {
                jarEntry = entries.nextElement();
                if(jarEntry.getName().startsWith(packageName) && jarEntry.getName().endsWith(".class")) {
                    String className = jarEntry.getName().replaceAll("/", "\\.");
                    className = className.substring(0, className.length() - 6);
                    if(className.contains("$")) continue;
                    ClassLoader classLoader = this.getClass().getClassLoader();
                    URL url = new URL("jar:file:" + jarPath + "!/");

                    @SuppressWarnings("resource")
					URLClassLoader uclLoader = new URLClassLoader(new URL[]{url}, classLoader);

                    
                    try {
                        Class<?> commands = uclLoader.loadClass(className);
                        commandsArray.add((CommandBase) commands.newInstance());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commandsArray;
    }
}
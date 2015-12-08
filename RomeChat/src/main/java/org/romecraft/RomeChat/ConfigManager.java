package org.romecraft.RomeChat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	private static ConfigManager instance = null;

	private RomeChat plugin;
	private File configFile;
	private FileConfiguration config;

	private ConfigManager(RomeChat plugin) {
		this.plugin = plugin;

		try {
			firstRun();
		} catch (Exception e) {
			e.printStackTrace();
		}

		config = new YamlConfiguration();
		loadYamls();
	}

	public static ConfigManager getInstance(RomeChat plugin) {
		if(instance == null) {
			instance = new ConfigManager(plugin);
		}

		return instance;
	}

	private void firstRun() throws Exception {
		if(!configFile.exists()){
			configFile.getParentFile().mkdirs();
			copy(plugin.getResource("config.yml"), configFile);
		}
	}

	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while((len=in.read(buf))>0){
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * in here, each of the FileConfigurations loaded the contents of yamls
	 *  found at the /plugins/<pluginName>/*yml.
	 * needed at onEnable() after using firstRun();
	 * can be called anywhere if you need to reload the yamls.
	 */
	public void loadYamls() {
		try {
			config.load(configFile); //loads the contents of the File to its FileConfiguration
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * save all FileConfigurations to its corresponding File
	 * optional at onDisable()
	 * can be called anywhere if you have *.set(path,value) on your methods
	 */
	public void saveYamls() {
		try {
			config.save(configFile); //saves the FileConfiguration to its File
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

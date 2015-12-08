package org.romecraft.RomeChat;

import java.io.File;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RomeChat extends JavaPlugin {

	public enum ChatColor {
		BLACK("§0"),
		NAVY("§1"),
		GREEN("§2"),
		BLUE("§3"),
		RED("§4"),
		PURPLE("§5"),
		GOLD("§6"),
		LIGHT_GRAY("§7"),
		GRAY("§8"),
		DARK_PURPLE("§9"),
		LIGHT_GREEN("§a"),
		LIGHT_BLUE("§b"),
		ROSE("§c"),
		LIGHT_PURPLE("§d"),
		YELLOW("§e"),
		WHITE("§f");

		public final String str;

		ChatColor(String str) {
			this.str = str;
		}
	}

	private static final Logger LOGGER = Logger.getLogger("Minecraft");
	private RomeChat plugin;
	private File configFile;
	private FileConfiguration config;
	private ConfigManager configManager;

	@Override
	public void onEnable() {
		plugin = this;

		configManager = ConfigManager.getInstance(plugin);
		configManager.loadYamls();
	}
}

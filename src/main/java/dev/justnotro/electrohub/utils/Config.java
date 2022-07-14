package dev.justnotro.electrohub.utils;

import dev.justnotro.electrohub.Electrohub;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Config extends YamlConfiguration {

	private static final Plugin plugin = Electrohub.getInstance();
	private static final File dataFolder = plugin.getDataFolder();
	
	@Getter
	private static final Map<String, Config> configs = new ConcurrentHashMap<>();
	
	@Setter
	@Getter
	private String name, folder;
	
	@Getter
	private File file;
	
	public Config(String name, @Nullable String folder, boolean loadFromDefault) {
		setName(name);
		setFolder(folder);
		configs.put(folder == null ? name : folder + File.separator + name, this);
		load(loadFromDefault);
	}
	
	public static Config getConfig(String name, @Nullable String folder, boolean loadFromDefault) {
		String path = folder == null ? name : folder + File.separator + name;
		return configs.containsKey(path) ? configs.get(name) : new Config(name, folder, loadFromDefault);
	}

	@SneakyThrows
	public void reloadConfig() {
		this.load(false);
	}

	@SneakyThrows
	protected void load(boolean defaultConfig) {
		if (defaultConfig) saveDefaultConfig();
		String path = (plugin.getDataFolder() + (folder != null ? (File.separator + folder) : ""));
		file = new File(path, name.toLowerCase() + ".yml");
		if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
		if (!file.exists()) file.createNewFile();
		
		this.load(file);
	}

	
	@SneakyThrows
	public void saveDefaultConfig() {
		plugin.saveResource(name.toLowerCase() + ".yml", false);
	}
	
	@SneakyThrows
	public void saveConfig() {
		this.options().copyDefaults(true);
		this.options().copyHeader(true);
		this.save(file);
	}

	@SneakyThrows
	public static List<String> scanFolder(String folder) {
		String path = (folder != null ? (File.separator + folder) : "");
		File[] files = new File(dataFolder + path).listFiles();
		return Stream.of(files)
				.filter(File::isFile)
				.map(file -> file.getName().replaceAll(".yml", ""))
				.collect(Collectors.toList());
	}

	public static class Locations {

		public Location deserialize(Config config, String section, boolean withYawPitch) {
			String[] splitter = config.getString(section).split(" ");
			World w = Bukkit.getWorld(splitter[0]);
			double x = Double.parseDouble(splitter[1]);
			double y = Double.parseDouble(splitter[2]);
			double z = Double.parseDouble(splitter[3]);

			if (!withYawPitch) return new Location(w, x, y, z);

			float yaw = Float.parseFloat(splitter[4]);
			float pitch = Float.parseFloat(splitter[5]);

			return new Location(w, x, y, z, yaw, pitch);
		}

		public static String serialize(Location loc) {
			DecimalFormat df = new DecimalFormat("#.##");

			return loc.getWorld().getName() +
					" " + df.format(loc.getX()) +
					" " + df.format(loc.getY()) +
					" " + df.format(loc.getZ()) +
					" " + df.format(loc.getYaw()) +
					" " + df.format(loc.getPitch());
		}

	}

	//TODO don't forget to finish this, this is very important.
	public static class ItemStack {

		public ItemBuilder deserialize(Config config, String section) {
			return null;
		}

		public static String serialize(Location loc) {
			return null;
		}

	}

	public static class Sound {

		public org.bukkit.Sound deserialize(Config config, String section) {
			return null;
		}

		public static String serialize(org.bukkit.Sound sound) {
			return null;
		}
	}
}

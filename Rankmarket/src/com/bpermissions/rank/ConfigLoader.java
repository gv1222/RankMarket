package com.bpermissions.rank;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class ConfigLoader
{
  public abstract void load();

  public abstract void save();

  protected void loadInternal(File file, YamlConfiguration config)
  {
    create(file);
    try {
      config.load(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void saveInternal(File file, YamlConfiguration config) {
    create(file);
    try {
      config.save(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void create(File file) {
    try {
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        file.createNewFile();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
package com.bpermissions.rank;

import java.io.PrintStream;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Util
{
  public static Permission permission = null;
  public static Economy economy = null;
  public static Chat chat = null;

  public static Boolean setupPermissions()
  {
    RegisteredServiceProvider permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
    if (permissionProvider != null) {
      permission = (Permission)permissionProvider.getProvider();
    }
    if (permission != null) return Boolean.valueOf(true); return Boolean.valueOf(false);
  }

  public static Boolean setupChat()
  {
    RegisteredServiceProvider chatProvider = getServer().getServicesManager().getRegistration(Chat.class);
    if (chatProvider != null) {
      chat = (Chat)chatProvider.getProvider();
    }
    if (chat != null) return Boolean.valueOf(true); return Boolean.valueOf(false);
  }

  public static Boolean setupEconomy()
  {
    RegisteredServiceProvider economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
    if (economyProvider != null) {
      economy = (Economy)economyProvider.getProvider();
    }
    if (economy != null) return Boolean.valueOf(true); return Boolean.valueOf(false);
  }

  public static Permission getPermissions()
  {
    return permission;
  }

  public static Economy getEconomy()
  {
    return economy;
  }

  public static Chat getChat()
  {
    return chat;
  }

  public static void log(String message, JavaPlugin plugin)
  {
    System.out.println("[" + plugin.getDescription().getName() + " " + plugin.getDescription().getVersion() + "] " + message);
  }

  private static Server getServer()
  {
    return Bukkit.getServer();
  }
}
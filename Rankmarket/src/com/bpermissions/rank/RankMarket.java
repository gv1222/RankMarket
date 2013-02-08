package com.bpermissions.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RankMarket extends JavaPlugin
{
  Economy economy;
  net.milkbowl.vault.permission.Permission permissions;
  RankMarketConfig config = new RankMarketConfig();

  public void onDisable()
  {
    Util.log("Disabled", this);
  }

  public void onEnable()
  {
    if (Util.setupEconomy().booleanValue())
      this.economy = Util.getEconomy();
    if (Util.setupPermissions().booleanValue())
      this.permissions = Util.getPermissions();
    this.config.load();
    registerPermissions();
    Util.log("Enabled", this);
  }

  private void registerPermissions()
  {
    PluginManager pm = getServer().getPluginManager();
    Map children = new HashMap();
    for (Rank rank : getAllRanks())
      children.put(rank.getPermission(), Boolean.valueOf(true));
    org.bukkit.permissions.Permission perm = new org.bukkit.permissions.Permission("rankmarket.*", PermissionDefault.OP, children);
    pm.addPermission(perm);
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((this.economy == null) || (this.permissions == null)) {
      sender.sendMessage(ChatColor.RED + "Please install additional plugins in order for this to work.");
      return true;
    }
    if ((sender instanceof Player)) {
      Player player = (Player)sender;
      if (args.length == 0) {
        List ranks = getAllowedRanks(player);
        sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.GREEN + "Available ranks:");
        if (ranks.size() == 0) {
          sender.sendMessage(ChatColor.RED + " No ranks available ");
        } else {
          for (int i = 0; (i < ranks.size()) && (i < 6); i++) {
            Rank rank = (Rank)ranks.get(i);
            sender.sendMessage(ChatColor.BLUE + " - " + rank.getName() + ChatColor.WHITE + " cost: " + ChatColor.GRAY + this.economy.format(rank.getCost()));
          }
          sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.GREEN + "Use /" + label + " [name] to purchase a rank.");
          return true;
        }
      } else if (args.length == 1) {
        String rank = args[0];
        if (this.config.hasRank(rank)) {
          Rank r = this.config.getRank(rank);
          if ((player.hasPermission(r.getPermission())) && (this.economy.has(player.getName(), r.getCost()))) {
            sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.GREEN + "You have purchased the rank " + r.getName() + " for " + this.economy.format(r.getCost()));
            this.economy.withdrawPlayer(player.getName(), r.getCost());

            String[] groups = this.permissions.getPlayerGroups(player);
            for (String g : groups) {
              this.permissions.playerRemoveGroup(player, g);
            }
            for (String g : r.getGroups())
              this.permissions.playerAddGroup(player, g);
            return true;
          }
          sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.RED + "You don't have enough money to purchase that rank!");
          return true;
        }

        sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.RED + "There is no rank by that name.");
        return true;
      }

      return false;
    }
    sender.sendMessage("Only players can buy ranks.");
    return true;
  }

  public List<Rank> getAllowedRanks(Player player)
  {
    List ranks = getAllRanks();
    List allowed = new ArrayList();
    for (Rank rank : ranks)
      if (player.hasPermission(rank.getPermission()))
        allowed.add(rank);
    return allowed;
  }

  public List<Rank> getAllRanks() {
    return this.config.getAllRanks();
  }
}
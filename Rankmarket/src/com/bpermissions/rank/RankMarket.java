/*     */ package com.bpermissions.rank;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.milkbowl.vault.economy.Economy;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.permissions.PermissionDefault;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class RankMarket extends JavaPlugin
/*     */ {
/*     */   Economy economy;
/*     */   net.milkbowl.vault.permission.Permission permissions;
/*  22 */   RankMarketConfig config = new RankMarketConfig();
/*     */ 
/*     */   public void onDisable()
/*     */   {
/*  26 */     Util.log("Disabled", this);
/*     */   }
/*     */ 
/*     */   public void onEnable()
/*     */   {
/*  31 */     if (Util.setupEconomy().booleanValue())
/*  32 */       this.economy = Util.getEconomy();
/*  33 */     if (Util.setupPermissions().booleanValue())
/*  34 */       this.permissions = Util.getPermissions();
/*  35 */     this.config.load();
/*  36 */     registerPermissions();
/*  37 */     Util.log("Enabled", this);
/*     */   }
/*     */ 
/*     */   private void registerPermissions()
/*     */   {
/*  42 */     PluginManager pm = getServer().getPluginManager();
/*  43 */     Map children = new HashMap();
/*  44 */     for (Rank rank : getAllRanks())
/*  45 */       children.put(rank.getPermission(), Boolean.valueOf(true));
/*  46 */     org.bukkit.permissions.Permission perm = new org.bukkit.permissions.Permission("rankmarket.*", PermissionDefault.OP, children);
/*  47 */     pm.addPermission(perm);
/*     */   }
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
/*     */   {
/*  54 */     if ((this.economy == null) || (this.permissions == null)) {
/*  55 */       sender.sendMessage(ChatColor.RED + "Please install additional plugins in order for this to work.");
/*  56 */       return true;
/*     */     }
/*  58 */     if ((sender instanceof Player)) {
/*  59 */       Player player = (Player)sender;
/*  60 */       if (args.length == 0) {
/*  61 */         List ranks = getAllowedRanks(player);
/*  62 */         sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.GREEN + "Available ranks:");
/*  63 */         if (ranks.size() == 0) {
/*  64 */           sender.sendMessage(ChatColor.RED + " No ranks available ");
/*     */         } else {
/*  66 */           for (int i = 0; (i < ranks.size()) && (i < 6); i++) {
/*  67 */             Rank rank = (Rank)ranks.get(i);
/*  68 */             sender.sendMessage(ChatColor.BLUE + " - " + rank.getName() + ChatColor.WHITE + " cost: " + ChatColor.GRAY + this.economy.format(rank.getCost()));
/*     */           }
/*  70 */           sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.GREEN + "Use /" + label + " [name] to purchase a rank.");
/*  71 */           return true;
/*     */         }
/*  73 */       } else if (args.length == 1) {
/*  74 */         String rank = args[0];
/*  75 */         if (this.config.hasRank(rank)) {
/*  76 */           Rank r = this.config.getRank(rank);
/*  77 */           if ((player.hasPermission(r.getPermission())) && (this.economy.has(player.getName(), r.getCost()))) {
/*  78 */             sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.GREEN + "You have purchased the rank " + r.getName() + " for " + this.economy.format(r.getCost()));
/*  79 */             this.economy.withdrawPlayer(player.getName(), r.getCost());
/*     */ 
/*  81 */             String[] groups = this.permissions.getPlayerGroups(player);
/*  82 */             for (String g : groups) {
/*  83 */               this.permissions.playerRemoveGroup(player, g);
/*     */             }
/*  85 */             for (String g : r.getGroups())
/*  86 */               this.permissions.playerAddGroup(player, g);
/*  87 */             return true;
/*     */           }
/*  89 */           sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.RED + "You don't have enough money to purchase that rank!");
/*  90 */           return true;
/*     */         }
/*     */ 
/*  93 */         sender.sendMessage(ChatColor.GOLD + "[RM] " + ChatColor.RED + "There is no rank by that name.");
/*  94 */         return true;
/*     */       }
/*     */ 
/*  97 */       return false;
/*     */     }
/*  99 */     sender.sendMessage("Only players can buy ranks.");
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   public List<Rank> getAllowedRanks(Player player)
/*     */   {
/* 105 */     List ranks = getAllRanks();
/* 106 */     List allowed = new ArrayList();
/* 107 */     for (Rank rank : ranks)
/* 108 */       if (player.hasPermission(rank.getPermission()))
/* 109 */         allowed.add(rank);
/* 110 */     return allowed;
/*     */   }
/*     */ 
/*     */   public List<Rank> getAllRanks() {
/* 114 */     return this.config.getAllRanks();
/*     */   }
/*     */ }

/* Location:           C:\Users\Gv1222\Desktop\RankMarket.jar
 * Qualified Name:     com.bpermissions.rank.RankMarket
 * JD-Core Version:    0.6.2
 */
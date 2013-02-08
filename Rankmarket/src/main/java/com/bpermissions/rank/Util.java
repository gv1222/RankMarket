/*    */ package main.java.com.bpermissions.rank;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import net.milkbowl.vault.chat.Chat;
/*    */ import net.milkbowl.vault.economy.Economy;
/*    */ import net.milkbowl.vault.permission.Permission;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
/*    */ import org.bukkit.plugin.ServicesManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class Util
/*    */ {
/* 14 */   public static Permission permission = null;
/* 15 */   public static Economy economy = null;
/* 16 */   public static Chat chat = null;
/*    */ 
/*    */   public static Boolean setupPermissions()
/*    */   {
/* 20 */     RegisteredServiceProvider permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
/* 21 */     if (permissionProvider != null) {
/* 22 */       permission = (Permission)permissionProvider.getProvider();
/*    */     }
/* 24 */     if (permission != null) return Boolean.valueOf(true); return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */   public static Boolean setupChat()
/*    */   {
/* 29 */     RegisteredServiceProvider chatProvider = getServer().getServicesManager().getRegistration(Chat.class);
/* 30 */     if (chatProvider != null) {
/* 31 */       chat = (Chat)chatProvider.getProvider();
/*    */     }
/* 33 */     if (chat != null) return Boolean.valueOf(true); return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */   public static Boolean setupEconomy()
/*    */   {
/* 38 */     RegisteredServiceProvider economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
/* 39 */     if (economyProvider != null) {
/* 40 */       economy = (Economy)economyProvider.getProvider();
/*    */     }
/* 42 */     if (economy != null) return Boolean.valueOf(true); return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */   public static Permission getPermissions()
/*    */   {
/* 47 */     return permission;
/*    */   }
/*    */ 
/*    */   public static Economy getEconomy()
/*    */   {
/* 52 */     return economy;
/*    */   }
/*    */ 
/*    */   public static Chat getChat()
/*    */   {
/* 57 */     return chat;
/*    */   }
/*    */ 
/*    */   public static void log(String message, JavaPlugin plugin)
/*    */   {
/* 62 */     System.out.println("[" + plugin.getDescription().getName() + " " + plugin.getDescription().getVersion() + "] " + message);
/*    */   }
/*    */ 
/*    */   private static Server getServer()
/*    */   {
/* 67 */     return Bukkit.getServer();
/*    */   }
/*    */ }

/* Location:           C:\Users\Gv1222\Desktop\RankMarket.jar
 * Qualified Name:     com.bpermissions.rank.Util
 * JD-Core Version:    0.6.2
 */
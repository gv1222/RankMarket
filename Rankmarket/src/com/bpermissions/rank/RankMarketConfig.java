/*    */ package com.bpermissions.rank;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ public class RankMarketConfig extends ConfigLoader
/*    */ {
/* 11 */   private final File file = new File("plugins/RankMarket/config.yml");
/* 12 */   private final YamlConfiguration config = new YamlConfiguration();
/*    */ 
/* 14 */   Map<String, Rank> ranks = new HashMap();
/*    */ 
/*    */   public void load()
/*    */   {
/* 18 */     loadInternal(this.file, this.config);
/* 19 */     if ((this.config.getConfigurationSection("ranks") == null) || (this.config.getConfigurationSection("ranks").getKeys(false) == null) || (this.config.getConfigurationSection("ranks").getKeys(false).size() == 0)) {
/* 20 */       setDefaults();
/*    */     }
/* 22 */     ConfigurationSection ranks = this.config.getConfigurationSection("ranks");
/* 23 */     Set keys = ranks.getKeys(false);
/* 24 */     for (String key : keys) {
/* 25 */       String rank = key;
/* 26 */       List groups = ranks.getStringList(rank + ".groups");
/* 27 */       int cost = ranks.getInt(rank + ".cost");
/* 28 */       Rank r = new Rank(rank, groups, cost);
/*    */ 
/* 30 */       this.ranks.put(r.getName().toLowerCase(), r);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void save()
/*    */   {
/* 36 */     saveInternal(this.file, this.config);
/*    */   }
/*    */ 
/*    */   public void setDefaults() {
/* 40 */     ConfigurationSection ranks = this.config.createSection("ranks");
/*    */ 
/* 42 */     String rank = "miner";
/* 43 */     List groups = new ArrayList();
/* 44 */     groups.add("miner");
/* 45 */     int cost = 500;
/* 46 */     ranks.set(rank + ".groups", groups);
/* 47 */     ranks.set(rank + ".cost", Integer.valueOf(cost));
/*    */ 
/* 49 */     rank = "builder";
/* 50 */     groups = new ArrayList();
/* 51 */     groups.add("builder");
/* 52 */     ranks.set(rank + ".groups", groups);
/* 53 */     ranks.set(rank + ".cost", Integer.valueOf(cost));
/* 54 */     save();
/*    */   }
/*    */ 
/*    */   public List<Rank> getAllRanks() {
/* 58 */     Set rs = this.ranks.keySet();
/* 59 */     List rk = new ArrayList();
/* 60 */     for (String r : rs) {
/* 61 */       rk.add((Rank)this.ranks.get(r));
/*    */     }
/* 63 */     return rk;
/*    */   }
/*    */ 
/*    */   public boolean hasRank(String name) {
/* 67 */     return this.ranks.containsKey(name.toLowerCase());
/*    */   }
/*    */ 
/*    */   public Rank getRank(String name) {
/* 71 */     return (Rank)this.ranks.get(name.toLowerCase());
/*    */   }
/*    */ }

/* Location:           C:\Users\Gv1222\Desktop\RankMarket.jar
 * Qualified Name:     com.bpermissions.rank.RankMarketConfig
 * JD-Core Version:    0.6.2
 */
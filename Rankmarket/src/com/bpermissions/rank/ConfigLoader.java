/*    */ package com.bpermissions.rank;
/*    */ 
/*    */ import java.io.File;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ public abstract class ConfigLoader
/*    */ {
/*    */   public abstract void load();
/*    */ 
/*    */   public abstract void save();
/*    */ 
/*    */   protected void loadInternal(File file, YamlConfiguration config)
/*    */   {
/* 14 */     create(file);
/*    */     try {
/* 16 */       config.load(file);
/*    */     } catch (Exception e) {
/* 18 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   protected void saveInternal(File file, YamlConfiguration config) {
/* 23 */     create(file);
/*    */     try {
/* 25 */       config.save(file);
/*    */     } catch (Exception e) {
/* 27 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   private void create(File file) {
/*    */     try {
/* 33 */       if (!file.exists()) {
/* 34 */         file.getParentFile().mkdirs();
/* 35 */         file.createNewFile();
/*    */       }
/*    */     } catch (Exception e) {
/* 38 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Gv1222\Desktop\RankMarket.jar
 * Qualified Name:     com.bpermissions.rank.ConfigLoader
 * JD-Core Version:    0.6.2
 */
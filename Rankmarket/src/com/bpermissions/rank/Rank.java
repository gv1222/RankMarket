/*    */ package com.bpermissions.rank;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class Rank
/*    */ {
/*    */   private final String name;
/*    */   private final List<String> groups;
/*    */   private final int cost;
/*    */ 
/*    */   protected Rank(String name, List<String> groups, int cost)
/*    */   {
/* 12 */     this.name = name;
/* 13 */     this.groups = groups;
/* 14 */     this.cost = cost;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 19 */     return this.name.hashCode();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 24 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 28 */     return this.name;
/*    */   }
/*    */ 
/*    */   public List<String> getGroups() {
/* 32 */     return this.groups;
/*    */   }
/*    */ 
/*    */   public int getCost() {
/* 36 */     return this.cost;
/*    */   }
/*    */ 
/*    */   public String getPermission() {
/* 40 */     return "rankmarket." + this.name.toLowerCase();
/*    */   }
/*    */ }

/* Location:           C:\Users\Gv1222\Desktop\RankMarket.jar
 * Qualified Name:     com.bpermissions.rank.Rank
 * JD-Core Version:    0.6.2
 */
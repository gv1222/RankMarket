package com.bpermissions.rank;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class RankMarketConfig extends ConfigLoader
{
  private final File file = new File("plugins/RankMarket/config.yml");
  private final YamlConfiguration config = new YamlConfiguration();

  Map<String, Rank> ranks = new HashMap();

  public void load()
  {
    loadInternal(this.file, this.config);
    if ((this.config.getConfigurationSection("ranks") == null) || (this.config.getConfigurationSection("ranks").getKeys(false) == null) || (this.config.getConfigurationSection("ranks").getKeys(false).size() == 0)) {
      setDefaults();
    }
    ConfigurationSection ranks = this.config.getConfigurationSection("ranks");
    Set keys = ranks.getKeys(false);
    for (String key : keys) {
      String rank = key;
      List groups = ranks.getStringList(rank + ".groups");
      int cost = ranks.getInt(rank + ".cost");
      Rank r = new Rank(rank, groups, cost);

      this.ranks.put(r.getName().toLowerCase(), r);
    }
  }

  public void save()
  {
    saveInternal(this.file, this.config);
  }

  public void setDefaults() {
    ConfigurationSection ranks = this.config.createSection("ranks");

    String rank = "miner";
    List groups = new ArrayList();
    groups.add("miner");
    int cost = 500;
    ranks.set(rank + ".groups", groups);
    ranks.set(rank + ".cost", Integer.valueOf(cost));

    rank = "builder";
    groups = new ArrayList();
    groups.add("builder");
    ranks.set(rank + ".groups", groups);
    ranks.set(rank + ".cost", Integer.valueOf(cost));
    save();
  }

  public List<Rank> getAllRanks() {
    Set rs = this.ranks.keySet();
    List rk = new ArrayList();
    for (String r : rs) {
      rk.add((Rank)this.ranks.get(r));
    }
    return rk;
  }

  public boolean hasRank(String name) {
    return this.ranks.containsKey(name.toLowerCase());
  }

  public Rank getRank(String name) {
    return (Rank)this.ranks.get(name.toLowerCase());
  }
}
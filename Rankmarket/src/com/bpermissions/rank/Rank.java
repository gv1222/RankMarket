package com.bpermissions.rank;

import java.util.List;

public class Rank
{
  private final String name;
  private final List<String> groups;
  private final int cost;

  protected Rank(String name, List<String> groups, int cost)
  {
    this.name = name;
    this.groups = groups;
    this.cost = cost;
  }

  public int hashCode()
  {
    return this.name.hashCode();
  }

  public String toString()
  {
    return this.name;
  }

  public String getName() {
    return this.name;
  }

  public List<String> getGroups() {
    return this.groups;
  }

  public int getCost() {
    return this.cost;
  }

  public String getPermission() {
    return "rankmarket." + this.name.toLowerCase();
  }
}
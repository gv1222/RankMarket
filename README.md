RankMarket
==========
RankMarket

RankMarket is designed to let your users choose their donation benefits or earn money in your economy and rank up.

Each "rank" has a permission node associated with it's availability for purchase, so you can make even design tiered rank up systems, where each rank unlocks the next for purchase!

How to use

Typing '/rank' in-game will open up a list of available ranks for purchase. Type '/rank name' to purchase the rank (if you have enough funds available).

Configuration

The RankMarket config file (plugins/RankMarket/config.yml) is the only place you need to edit to setup your ranks.

ranks:
  miner:
    groups:
    - mine
    cost: 200
  builder:
    groups:
    - build
    cost: 500
For more information on configuration check out the Config page.

Permissions

RankMarket uses the "SuperPerms" system to actually check for permissions, but lets you use any Vault supported Permission plugin to make the purchase.

The 'rankmarket.*' node gives access to all rankmarket permissions.

Economy

RankMarket supports any Vault supported Economy plugin, what you use is entirely up to you!

It takes the money from the players 'balance in hand' or 'wallet' rather than banks.

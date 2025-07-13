# Overral
You just simply get a dragon egg each time beating the Ender Dragon. I know there are 2 mods did this already but I found no mod that have ordinal number to easier managing. So I made myself.

# Feature
The max number of generation of the Dragon Egg is 2097151.After reaching it max, it will not be count more and stay at 2097151 for every egg then.

The Dragon Egg will generate everytime the dragon is beaten. It is also have a block state to keep track of ordinal number of that Dragon.

### **GAMERULE**
- **maxEggsGeneration** gamerule, default is 1, minimum is 0, maximum is 1048575.
- **continueWhenMax** gamerule, default is false.
> - If **true**, it will continue spawn the dragon egg after beating the dragon even maxEggsGeneration is max. The dragon egg's "generation" block state will stay at the maxium maxEggsGeneration gamerule and will con continue to count.
> - If **false**, it will stop generate the dragon egg after dragon egg's "generation" block state hit the maximum maxEggsGeneration.

![Egg Generation](https://cdn.modrinth.com/data/Msg6LrSS/images/82cc3f6c902ee80acec31f2f1a787ebf2704690c.jpeg)

The Dragon Egg have it state default to 0 when getting from creative inventory.

![Default number](https://cdn.modrinth.com/data/Msg6LrSS/images/5d0747a2a3153262978cfd12e0849a40baf29dbc.png)

# Conflict
This mod is conflict with [Infinite Dragon Eggs](https://modrinth.com/mod/infinite-dragon-eggs)

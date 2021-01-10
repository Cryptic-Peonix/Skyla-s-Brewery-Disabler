# Skyla-s-Brewery-Disabler

## What is this?
- A Minecraft spigot plugin for version 1.16.
- Disables brewing, splash potions, lingering potions, and drinkable potions
- Fully configurable with the config.yml file

## The Config File

## More in-depth Plugin info
### To Reload the Plugin:
    Use the command /sbd reload

How Brewery Disabling works:
  To make the plugin comapitble with the Brewery plugin, cpotion crafting is not directly disabled. Instead, the refuling of brewing stands is blocked.
  It uses a listner to check whenever a brewing stand is about to be refuled and blocks it

Splash Potions and Lingering Potions:
  The plugin listens whenever a player right clicks air or a block.
    - Can be enabled/disabled with the command /splash-potions (or /linger-potions) enabled true/false
  If the item in the players hand is a splash potion/lingering potion, the event is cancelled and the potion will not be thrown.
  Admins can also configure if the item is deleted from the player's inventory after attempted use.
    - Can use the config.yml or /splash-potions (or /linger-potions) delete-item true/false
  The plugin can also disable these potions being thrown out dispensers
    - Also configrable with the comfig.yml file or /splash-potions (/linger-potions) can-use-dispensers true/false
